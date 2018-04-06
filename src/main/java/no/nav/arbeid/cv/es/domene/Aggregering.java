package no.nav.arbeid.cv.es.domene;

import java.util.ArrayList;
import java.util.List;

public class Aggregering {

  private String navn;

  private List<Aggregeringsfelt> felt = new ArrayList<>();

  public Aggregering(String navn) {
    this.navn = navn;
  }

  public Aggregering(String navn, List<Aggregeringsfelt> felt) {
    this.navn = navn;
    this.felt = felt;
  }

  public String getNavn() {
    return navn;
  }

  public List<Aggregeringsfelt> getFelt() {
    return felt;
  }

  public void addFelt(String feltnavn, Long antall) {
    this.felt.add(new Aggregeringsfelt(feltnavn, antall));
  }
}
