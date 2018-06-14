package no.nav.arbeid.cv.kandidatsok.domene.hent;

public class ArboPersonProfilLedd {

  private String arbeidstidsordningKode;
  private String arbeidstidsordningKodeTekst;

  public ArboPersonProfilLedd() {}

  public String getArbeidstidsordningKode() {
    return arbeidstidsordningKode;
  }

  public ArboPersonProfilLedd(String arbeidstidsordningKode, String arbeidstidsordningKodeTekst) {
    super();
    this.arbeidstidsordningKode = arbeidstidsordningKode;
    this.arbeidstidsordningKodeTekst = arbeidstidsordningKodeTekst;
  }

  public void setArbeidstidsordningKode(String arbeidstidsordningKode) {
    this.arbeidstidsordningKode = arbeidstidsordningKode;
  }

  public String getArbeidstidsordningKodeTekst() {
    return arbeidstidsordningKodeTekst;
  }

  public void setArbeidstidsordningKodeTekst(String arbeidstidsordningKodeTekst) {
    this.arbeidstidsordningKodeTekst = arbeidstidsordningKodeTekst;
  }
}