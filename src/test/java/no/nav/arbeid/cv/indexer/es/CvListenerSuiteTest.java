package no.nav.arbeid.cv.indexer.es;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import java.net.SocketTimeoutException;
import java.util.Properties;
import java.util.UUID;

import org.apache.http.HttpHost;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import no.nav.arbeid.cv.events.CvEvent;
import no.nav.arbeid.cv.indexer.config.IndexerServiceConfig;
import no.nav.arbeid.cv.indexer.config.KafkaConfig;
import no.nav.arbeid.cv.indexer.config.TopicNames;
import no.nav.arbeid.cv.indexer.config.temp.TempCvEventObjectMother;
import no.nav.arbeid.cv.indexer.domene.ApplicationException;
import no.nav.arbeid.cv.indexer.domene.OperationalException;
import no.nav.arbeid.cv.indexer.service.CvIndexerService;
import no.nav.arbeid.cv.kandidatsok.config.MethodSecurityConfig;
import no.nav.arbeid.cv.kandidatsok.config.OidcConfigurationDev;
import no.nav.arbeid.cv.kandidatsok.config.WireMockConfig;
import no.nav.security.spring.oidc.test.TokenGeneratorConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CvListenerSuiteTest {
  /*
   * For å kunne kjøre denne testen må Linux rekonfigureres litt.. Lag en fil i
   * /etc/sysctl.d/01-increase_vm_max_map_count.conf som inneholder følgende: vm.max_map_count =
   * 262144
   */

  // Kjører "docker-compose up" manuelt istedenfor denne ClassRule:
  /*
   * @ClassRule public static DockerComposeRule docker =
   * DockerComposeRule.builder().file("src/test/resources/docker-compose-kafka-og-es.yml") //
   * .waitingForHostNetworkedPort(9200, port -> SuccessOrFailure //
   * .fromBoolean(port.isListeningNow(), "Internal port " + port + " was not listening")) .build();
   */
  @Autowired
  private KafkaTemplate kafkaTemplate;

  @Autowired
  private CvIndexerService cvIndexerServiceMock;

  @TestConfiguration
  @OverrideAutoConfiguration(enabled = true)
  @ImportAutoConfiguration(classes = {KafkaAutoConfiguration.class},
      exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
  @Import({IndexerServiceConfig.class, KafkaConfig.class, MethodSecurityConfig.class,
      TokenGeneratorConfiguration.class, OidcConfigurationDev.class, WireMockConfig.class})
  static class TestConfig {
    @Bean
    public CvIndexerService cvIndexerService() {
      return Mockito.mock(CvIndexerService.class);
    }

    @Bean
    public RestHighLevelClient restHighLevelClient() {
      return new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9250, "http")));
    }
  }

  @Test
  public void skalMottaOgIndeksereEvents() {
    Mockito.reset(cvIndexerServiceMock);

    CvEvent publisertEvent = TempCvEventObjectMother.giveMeCvEvent();
    publisertEvent.setAdresselinje1(UUID.randomUUID().toString());
    kafkaTemplate.send(TopicNames.TOPIC_CVEVENT_V3, publisertEvent);

    verify(cvIndexerServiceMock, timeout(500l).times(1)).bulkIndekser(argThat(e -> e.stream()
        .anyMatch(v -> publisertEvent.getAdresselinje1().equals(v.getAdresselinje1()))));
  }

  private Appender mockLogger(Class clazz) {
    ch.qos.logback.classic.Logger eventLogger =
        (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(clazz);
    final Appender appender = Mockito.mock(Appender.class);
    Mockito.when(appender.getName()).thenReturn("MockAppender");
    eventLogger.addAppender(appender);
    return appender;
  }

  @Test
  public void skalLoggeApplikasjonsfeilOgFortsetteMedProsesseringAvEvents() {
    String feilendeAdresse = UUID.randomUUID().toString();
    String okAdresse1 = UUID.randomUUID().toString();
    String okAdresse2 = UUID.randomUUID().toString();

    Appender mockAppender = mockLogger(KafkaConfig.class);

    Mockito.reset(cvIndexerServiceMock);
    Mockito.doThrow(new ApplicationException("Applikasjonsfeil", new NullPointerException()))
        .when(cvIndexerServiceMock).bulkIndekser(
            argThat(e -> e.stream().anyMatch(v -> feilendeAdresse.equals(v.getAdresselinje1()))));

    CvEvent publisertEvent1 = TempCvEventObjectMother.giveMeCvEvent();
    publisertEvent1.setAdresselinje1(okAdresse1);
    CvEvent publisertEvent2 = TempCvEventObjectMother.giveMeCvEvent();
    publisertEvent2.setAdresselinje1(feilendeAdresse);
    CvEvent publisertEvent3 = TempCvEventObjectMother.giveMeCvEvent();
    publisertEvent3.setAdresselinje1(okAdresse2);
    kafkaTemplate.send(TopicNames.TOPIC_CVEVENT_V3, publisertEvent1);
    kafkaTemplate.send(TopicNames.TOPIC_CVEVENT_V3, publisertEvent2);

    verify(cvIndexerServiceMock, timeout(500l).times(1)).bulkIndekser(
        argThat(e -> e.stream().anyMatch(v -> okAdresse1.equals(v.getAdresselinje1()))));
    verify(cvIndexerServiceMock, timeout(500l).times(1)).bulkIndekser(
        argThat(e -> e.stream().anyMatch(v -> feilendeAdresse.equals(v.getAdresselinje1()))));
    kafkaTemplate.send(TopicNames.TOPIC_CVEVENT_V3, publisertEvent3);
    verify(cvIndexerServiceMock, timeout(1000l).times(1)).bulkIndekser(
        argThat(e -> e.stream().anyMatch(v -> okAdresse2.equals(v.getAdresselinje1()))));

    verify(mockAppender).doAppend(
        argThat(le -> ((LoggingEvent) le).getFormattedMessage().contains("Applikasjonsfeil")));
  }


  @Test
  public void skalRekjoreInfrastrukturfeil() {
    String feilendeAdresse = UUID.randomUUID().toString();
    String okAdresse1 = UUID.randomUUID().toString();
    String okAdresse2 = UUID.randomUUID().toString();

    Mockito.reset(cvIndexerServiceMock);
    Mockito.doThrow(new OperationalException("Infrastrukturfeil 1", new SocketTimeoutException()))
        .doThrow(new OperationalException("Infrastrukturfeil 2", new SocketTimeoutException()))
        .doNothing().when(cvIndexerServiceMock).bulkIndekser(
            argThat(e -> e.stream().anyMatch(v -> feilendeAdresse.equals(v.getAdresselinje1()))));

    CvEvent publisertEvent1 = TempCvEventObjectMother.giveMeCvEvent();
    publisertEvent1.setAdresselinje1(feilendeAdresse);
    CvEvent publisertEvent2 = TempCvEventObjectMother.giveMeCvEvent();
    publisertEvent2.setAdresselinje1(okAdresse1);
    CvEvent publisertEvent3 = TempCvEventObjectMother.giveMeCvEvent();
    publisertEvent3.setAdresselinje1(okAdresse2);
    kafkaTemplate.send(TopicNames.TOPIC_CVEVENT_V3, publisertEvent1);
    kafkaTemplate.send(TopicNames.TOPIC_CVEVENT_V3, publisertEvent2);

    verify(cvIndexerServiceMock, timeout(2500l).times(3)).bulkIndekser(
        argThat(e -> e.stream().anyMatch(v -> feilendeAdresse.equals(v.getAdresselinje1()))));

    verify(cvIndexerServiceMock, timeout(500l).atLeast(1)).bulkIndekser(
        argThat(e -> e.stream().anyMatch(v -> okAdresse1.equals(v.getAdresselinje1()))));

    kafkaTemplate.send(TopicNames.TOPIC_CVEVENT_V3, publisertEvent3);
    verify(cvIndexerServiceMock, timeout(500l).times(1)).bulkIndekser(
        argThat(e -> e.stream().anyMatch(v -> okAdresse2.equals(v.getAdresselinje1()))));
  }

  @Test
  public void skalKunneMottaSlettemeldinger() {
    Mockito.reset(cvIndexerServiceMock);

    kafkaTemplate.send(TopicNames.TOPIC_CVEVENT_V3, 1l, null);
    verify(cvIndexerServiceMock, timeout(100l).times(1))
        .bulkSlett(argThat(e -> e.stream().anyMatch(v -> v.equals(1l))));
  }


  @Test
  @Ignore("spring-kafka issue #667 må fikses før vi kan håndtere giftpiller")
  public void skalLoggeOgIgnorereGiftpiller() {
    String okAdresse1 = UUID.randomUUID().toString();
    String okAdresse2 = UUID.randomUUID().toString();

    Appender mockAppender = mockLogger(KafkaConfig.class);

    Mockito.reset(cvIndexerServiceMock);
    CvEvent publisertEvent1 = TempCvEventObjectMother.giveMeCvEvent();
    publisertEvent1.setAdresselinje1(okAdresse1);
    CvEvent publisertEvent3 = TempCvEventObjectMother.giveMeCvEvent();
    publisertEvent3.setAdresselinje1(okAdresse2);
    kafkaTemplate.send(TopicNames.TOPIC_CVEVENT_V3, publisertEvent1);
    sendGiftpille();
    kafkaTemplate.send(TopicNames.TOPIC_CVEVENT_V3, publisertEvent3);

    verify(cvIndexerServiceMock, timeout(500l).times(1)).bulkIndekser(
        argThat(e -> e.stream().anyMatch(v -> okAdresse1.equals(v.getAdresselinje1()))));

    kafkaTemplate.send(TopicNames.TOPIC_CVEVENT_V3, publisertEvent3);
    verify(cvIndexerServiceMock, timeout(100l).times(1)).bulkIndekser(
        argThat(e -> e.stream().anyMatch(v -> okAdresse2.equals(v.getAdresselinje1()))));

    verify(mockAppender).doAppend(
        argThat(le -> ((LoggingEvent) le).getFormattedMessage().contains("Applikasjonsfeil")));
  }

  private void sendGiftpille() {
    Properties props = new Properties();
    props.put("bootstrap.servers", "localhost:9092");
    props.put("acks", "all");
    props.put("retries", 0);
    props.put("batch.size", 16384);
    props.put("linger.ms", 1);
    props.put("buffer.memory", 33554432);
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

    Producer<String, String> producer = new KafkaProducer<>(props);
    producer.send(
        new ProducerRecord<String, String>(TopicNames.TOPIC_CVEVENT_V3, "pille", "Giftpille"));
    producer.close();
  }

}