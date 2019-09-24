package no.nav.arbeid.cv.kandidatsok.es.domene;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import no.nav.elasticsearch.mapping.annotations.ElasticKeywordField;
import no.nav.elasticsearch.mapping.annotations.ElasticTextField;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EsArbeidsdagerJobbonsker {

    @ElasticKeywordField
    private String arbeidsdagerKode;

    @ElasticTextField(analyzer = "norwegian")
    @ElasticKeywordField
    private String arbeidsdagerKodeTekst;

    public EsArbeidsdagerJobbonsker() {
    }

    public EsArbeidsdagerJobbonsker(String arbeidsdagerKode,
                                    String arbeidsdagerKodeTekst) {
        this.arbeidsdagerKode = arbeidsdagerKode;
        this.arbeidsdagerKodeTekst = arbeidsdagerKodeTekst;
    }

    public String getArbeidsdagerKode() {
        return arbeidsdagerKode;
    }

    public String getArbeidsdagerKodeTekst() {
        return arbeidsdagerKodeTekst;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EsArbeidsdagerJobbonsker that = (EsArbeidsdagerJobbonsker) o;
        return Objects.equals(arbeidsdagerKode, that.arbeidsdagerKode)
                && Objects.equals(arbeidsdagerKodeTekst, that.arbeidsdagerKodeTekst);
    }

    @Override
    public int hashCode() {

        return Objects.hash(arbeidsdagerKode, arbeidsdagerKodeTekst);
    }

    @Override
    public String toString() {
        return "EsArbeidstidsordningJobbonsker{" + "arbeidsdagerKode='" + arbeidsdagerKode
                + '\'' + ", arbeidsdagerKodeTekst='" + arbeidsdagerKodeTekst + '\'' + '}';
    }

}
