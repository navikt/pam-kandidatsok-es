package no.nav.arbeid.cv.kandidatsok.es.domene;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import no.nav.elasticsearch.mapping.annotations.ElasticBooleanField;
import no.nav.elasticsearch.mapping.annotations.ElasticDateField;
import no.nav.elasticsearch.mapping.annotations.ElasticDocument;
import no.nav.elasticsearch.mapping.annotations.ElasticIntegerField;
import no.nav.elasticsearch.mapping.annotations.ElasticKeywordField;
import no.nav.elasticsearch.mapping.annotations.ElasticLongField;
import no.nav.elasticsearch.mapping.annotations.ElasticNestedField;
import no.nav.elasticsearch.mapping.annotations.ElasticTextField;

@JsonIgnoreProperties(ignoreUnknown = true)
@ElasticDocument
public class EsCv {

    // Define friktest to add norwegian analyzer
    // Kan fjernes hvis fritekst-sok ikke brukes
    @ElasticTextField(analyzer = "norwegian")
    private String fritekst;

    @ElasticTextField
    private String fodselsnummer;

    @ElasticTextField
    private String fornavn;

    @ElasticTextField
    private String etternavn;

    @ElasticDateField
    private Date fodselsdato;

    @ElasticBooleanField
    private Boolean fodselsdatoErDnr;

    @ElasticKeywordField
    private String formidlingsgruppekode;

    @ElasticKeywordField
    private String epostadresse;

    @ElasticKeywordField
    private String mobiltelefon;

    @ElasticBooleanField
    private boolean harKontaktinformasjon;
    
    @ElasticKeywordField
    private String telefon;

    @ElasticKeywordField
    private String statsborgerskap;

    @ElasticKeywordField
    private String kandidatnr;

    @ElasticTextField(copyTo = "fritekst", analyzer = "norwegian")
    private String beskrivelse;

    @ElasticKeywordField
    private String samtykkeStatus;

    @ElasticDateField
    private Date samtykkeDato;

    @ElasticTextField
    private String adresselinje1;

    @ElasticTextField
    private String adresselinje2;

    @ElasticTextField
    private String adresselinje3;

    @ElasticKeywordField
    private String postnummer;

    @ElasticKeywordField
    private String poststed;

    @ElasticKeywordField
    private String landkode;

    @ElasticLongField
    private Integer kommunenummer;

    @ElasticKeywordField
    private Integer kommunenummerkw;

    @ElasticBooleanField
    private Boolean disponererBil;

    @ElasticDateField
    private Date tidsstempel;
    
    @ElasticBooleanField
    private Boolean doed;
    
    @ElasticKeywordField
    private String frKode;
    
    @ElasticKeywordField
    private String kvalifiseringsgruppekode;
 
    @ElasticKeywordField
    private String hovedmaalkode;
    
    @ElasticTextField
    private String orgenhet;
    
    @ElasticBooleanField
    private Boolean fritattKandidatsok;
    
    @ElasticBooleanField
    private Boolean fritattAgKandidatsok;
    
    @ElasticNestedField
    private List<EsUtdanning> utdanning = new ArrayList<>();

    @ElasticNestedField
    private List<EsYrkeserfaring> yrkeserfaring = new ArrayList<>();

    @ElasticNestedField
    private List<EsKompetanse> kompetanse = new ArrayList<>();

    @ElasticNestedField
    private List<EsAnnenErfaring> annenerfaring = new ArrayList<>();

    @ElasticNestedField
    private List<EsSertifikat> sertifikat = new ArrayList<>();

    @ElasticNestedField
    private List<EsForerkort> forerkort = new ArrayList<>();

    @ElasticNestedField
    private List<EsSprak> sprak = new ArrayList<>();

    @ElasticNestedField
    private List<EsKurs> kurs = new ArrayList<>();

    @ElasticNestedField
    private List<EsVerv> verv = new ArrayList<>();

    @ElasticNestedField
    private List<EsGeografiJobbonsker> geografiJobbonsker = new ArrayList<>();

    @ElasticNestedField
    private List<EsYrkeJobbonsker> yrkeJobbonsker = new ArrayList<>();

