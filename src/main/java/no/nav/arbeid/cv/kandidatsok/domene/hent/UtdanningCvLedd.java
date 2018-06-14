package no.nav.arbeid.cv.kandidatsok.domene.hent;

import java.time.LocalDate;

public class UtdanningCvLedd {


  private String utdannelsessted;
  private String alternativtUtdanningsnavn;
  private String nusKode;
  private String nusKodeUtdanningsnavn;
  private LocalDate fraDato;
  private LocalDate tilDato;

  public UtdanningCvLedd() {}

  public UtdanningCvLedd(String utdannelsessted, String alternativtUtdanningsnavn, String nusKode,
      String nusKodeUtdanningsnavn, LocalDate fraDato, LocalDate tilDato) {
    super();
    this.utdannelsessted = utdannelsessted;
    this.alternativtUtdanningsnavn = alternativtUtdanningsnavn;
    this.nusKode = nusKode;
    this.nusKodeUtdanningsnavn = nusKodeUtdanningsnavn;
    this.fraDato = fraDato;
    this.tilDato = tilDato;
  }

  public String getUtdannelsessted() {
    return utdannelsessted;
  }

  public void setUtdannelsessted(String utdannelsessted) {
    this.utdannelsessted = utdannelsessted;
  }

  public String getAlternativtUtdanningsnavn() {
    return alternativtUtdanningsnavn;
  }

  public void setAlternativtUtdanningsnavn(String alternativtUtdanningsnavn) {
    this.alternativtUtdanningsnavn = alternativtUtdanningsnavn;
  }

  public String getNusKode() {
    return nusKode;
  }

  public void setNusKode(String nusKode) {
    this.nusKode = nusKode;
  }

  public String getNusKodeUtdanningsnavn() {
    return nusKodeUtdanningsnavn;
  }

  public void setNusKodeUtdanningsnavn(String nusKodeUtdanningsnavn) {
    this.nusKodeUtdanningsnavn = nusKodeUtdanningsnavn;
  }

  public LocalDate getFraDato() {
    return fraDato;
  }

  public void setFraDato(LocalDate fraDato) {
    this.fraDato = fraDato;
  }

  public LocalDate getTilDato() {
    return tilDato;
  }

  public void setTilDato(LocalDate tilDato) {
    this.tilDato = tilDato;
  }
}