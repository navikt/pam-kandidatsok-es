package no.nav.arbeid.cv.es.es;

import java.util.Collections;
import no.nav.arbeid.cv.es.client.EsCvClient;
import no.nav.arbeid.cv.es.config.ServiceConfig;
import no.nav.arbeid.cv.es.config.temp.TempCvEventObjectMother;
import no.nav.arbeid.cv.es.domene.Aggregering;
import no.nav.arbeid.cv.es.domene.EsCv;
import no.nav.arbeid.cv.es.domene.Sokekriterier;
import no.nav.arbeid.cv.es.domene.Sokeresultat;
import no.nav.arbeid.cv.es.service.EsCvTransformer;
import no.nav.security.spring.oidc.test.TokenGeneratorConfiguration;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexCvSuiteTest {

  private static final String ES_DOCKER_SERVICE = "elastic_search";

  /*
   * For å kunne kjøre denne testen må Linux rekonfigureres litt.. Lag en fil i
   * /etc/sysctl.d/01-increase_vm_max_map_count.conf som inneholder følgende: vm.max_map_count =
   * 262144
   */

  // Kjører "docker-compose up" manuelt istedenfor denne ClassRule:
/*
  @ClassRule
  public static DockerComposeRule docker =
      DockerComposeRule.builder().file("src/test/resources/docker-compose-kun-es.yml")
          // .waitingForHostNetworkedPort(9200, port -> SuccessOrFailure
          // .fromBoolean(port.isListeningNow(), "Internal port " + port + " was not listening"))
          .build();
*/
  @Autowired
  private EsCvTransformer transformer;

  @Autowired
  private EsCvClient client;

  @TestConfiguration
  @OverrideAutoConfiguration(enabled = true)
  @ImportAutoConfiguration(exclude = {KafkaAutoConfiguration.class,
      DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
  @Import({ServiceConfig.class, TokenGeneratorConfiguration.class})
  static class TestConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient() {
      return new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
    }

  }

  @Before
  public void before() throws IOException {
    try {
      client.deleteIndex();
    } catch (Exception e) {
      // Ignore
    }

    client.createIndex();

    client.index(transformer.transform(TempCvEventObjectMother.giveMeCvEvent()));
    client.index(transformer.transform(TempCvEventObjectMother.giveMeCvEvent2()));
    client.index(transformer.transform(TempCvEventObjectMother.giveMeCvEvent3()));
    client.index(transformer.transform(TempCvEventObjectMother.giveMeCvEvent4()));
    client.index(transformer.transform(TempCvEventObjectMother.giveMeCvEvent5()));
  }

  @After
  public void after() throws IOException {
    client.deleteIndex();
  }

  @Test
  public void test() throws IOException {
    Sokeresultat sokeres =
        client.sok(Sokekriterier.med()
            .etternavn("NORDMANN")
            .nusKode("355211")
            .bygg());
    List<EsCv> list = sokeres.getCver();
    List<Aggregering> aggregeringer = sokeres.getAggregeringer();

    assertThat(list.size()).isEqualTo(1);
    EsCv esCv = list.get(0);
    assertThat(esCv).isEqualTo(transformer.transform(TempCvEventObjectMother.giveMeCvEvent()));
  }

  @Test
  public void testUtenSokekriterierReturnererAlleTestPersoner() throws IOException {
    Sokeresultat sokeresultat =
        client.sok(Sokekriterier.med().bygg());

    List<EsCv> cver = sokeresultat.getCver();

    assertThat(cver.size()).isEqualTo(5);
  }

  @Test
  public void testFlereInputFritekstGirBredereResultat() throws IOException {
    Sokeresultat sokeresultat1 =
        client.sok(Sokekriterier.med()
            .fritekst("javautvikler")
            .bygg());
    Sokeresultat sokeresultat =
        client.sok(Sokekriterier.med()
            .fritekst("industrimekaniker javautvikler")
            .bygg());

    List<EsCv> cver1 = sokeresultat1.getCver();
    List<EsCv> cver = sokeresultat.getCver();

    assertThat(cver1.size()).isLessThan(cver.size());
    assertThat(cver1.size()).isEqualTo(1);
    assertThat(cver.size()).isEqualTo(3);
  }

  @Test
  public void testSokPaNorskeStoppordGirIkkeResultat() throws IOException {
    Sokeresultat sokeresultatYrke =
        client.sok(Sokekriterier.med()
            .stillingstitler(Collections.singletonList("og"))
            .bygg());
    Sokeresultat sokeresultatKomp =
        client.sok(Sokekriterier.med()
            .kompetanser(Collections.singletonList("og"))
            .bygg());
    Sokeresultat sokeresultatUtdanning =
        client.sok(Sokekriterier.med()
            .utdanninger(Collections.singletonList("og"))
            .bygg());
    Sokeresultat sokeresultatFritekst =
        client.sok(Sokekriterier.med()
            .fritekst("og")
            .bygg());

    List<EsCv> cverYrke = sokeresultatYrke.getCver();
    List<EsCv> cverKomp = sokeresultatKomp.getCver();
    List<EsCv> cverUtdanning = sokeresultatUtdanning.getCver();
    List<EsCv> cverFritekst = sokeresultatFritekst.getCver();

    assertThat(cverYrke.size()).isEqualTo(0);
    assertThat(cverKomp.size()).isEqualTo(0);
    assertThat(cverUtdanning.size()).isEqualTo(0);
    assertThat(cverFritekst.size()).isEqualTo(0);
  }

  @Test
  public void testSokMedFlereKriterierGirSvarMedAlleFelter() throws IOException {
    Sokeresultat sokeresultat =
        client.sok(Sokekriterier.med()
            .stillingstitler(Collections.singletonList("Progger"))
            .kompetanser(Collections.singletonList("Landtransport generelt"))
            .utdanninger(Collections.singletonList("Master i sikkerhet"))
            .bygg());

    List<EsCv> cver = sokeresultat.getCver();

    assertThat(cver.size()).isEqualTo(1);
    EsCv cv = cver.get(0);
    assertThat(cv).isEqualTo(transformer.transform(TempCvEventObjectMother.giveMeCvEvent5()));
  }

  @Test
  public void testFlereInputYrkeGirMindreTreff() throws IOException {
    Sokeresultat sokeresultat =
        client.sok(Sokekriterier.med()
            .stillingstitler(Collections.singletonList("Industrimekaniker"))
            .bygg());
    Sokeresultat sokeresultat2 =
        client.sok(Sokekriterier.med()
            .stillingstitler(Arrays.asList("Progger", "Industrimekaniker"))
            .bygg());

    List<EsCv> cver = sokeresultat.getCver();
    List<EsCv> cver2 = sokeresultat2.getCver();

    assertThat(cver.size()).isGreaterThan(cver2.size());
  }

  @Test
  public void testFlereInputKompetanseGirMindreTreff() throws IOException {
    Sokeresultat sokeresultat =
        client.sok(Sokekriterier.med()
            .kompetanser(Collections.singletonList("Programvareutvikler"))
            .bygg());
    Sokeresultat sokeresultat2 =
        client.sok(Sokekriterier.med()
            .kompetanser(Arrays.asList("Programvareutvikler", "Nyhetsanker"))
            .bygg());

    List<EsCv> cver = sokeresultat.getCver();
    List<EsCv> cver2 = sokeresultat2.getCver();

    assertThat(cver.size()).isGreaterThan(cver2.size());
  }

  @Test
  public void testFlereInputUtdanningGirMindreTreff() throws IOException {
    Sokeresultat sokeresultat =
        client.sok(Sokekriterier.med()
            .utdanninger(Collections.singletonList("Bygg og anlegg"))
            .bygg());
    Sokeresultat sokeresultat2 =
        client.sok(Sokekriterier.med()
            .utdanninger(Arrays.asList("Bygg og anlegg", "master i sikkerhet"))
            .bygg());

    List<EsCv> cver = sokeresultat.getCver();
    List<EsCv> cver2 = sokeresultat2.getCver();

    assertThat(cver.size()).isGreaterThan(cver2.size());
  }

  @Test
  public void testStemOrdSkalGiSammeResultat() throws IOException {
    Sokeresultat sokeresultat =
        client.sok(Sokekriterier.med()
            .stillingstitler(Collections.singletonList("Progger"))
            .bygg());
    Sokeresultat sokeresultatStemOrd =
        client.sok(Sokekriterier.med()
            .stillingstitler(Collections.singletonList("Progg"))
            .bygg());

    List<EsCv> cver = sokeresultat.getCver();
    List<EsCv> cverStemOrd = sokeresultatStemOrd.getCver();

    assertThat(cver.size()).isEqualTo(cverStemOrd.size());
    assertThat(cver.get(0)).isEqualTo(cverStemOrd.get(0));
  }

  @Test
  public void testSokPaStyrkKode() throws IOException {
    Sokeresultat sokeresultat =
        client.sok(Sokekriterier.med()
            .styrkKode("5684.05")
            .bygg());

    List<EsCv> cver = sokeresultat.getCver();
    EsCv cv = cver.get(0);
    assertThat(cv).isEqualTo(transformer.transform(TempCvEventObjectMother.giveMeCvEvent3()));
  }

  @Test
  public void testSokPaNusKode() throws IOException {
    Sokeresultat sokeresultat =
        client.sok(Sokekriterier.med()
            .nusKode("486595")
            .bygg());

    List<EsCv> cver = sokeresultat.getCver();
    EsCv cv = cver.get(0);
    assertThat(cv).isEqualTo(transformer.transform(TempCvEventObjectMother.giveMeCvEvent5()));
  }

  @Test
  public void testSokPaFlereStyrkKoderGirBegrensendeResultat() throws IOException {
    List<String> styrkKoder = new ArrayList<>();
    styrkKoder.add("5684.05");

    Sokeresultat sokeresultat =
        client.sok(Sokekriterier.med()
            .styrkKoder(styrkKoder)
            .bygg());

    styrkKoder.add("5124.46");

    Sokeresultat sokeresultatToKoder =
        client.sok(Sokekriterier.med()
            .styrkKoder(styrkKoder)
            .bygg());

    styrkKoder.add("5746.07");

    Sokeresultat sokeresultatTreKoder =
        client.sok(Sokekriterier.med()
            .styrkKoder(styrkKoder)
            .bygg());

    List<EsCv> cver = sokeresultat.getCver();
    List<EsCv> cver2 = sokeresultatToKoder.getCver();
    List<EsCv> cver3 = sokeresultatTreKoder.getCver();

    assertThat(cver.size()).isGreaterThan(cver2.size());
    assertThat(cver2.size()).isGreaterThan(cver3.size());
  }

  @Test
  public void testSokPaFlereNusKoderGirBegrensendeResultat() throws IOException {
    List<String> nusKoder = new ArrayList<>();
    nusKoder.add("296647");

    Sokeresultat sokeresultat =
        client.sok(Sokekriterier.med()
            .nusKoder(nusKoder)
            .bygg());

    nusKoder.add("456375");

    Sokeresultat sokeresultatToKoder =
        client.sok(Sokekriterier.med()
            .nusKoder(nusKoder)
            .bygg());

    List<EsCv> cver = sokeresultat.getCver();
    List<EsCv> cver2 = sokeresultatToKoder.getCver();

    assertThat(cver.size()).isGreaterThan(cver2.size());

  }

  @Test
  public void testSamletKompetanseSkalGiResultatVedSokPaSprak() throws IOException {
    Sokeresultat sokeresultat =
        client.sok(Sokekriterier.med()
            .kompetanser(Collections.singletonList("Dansk"))
            .bygg());

    List<EsCv> cver = sokeresultat.getCver();
    EsCv cv = cver.get(0);

    assertThat(cver.size()).isEqualTo(1);
    assertThat(cv).isEqualTo(transformer.transform(TempCvEventObjectMother.giveMeCvEvent5()));
  }

  @Test
  public void testSamletKompetanseSkalGiResultatVedSokPaSertifikater() throws IOException {
    Sokeresultat sokeresultat =
        client.sok(Sokekriterier.med()
            .kompetanser(Collections.singletonList("Truckførerbevis"))
            .bygg());

    List<EsCv> cver = sokeresultat.getCver();
    EsCv cv = cver.get(0);

    assertThat(cver.size()).isEqualTo(1);
    assertThat(cv).isEqualTo(transformer.transform(TempCvEventObjectMother.giveMeCvEvent3()));
  }

  @Test
  public void testSamletKompetanseSkalGiResultatVedSokPaForerkort() throws IOException {
    Sokeresultat sokeresultat =
        client.sok(Sokekriterier.med()
            .kompetanser(Collections.singletonList("Traktorlappen"))
            .bygg());

    List<EsCv> cver = sokeresultat.getCver();
    EsCv cv = cver.get(0);

    assertThat(cver.size()).isEqualTo(1);
    assertThat(cv).isEqualTo(transformer.transform(TempCvEventObjectMother.giveMeCvEvent3()));
  }

  @Test
  public void testSamletKompetanseSkalGiResultatVedSokPaKurs() throws IOException {
    Sokeresultat sokeresultat =
        client.sok(Sokekriterier.med()
            .kompetanser(Collections.singletonList("Spring Boot"))
            .bygg());

    List<EsCv> cver = sokeresultat.getCver();
    EsCv cv = cver.get(0);

    assertThat(cver.size()).isEqualTo(1);
    assertThat(cv).isEqualTo(transformer.transform(TempCvEventObjectMother.giveMeCvEvent5()));
  }

  @Test
  public void testSamletKompetanseSkalGiResultatVedSokPaKompetanse() throws IOException {
    Sokeresultat sokeresultat =
        client.sok(Sokekriterier.med()
            .kompetanser(Collections.singletonList("Javautvikler"))
            .bygg());

    List<EsCv> cver = sokeresultat.getCver();
    EsCv cv = cver.get(0);

    assertThat(cver.size()).isEqualTo(1);
    assertThat(cv).isEqualTo(transformer.transform(TempCvEventObjectMother.giveMeCvEvent2()));
  }

  @Test
  public void testSokPaFlereGeografiJobbonskerGirBegrensendeResultat() throws IOException {
    Sokeresultat sokeresultat =
        client.sok(Sokekriterier.med()
            .geografiList(Collections.singletonList("Oslo"))
            .bygg());
    Sokeresultat sokeresultat2 =
        client.sok(Sokekriterier.med()
            .geografiList(Arrays.asList("Oslo", "Harstad"))
            .bygg());

    List<EsCv> cver = sokeresultat.getCver();
    List<EsCv> cver2 = sokeresultat2.getCver();

    assertThat(cver.size()).isGreaterThan(cver2.size());
  }

  @Test
  public void testPaTotalYrkeserfaringSkalGiKorrektResultat() throws IOException {
    Sokeresultat sokeresultat =
        client.sok(Sokekriterier.med()
            .totalYrkeserfaring("37-72")
            .bygg());

    List<EsCv> cver = sokeresultat.getCver();
    EsCv cv = cver.get(0);

    assertThat(cv).isEqualTo(transformer.transform(TempCvEventObjectMother.giveMeCvEvent2()));
  }

}