    @ElasticNestedField
    private List<EsOmfangJobbonsker> omfangJobbonsker = new ArrayList<>();

    @ElasticNestedField
    private List<EsAnsettelsesformJobbonsker> ansettelsesformJobbonsker = new ArrayList<>();
    
    @ElasticNestedField
    private List<EsArbeidstidsordningJobbonsker> arbeidstidsordningJobbonsker = new ArrayList<>();

    @ElasticNestedField
    private List<EsArbeidsdagerJobbonsker> arbeidsdagerJobbonsker = new ArrayList<>();

    @ElasticNestedField
    private List<EsArbeidstidJobbonsker> arbeidstidJobbonsker = new ArrayList<>();

    @ElasticNestedField
    private List<EsSamletKompetanse> samletKompetanse = new ArrayList<>();

    @ElasticIntegerField
    private int totalLengdeYrkeserfaring;


    public EsCv() {

    }

    public EsCv(String fodselsnummer, String fornavn, String etternavn, Date fodselsdato,
            Boolean fodselsdatoErDnr, String formidlingsgruppekode, String epostadresse,
            String mobiltelefon, String telefon, String statsborgerskap, String arenaKandidatnr, String beskrivelse,
            String samtykkeStatus, Date samtykkeDato, String adresselinje1, String adresselinje2,
            String adresselinje3, String postnummer, String poststed, String landkode,
            Integer kommunenummer, Boolean disponererBil, Date tidsstempel, Integer kommunenummerkw,
            Boolean doed, String frKode, String kvalifiseringsgruppekode, String hovedmaalkode, String orgenhet,
            Boolean fritattKandidatsok, Boolean fritattAgKandidatsok) {
        this.fodselsnummer = fodselsnummer;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.fodselsdato = fodselsdato;
        this.fodselsdatoErDnr = fodselsdatoErDnr;
        this.formidlingsgruppekode = formidlingsgruppekode;
        this.epostadresse = epostadresse;
        this.mobiltelefon = mobiltelefon;
        this.telefon = telefon;
        this.harKontaktinformasjon = !StringUtils.isAllBlank(this.epostadresse, this.mobiltelefon, this.telefon);        
        this.statsborgerskap = statsborgerskap;
        this.kandidatnr = arenaKandidatnr;
        this.beskrivelse = beskrivelse;
        this.samtykkeStatus = samtykkeStatus;
        this.samtykkeDato = samtykkeDato;
        this.adresselinje1 = adresselinje1;
        this.adresselinje2 = adresselinje2;
        this.adresselinje3 = adresselinje3;
        this.postnummer = postnummer;
        this.poststed = poststed;
        this.landkode = landkode;
        this.kommunenummer = kommunenummer;
        this.disponererBil = disponererBil;
        this.tidsstempel = tidsstempel;
        this.kommunenummerkw = kommunenummerkw;
        this.doed = doed;
        this.frKode = frKode;
        this.kvalifiseringsgruppekode = kvalifiseringsgruppekode;
        this.hovedmaalkode = hovedmaalkode;
        this.orgenhet = orgenhet;
        this.fritattKandidatsok = fritattKandidatsok;
        this.fritattAgKandidatsok = fritattAgKandidatsok;
    }

    // Adderfunksjoner
    public void addUtdanning(EsUtdanning utdanning) {
        this.utdanning.add(utdanning);
    }

    public void addUtdanning(Collection<EsUtdanning> utdanningListe) {
        this.utdanning.addAll(utdanningListe);
    }

    public void addYrkeserfaring(EsYrkeserfaring yrkeserfaring) {
        this.yrkeserfaring.add(yrkeserfaring);
    }

    public void addYrkeserfaring(Collection<EsYrkeserfaring> yrkeserfaringListe) {
        yrkeserfaringListe
                .forEach(y -> this.totalLengdeYrkeserfaring += y.getYrkeserfaringManeder());
        this.yrkeserfaring.addAll(yrkeserfaringListe);
    }

