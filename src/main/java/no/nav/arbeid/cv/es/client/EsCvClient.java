package no.nav.arbeid.cv.es.client;

import java.io.IOException;
import java.util.List;

import no.nav.arbeid.cv.es.domene.EsCv;
import no.nav.arbeid.cv.es.domene.Sokeresultat;

public interface EsCvClient {

  Sokeresultat sok(String fritekst, String stillingstittel, String kompetanse, String nusKode) throws IOException;

  Sokeresultat findByYrkeserfaringStyrkKodeTekst(String styrkBeskrivelse) throws IOException;

  void index(EsCv esCv) throws IOException;

  void createIndex() throws IOException;

  void deleteIndex() throws IOException;

  Sokeresultat findByEtternavnAndUtdanningNusKodeTekst(String etternavn, String utdanningNusKodeTekst) throws IOException;

  List<String> typeAheadKompetanse(String prefix) throws IOException;

  List<String> typeAheadUtdanning(String prefix) throws IOException;

  List<String> typeAheadYrkeserfaring(String prefix) throws IOException;
}
