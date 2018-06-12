package no.nav.arbeid.cv.kandidatsok.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.client.RestTemplate;

import no.nav.arbeid.cv.kandidatsok.altinn.AltinnGateway;
import no.nav.arbeid.cv.kandidatsok.config.components.AltinnEnvConf;
import no.nav.arbeid.cv.kandidatsok.config.components.CorsInterceptor;
import no.nav.arbeid.cv.kandidatsok.service.ArbeidsgiverService;
import no.nav.security.oidc.context.OIDCRequestContextHolder;
import no.nav.security.spring.oidc.validation.api.EnableOIDCTokenValidation;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableOIDCTokenValidation(ignore = "org.springframework")
@Import(ApiConfiguration.class)
public class MethodSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private OIDCRequestContextHolder oidcRequestContextHolder;

  @Value("${no.nav.arbeid.api.allowed.origins:http://localhost:9009,https://pam-cv-indexer.nais.oera-q.local,https://pam-cv-indexer-q.nav.no,https://pam-cv-indexer.nav.no}")
  private String[] allowedOrigins;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().anyRequest().permitAll();
  }

  @Bean
  public CorsInterceptor corsInterceptor() {
    return new CorsInterceptor(allowedOrigins);
  }

  @Bean
  public AltinnEnvConf altinnEnvConf() {
    return new AltinnEnvConf();
  }

  @Bean
  public AltinnGateway altinnService() {
    return new AltinnGateway(restTemplate, altinnEnvConf());
  }

  @Bean
  public ArbeidsgiverService arbeidsgiverService() {
    return new ArbeidsgiverService(altinnService(), oidcRequestContextHolder);
  }

}