    public void addKompetanse(EsKompetanse kompetanse) {
        if (kompetanse != null) {
            this.kompetanse.add(kompetanse);
            this.addSamletKompetanse(Collections
                    .singletonList(new EsSamletKompetanse(kompetanse.getKompKodeNavn())));
        }
    }

    public void addKompetanse(Collection<EsKompetanse> kompetanseListe) {
        if (kompetanseListe != null) {
            this.kompetanse.addAll(kompetanseListe);
            this.addSamletKompetanse(
                    kompetanseListe.stream().map(k -> new EsSamletKompetanse(k.getKompKodeNavn()))
                            .collect(Collectors.toList()));
        }
    }

    public void addAnnenErfaring(EsAnnenErfaring annenErfaring) {
        this.annenerfaring.add(annenErfaring);
    }

    public void addAnnenErfaring(Collection<EsAnnenErfaring> annenErfaringListe) {
        this.annenerfaring.addAll(annenErfaringListe);
    }

    public void addSertifikat(EsSertifikat sertifikat) {
        if (sertifikat != null) {
            this.sertifikat.add(sertifikat);
            this.addSamletKompetanse(Collections
                    .singletonList(new EsSamletKompetanse(sertifikat.getSertifikatKodeNavn())));
        }
    }

    public void addSertifikat(Collection<EsSertifikat> sertifikatListe) {
        if (sertifikatListe != null) {
            this.sertifikat.addAll(sertifikatListe);
            this.addSamletKompetanse(sertifikatListe.stream()
                    .map(k -> new EsSamletKompetanse(k.getSertifikatKodeNavn()))
                    .collect(Collectors.toList()));
        }
    }

    public void addForerkort(EsForerkort forerkort) {
        if (forerkort != null) {
            this.forerkort.add(forerkort);
            this.addSamletKompetanse(Collections
                    .singletonList(new EsSamletKompetanse(forerkort.getForerkortKodeKlasse())));
        }
    }

    public void addForerkort(Collection<EsForerkort> forerkortListe) {
        if (forerkortListe != null) {
            this.forerkort.addAll(forerkortListe);
        }
    }

    public void addSprak(EsSprak sprak) {
        if (sprak != null) {
            this.sprak.add(sprak);
        }
    }

    public void addSprak(Collection<EsSprak> sprakListe) {
        if (sprakListe != null) {
            this.sprak.addAll(sprakListe);
        }
    }

    public void addKurs(EsKurs kurs) {
        if (kurs != null) {
            this.kurs.add(kurs);
//            if (StringUtils.isNotBlank(kurs.getTittel())) {
//                this.addSamletKompetanse(
//                        Collections.singletonList(new EsSamletKompetanse(kurs.getTittel())));
//            }
        }
    }

    public void addKurs(Collection<EsKurs> kursListe) {
        if (kursListe != null) {
            this.kurs.addAll(kursListe);
            // this.addSamletKompetanse(kursListe.stream().map(s -> s.getTittel())
            // .filter(t -> StringUtils.isNotBlank(t)).map(t -> new EsSamletKompetanse(t))
            // .collect(Collectors.toList()));
        }
    }

    public void addVerv(EsVerv verv) {
        this.verv.add(verv);
    }

    public void addVerv(Collection<EsVerv> vervListe) {
        this.verv.addAll(vervListe);
    }

    public void addGeografiJobbonske(Collection<EsGeografiJobbonsker> geografiJobbonskerListe) {
        this.geografiJobbonsker.addAll(geografiJobbonskerListe);
    }

    public void addYrkeJobbonske(Collection<EsYrkeJobbonsker> yrkeJobbonskerListe) {
        this.yrkeJobbonsker.addAll(yrkeJobbonskerListe);
    }

    public void addOmfangJobbonske(Collection<EsOmfangJobbonsker> omfangJobbonsker){
        this.omfangJobbonsker.addAll(omfangJobbonsker);
    }

    public void addAnsettelsesformJobbonske(Collection<EsAnsettelsesformJobbonsker> ansettelsesformJobbonsker){
        this.ansettelsesformJobbonsker.addAll(ansettelsesformJobbonsker);
    }

