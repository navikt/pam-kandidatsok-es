package no.nav.arbeid.cv.kandidatsok.es.domene;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EsGodkjenning {

    private String tittel;
    private String konsept_id;
    private String utsteder;
    private java.time.LocalDate gjennomfoert;
    private Long utloeper;



    public EsGodkjenning(String tittel, String konsept_id, String utsteder, LocalDate gjennomfoert, Long utloeper) {
        this.tittel = tittel;
        this.konsept_id = konsept_id;
        this.utsteder = utsteder;
        this.gjennomfoert = gjennomfoert;
        this.utloeper = utloeper;
    }

    public String getTittel() {
        return tittel;
    }

    public String getKonsept_id() {
        return konsept_id;
    }

    public String getUtsteder() {
        return utsteder;
    }

    public LocalDate getGjennomfoert() {
        return gjennomfoert;
    }

    public Long getUtloeper() {
        return utloeper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EsGodkjenning that = (EsGodkjenning) o;
        return Objects.equals(tittel, that.tittel) && Objects.equals(konsept_id, that.konsept_id) && Objects.equals(utsteder, that.utsteder) && Objects.equals(gjennomfoert, that.gjennomfoert) && Objects.equals(utloeper, that.utloeper);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tittel, konsept_id, utsteder, gjennomfoert, utloeper);
    }

    @Override
    public String toString() {
        return "EsGodkjenning{" +
                "tittel='" + tittel + '\'' +
                ", konsept_id='" + konsept_id + '\'' +
                ", utsteder='" + utsteder + '\'' +
                ", gjennomfoert=" + gjennomfoert +
                ", utloeper=" + utloeper +
                '}';
    }

}
