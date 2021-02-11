package no.nav.arbeid.kandidatsok.es.client;

import no.nav.arbeid.cv.kandidatsok.es.domene.EsCv;

import java.util.Collection;
import java.util.List;

/**
 * Tjeneste-grensesnitt for oppdatering av ElasticSearch CV-indeks.
 */
public interface EsIndexerService {

    /**
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException ved feil mot ES
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.ApplicationException ved feil med JSON-serialisering
     */
    void index(EsCv esCv, String indexName);

    /**
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException ved feil mot ES
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.ApplicationException ved feil med JSON-serialisering
     */
    int bulkIndex(List<EsCv> esCver, String indexName);

    /**
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException ved feil mot ES
     * @return antall slettede kandidat-docs faktisk slettet fra ES
     */
    int bulkSlettKandidatnr(List<String> kandidatnr, String indexName);

    /**
     * Slett basert på aktør-id-er
     * @param aktorId liste med aktør-id-er
     * @param indexName navn på målindeks
     * @return antall dokumenter faktisk slettet fra ElasticSearch
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException ved feil mot ES
     */
    int bulkSlettAktorId(List<String> aktorId, String indexName);

    /**
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException ved feil mot ES
     */
    void createIndex(String indexName);

    /**
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException ved feil mot ES
     */
    void deleteIndex(String indexName);

    /**
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException ved feil mot ES
     */
    boolean doesIndexExist(String indexName);

    /**
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException ved feil mot ES
     */
    long antallIndeksert(String indexName);

    /**
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException ved feil mot ES
     */
    long antallIndeksertSynligForVeileder(String indexName);

    /**
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException ved feil mot ES
     */
    long antallIndeksertSynligForArbeidsgiver(String indexName);

    /**
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException ved feil mot ES
     */
    Collection<String> getTargetsForAlias(String alias, String indexPattern);

    /**
     * @throws no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException ved feil mot ES
     */
    boolean updateIndexAlias(String alias, String removeForIndexPattern, String addForIndexName);

}