    public void addArbeidstidsordningJobbonsker(Collection<EsArbeidstidsordningJobbonsker> arbeidstidsordningJobbonsker){
        this.arbeidstidsordningJobbonsker.addAll(arbeidstidsordningJobbonsker);
    }

    public void addArbeidstidJobbonsker(Collection<EsArbeidstidJobbonsker> arbeidstidJobbonsker){
        this.arbeidstidJobbonsker.addAll(arbeidstidJobbonsker);
    }

    public void addArbeidsdagerJobbonsker(Collection<EsArbeidsdagerJobbonsker> arbeidsdagerJobbonsker){
        this.arbeidsdagerJobbonsker.addAll(arbeidsdagerJobbonsker);
    }

    private void addSamletKompetanse(Collection<EsSamletKompetanse> samletKompetanseListe) {
        this.samletKompetanse.addAll(samletKompetanseListe);
    }

    // gettere

    public String getFodselsnummer() {
        return fodselsnummer;
    }

    public String getFornavn() {
        return fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public Date getFodselsdato() {
        return fodselsdato;
    }

    public Boolean getFodselsdatoErDnr() {
        return fodselsdatoErDnr;
    }

    public String getFormidlingsgruppekode() {
        return formidlingsgruppekode;
    }
    
    public Boolean isDoed() {
        return doed;
    }
    
    public Boolean isFritattKandidatsok() {
        return fritattKandidatsok;
    }
    
    public Boolean isFritattAgKandidatsok() {
        return fritattAgKandidatsok;
    }
    
    public String getFrKode() {
        return frKode;
    }
    
    public String getKvalifiseringsgruppekode() {
        return kvalifiseringsgruppekode;
    }
    
    public String getHovedmaalkode() {
        return hovedmaalkode;
    }
    
    public String getOrgenhet() {
        return orgenhet;
    }

    public String getEpostadresse() {
        return epostadresse;
    }

    public String getMobiltelefon() {
        return mobiltelefon;
    }

    public String getTelefon() {
        return telefon;
    }
    
    public boolean isHarKontaktinformasjon() {
        return harKontaktinformasjon;
    }

    public String getStatsborgerskap() {
        return statsborgerskap;
    }

    public String getKandidatnr() {
        return kandidatnr;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public String getSamtykkeStatus() {
        return samtykkeStatus;
    }

    public Date getSamtykkeDato() {
        return samtykkeDato;
    }

    public String getAdresselinje1() {
        return adresselinje1;
    }

    public String getAdresselinje2() {
        return adresselinje2;
    }

    public String getAdresselinje3() {
        return adresselinje3;
    }

    public String getPostnummer() {
        return postnummer;
    }

    public String getPoststed() {
        return poststed;
    }

    public String getLandkode() {
        return landkode;
    }

    public Integer getKommunenummer() {
        return kommunenummer;
    }

    public Integer getKommunenummerkw() {
        return kommunenummerkw;
    }

    public Boolean getDisponererBil() {
        return disponererBil;
    }

    public Date getTidsstempel() {
        return tidsstempel;
    }

    public List<EsUtdanning> getUtdanning() {
        return utdanning;
    }

    public List<EsYrkeserfaring> getYrkeserfaring() {
        return yrkeserfaring;
    }

    public List<EsKompetanse> getKompetanse() {
        return kompetanse;
    }

    public List<EsAnnenErfaring> getAnnenerfaring() {
        return annenerfaring;
    }

    public List<EsSertifikat> getSertifikat() {
        return sertifikat;
    }

    public List<EsForerkort> getForerkort() {
        return forerkort;
    }

    public List<EsSprak> getSprak() {
        return sprak;
    }

    public List<EsKurs> getKurs() {
        return kurs;
    }

    public List<EsVerv> getVerv() {
        return verv;
    }

    public List<EsGeografiJobbonsker> getGeografiJobbonsker() {
        return geografiJobbonsker;
    }

    public List<EsYrkeJobbonsker> getYrkeJobbonsker() {
        return yrkeJobbonsker;
    }

    public List<EsOmfangJobbonsker> getOmfangJobbonsker() {
        return omfangJobbonsker;
    }

    public List<EsAnsettelsesformJobbonsker> getAnsettelsesformJobbonsker() {
        return ansettelsesformJobbonsker;
    }

    public List<EsArbeidstidsordningJobbonsker> getArbeidstidsordningJobbonsker() {
        return arbeidstidsordningJobbonsker;
    }

    public List<EsArbeidstidJobbonsker> getArbeidstidJobbonsker() {
        return arbeidstidJobbonsker;
    }

    public List<EsArbeidsdagerJobbonsker> getArbeidsdagerJobbonsker() {
        return arbeidsdagerJobbonsker;
    }

    public List<EsSamletKompetanse> getSamletKompetanse() {
        return samletKompetanse;
    }

    public int getTotalLengdeYrkeserfaring() {
        return totalLengdeYrkeserfaring;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EsCv esCv = (EsCv) o;
        return Objects.equals(fodselsnummer, esCv.fodselsnummer)
                && Objects.equals(fornavn, esCv.fornavn)
                && Objects.equals(etternavn, esCv.etternavn)
                && Objects.equals(fodselsdato, esCv.fodselsdato)
                && Objects.equals(fodselsdatoErDnr, esCv.fodselsdatoErDnr)
                && Objects.equals(doed, esCv.doed)
                && Objects.equals(frKode, esCv.frKode)
                && Objects.equals(kvalifiseringsgruppekode, esCv.kvalifiseringsgruppekode)
                && Objects.equals(hovedmaalkode, esCv.hovedmaalkode)
                && Objects.equals(orgenhet, esCv.orgenhet)
                && Objects.equals(fritattKandidatsok, esCv.fritattKandidatsok)
                && Objects.equals(fritattAgKandidatsok, esCv.fritattAgKandidatsok)                
                && Objects.equals(epostadresse, esCv.epostadresse)
                && Objects.equals(mobiltelefon, esCv.mobiltelefon)
                && Objects.equals(telefon, esCv.telefon)
                && Objects.equals(statsborgerskap, esCv.statsborgerskap)
                && Objects.equals(kandidatnr, esCv.kandidatnr)
                && Objects.equals(beskrivelse, esCv.beskrivelse)
                && Objects.equals(samtykkeStatus, esCv.samtykkeStatus)
                && Objects.equals(samtykkeDato, esCv.samtykkeDato)
                && Objects.equals(adresselinje1, esCv.adresselinje1)
                && Objects.equals(adresselinje2, esCv.adresselinje2)
                && Objects.equals(adresselinje3, esCv.adresselinje3)
                && Objects.equals(postnummer, esCv.postnummer)
                && Objects.equals(poststed, esCv.poststed)
                && Objects.equals(landkode, esCv.landkode)
                && Objects.equals(kommunenummer, esCv.kommunenummer)
                && Objects.equals(kommunenummerkw, esCv.kommunenummerkw)
                && Objects.equals(disponererBil, esCv.disponererBil)
                && Objects.equals(tidsstempel, esCv.tidsstempel)
                && Objects.equals(utdanning, esCv.utdanning)
                && Objects.equals(yrkeserfaring, esCv.yrkeserfaring)
                && Objects.equals(kompetanse, esCv.kompetanse)
                && Objects.equals(annenerfaring, esCv.annenerfaring)
                && Objects.equals(sertifikat, esCv.sertifikat)
                && Objects.equals(forerkort, esCv.forerkort) && Objects.equals(sprak, esCv.sprak)
                && Objects.equals(kurs, esCv.kurs) && Objects.equals(verv, esCv.verv)
                && Objects.equals(geografiJobbonsker, esCv.geografiJobbonsker)
                && Objects.equals(yrkeJobbonsker, esCv.yrkeJobbonsker)
                && Objects.equals(omfangJobbonsker, esCv.omfangJobbonsker)
                && Objects.equals(ansettelsesformJobbonsker, esCv.ansettelsesformJobbonsker)
                && Objects.equals(arbeidstidsordningJobbonsker, esCv.arbeidstidsordningJobbonsker)
                && Objects.equals(arbeidstidJobbonsker, esCv.arbeidstidJobbonsker)
                && Objects.equals(arbeidsdagerJobbonsker, esCv.arbeidsdagerJobbonsker)
                && Objects.equals(samletKompetanse, esCv.samletKompetanse)
                && Objects.equals(totalLengdeYrkeserfaring, esCv.totalLengdeYrkeserfaring);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fodselsnummer, fornavn, etternavn, fodselsdato, fodselsdatoErDnr,
                formidlingsgruppekode, doed, frKode, kvalifiseringsgruppekode, hovedmaalkode, orgenhet, fritattKandidatsok, fritattAgKandidatsok, epostadresse, mobiltelefon, telefon, statsborgerskap,
                kandidatnr, beskrivelse, samtykkeStatus, samtykkeDato, adresselinje1,
                adresselinje2, adresselinje3, postnummer, poststed, landkode, kommunenummer, kommunenummerkw,
                disponererBil, tidsstempel, utdanning, yrkeserfaring, kompetanse, annenerfaring,
                sertifikat, forerkort, sprak, kurs, verv, geografiJobbonsker, yrkeJobbonsker,
                omfangJobbonsker, ansettelsesformJobbonsker, arbeidstidsordningJobbonsker,
                arbeidstidJobbonsker, arbeidsdagerJobbonsker, samletKompetanse, totalLengdeYrkeserfaring);
    }

    @Override
    public String toString() {
        return "EsCv [fritekst=" + fritekst + ", fodselsnummer=" + fodselsnummer + ", fornavn="
                + fornavn + ", etternavn=" + etternavn + ", fodselsdato=" + fodselsdato
                + ", fodselsdatoErDnr=" + fodselsdatoErDnr + ", formidlingsgruppekode="
                + formidlingsgruppekode + ", epostadresse=" + epostadresse + ", mobiltelefon="
                + mobiltelefon + ", telefon=" + telefon + ", statsborgerskap=" + statsborgerskap
                + ", arenaKandidatnr=" + kandidatnr
                + ", beskrivelse=" + beskrivelse + ", samtykkeStatus=" + samtykkeStatus
                + ", samtykkeDato=" + samtykkeDato + ", adresselinje1=" + adresselinje1
                + ", adresselinje2=" + adresselinje2 + ", adresselinje3=" + adresselinje3
                + ", postnummer=" + postnummer + ", poststed=" + poststed + ", landkode=" + landkode
                + ", kommunenummer=" + kommunenummer + ", kommunenummerkw=" + kommunenummerkw
                + ", disponererBil=" + disponererBil + ", tidsstempel=" + tidsstempel + ", doed="
                + doed + ", frKode=" + frKode + ", kvalifiseringsgruppekode="
                + kvalifiseringsgruppekode + ", hovedmaalkode=" + hovedmaalkode + ", orgenhet="
                + orgenhet + ", fritattKandidatsok=" + fritattKandidatsok
                + ", fritattAgKandidatsok=" + fritattAgKandidatsok + ", utdanning=" + utdanning
                + ", yrkeserfaring=" + yrkeserfaring + ", kompetanse=" + kompetanse
                + ", annenerfaring=" + annenerfaring + ", sertifikat=" + sertifikat + ", forerkort="
                + forerkort + ", sprak=" + sprak + ", kurs=" + kurs + ", verv=" + verv
                + ", geografiJobbonsker=" + geografiJobbonsker + ", yrkeJobbonsker="
                + yrkeJobbonsker + ", omfangJobbonsker=" + omfangJobbonsker
                + ", ansettelsesformJobbonsker=" + ansettelsesformJobbonsker
                + ", arbeidstidsordningJobbonsker=" + arbeidstidsordningJobbonsker
                + ", arbeidsdagerJobbonsker=" + arbeidsdagerJobbonsker + ", arbeidstidJobbonsker="
                + arbeidstidJobbonsker + ", samletKompetanse=" + samletKompetanse
                + ", totalLengdeYrkeserfaring=" + totalLengdeYrkeserfaring + "]";
    }

    public void setKandidatnr(String nyArenaKandidatnr) {
        this.kandidatnr = nyArenaKandidatnr;
    }

}
