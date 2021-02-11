package no.nav.arbeid.kandidatsok.es.client;

import no.nav.arbeid.cv.kandidatsok.es.domene.EsCv;
import no.nav.arbeid.cv.kandidatsok.es.domene.sok.Sokekriterier;
import no.nav.arbeid.cv.kandidatsok.es.domene.sok.SokekriterierVeiledere;
import no.nav.arbeid.cv.kandidatsok.es.domene.sok.Sokeresultat;

import java.util.List;
import java.util.Optional;

public interface EsSokService {

    /**
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException ved feil mot ES
     */
    Sokeresultat arbeidsgiverSok(Sokekriterier sokekriterier);

    /**
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException ved feil mot ES
     */
    Sokeresultat veilederSok(SokekriterierVeiledere sokekriterier);

    /**
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException ved feil mot ES
     */
    List<String> typeAheadKompetanse(String prefix);

    /**
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException ved feil mot ES
     */
    List<String> typeAheadUtdanning(String prefix);

    /**
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException ved feil mot ES
     */
    List<String> typeAheadYrkeserfaring(String prefix);

    /**
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException ved feil mot ES
     */
    List<String> typeAheadGeografi(String prefix);

    /**
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException ved feil mot ES
     */
    List<String> typeAheadYrkeJobbonsker(String prefix);

    /**
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException ved feil mot ES
     */
    List<String> typeAheadSprak(String prefix);

    /**
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException ved feil mot ES
     */
    List<String> typeAheadNavkontor(String searchTerm);

    /**
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException ved feil mot ES
     */
    Optional<EsCv> arbeidsgiverHent(String kandidatnr);

    /**
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException ved feil mot ES
     */
    Optional<EsCv> veilederHent(String kandidatnr);

    /**
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException ved feil mot ES
     */
    Sokeresultat arbeidsgiverHentKandidater(List<String> kandidatnummer);

    /**
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException ved feil mot ES
     */
    Sokeresultat arbeidsgiverHentKandidaterForVisning(List<String> kandidatnummer);

    /**
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException ved feil mot ES
     */
    Sokeresultat veilederHentKandidater(List<String> kandidatnummer);

    /**
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException ved feil mot ES
     */
    Optional<no.nav.arbeid.cv.kandidatsok.es.domene.sok.EsCv> veilederSokPaaFnr(String fnr);

}
