package no.nav.arbeid.cv.kandidatsok.es.domene;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import no.nav.elasticsearch.mapping.annotations.ElasticCompletionField;
import no.nav.elasticsearch.mapping.annotations.ElasticKeywordField;
import no.nav.elasticsearch.mapping.annotations.ElasticTextField;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EsYrkeJobbonsker {

  @ElasticKeywordField
  private String styrkKode;

  @ElasticTextField(analyzer = "norwegian")
  @ElasticKeywordField
  @ElasticCompletionField
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  private String styrkBeskrivelse;

  private boolean primaertJobbonske;

  public EsYrkeJobbonsker() {}

  public EsYrkeJobbonsker(String styrkKode, String styrkBeskrivelse, boolean primaertJobbonske) {
    this.styrkKode = styrkKode;
    this.styrkBeskrivelse = styrkBeskrivelse;
    this.primaertJobbonske = primaertJobbonske;
  }

  public String getStyrkKode() {
    return styrkKode;
  }

  public String getStyrkBeskrivelse() {
    return styrkBeskrivelse;
  }

  public boolean isPrimaertJobbonske() {
    return primaertJobbonske;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EsYrkeJobbonsker that = (EsYrkeJobbonsker) o;
    return primaertJobbonske == that.primaertJobbonske && Objects.equals(styrkKode, that.styrkKode)
        && Objects.equals(styrkBeskrivelse, that.styrkBeskrivelse);
  }

  @Override
  public int hashCode() {

    return Objects.hash(styrkKode, styrkBeskrivelse, primaertJobbonske);
  }

  @Override
  public String toString() {
    return "EsYrkeJobbonsker{" + "styrkKode='" + styrkKode + '\'' + ", styrkBeskrivelse='"
        + styrkBeskrivelse + '\'' + ", primaertJobbonske=" + primaertJobbonske + '}';
  }
}
