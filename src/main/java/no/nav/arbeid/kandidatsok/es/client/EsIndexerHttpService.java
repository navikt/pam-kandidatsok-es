package no.nav.arbeid.kandidatsok.es.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import no.nav.arbeid.cv.indexer.config.StaticMappingProvider;
import no.nav.arbeid.cv.kandidatsok.es.domene.EsCv;
import no.nav.arbeid.cv.kandidatsok.es.exception.ApplicationException;
import no.nav.arbeid.cv.kandidatsok.es.exception.OperationalException;
import org.opensearch.OpenSearchStatusException;
import org.opensearch.action.DocWriteRequest;
import org.opensearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.opensearch.action.admin.indices.alias.IndicesAliasesRequest.AliasActions;
import org.opensearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.opensearch.action.admin.indices.delete.DeleteIndexRequest;
import org.opensearch.action.admin.indices.refresh.RefreshRequest;
import org.opensearch.action.bulk.BulkItemResponse;
import org.opensearch.action.bulk.BulkRequest;
import org.opensearch.action.bulk.BulkResponse;
import org.opensearch.action.index.IndexRequest;
import org.opensearch.action.index.IndexResponse;
import org.opensearch.action.support.WriteRequest;
import org.opensearch.action.support.master.AcknowledgedResponse;
import org.opensearch.client.GetAliasesResponse;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.Requests;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.client.core.CountRequest;
import org.opensearch.client.core.CountResponse;
import org.opensearch.client.indices.CreateIndexRequest;
import org.opensearch.client.indices.CreateIndexResponse;
import org.opensearch.client.indices.GetIndexRequest;
import org.opensearch.common.Strings;
import org.opensearch.common.unit.TimeValue;
import org.opensearch.common.xcontent.XContentFactory;
import org.opensearch.common.xcontent.XContentType;
import org.opensearch.index.query.BoolQueryBuilder;
import org.opensearch.index.query.QueryBuilders;
import org.opensearch.index.reindex.BulkByScrollResponse;
import org.opensearch.index.reindex.DeleteByQueryRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

public class EsIndexerHttpService implements EsIndexerService, AutoCloseable {

    private static final Logger LOGGER = LoggerFactory.getLogger(EsIndexerHttpService.class);

    private final RestHighLevelClient client;
    private final ObjectMapper mapper;
    private final MeterRegistry meterRegistry;
    private final int numberOfShards;
    private final int numberOfReplicas;

    private WriteRequest.RefreshPolicy refreshPolicy;

    public EsIndexerHttpService(RestHighLevelClient client, ObjectMapper objectMapper,
                                MeterRegistry meterRegistry, WriteRequest.RefreshPolicy refreshPolicy,
                                int numberOfShards, int numberOfReplicas) {
        this.client = client;
        this.mapper = objectMapper;
        this.meterRegistry = meterRegistry;
        this.refreshPolicy = refreshPolicy != null ? refreshPolicy : WriteRequest.RefreshPolicy.NONE;
        this.numberOfShards = numberOfShards;
        this.numberOfReplicas = numberOfReplicas;
    }

    @Override
    public void createIndex(String indexName) {
        try {
            CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);

            final Map<String, Object> settings = StaticMappingProvider.cvSettings();
            final Map<String, Object> indexSettings = (Map<String, Object>) settings.computeIfAbsent("index", k -> new HashMap<String, Object>());
            indexSettings.put("number_of_shards", numberOfShards);
            indexSettings.put("number_of_replicas", numberOfReplicas);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Settings: {}", Strings.toString(XContentFactory.contentBuilder(XContentType.JSON).map(settings).map(settings)));
            }
            createIndexRequest.settings(settings);

            final Map<String, Object> mapping = StaticMappingProvider.cvMapping();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Mapping: {}", Strings.toString(XContentFactory.contentBuilder(XContentType.JSON).map(mapping)));
            }
            createIndexRequest.mapping(mapping);

            CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            LOGGER.info("CREATEINDEXRESPONSE: index={}, acknowledged={}", createIndexResponse.index(), createIndexResponse.isAcknowledged());
        } catch (IllegalArgumentException ie) {
            throw new ApplicationException("Feil ved oppretting av ny indeks", ie);
        } catch (IOException ioe) {
            LOGGER.error("Feilet å lage index", ioe);
            throw new OperationalException(ioe);
        }
    }

    @Override
    public void deleteIndex(String indexName) {
        try {
            DeleteIndexRequest deleteRequest = new DeleteIndexRequest(indexName);
            AcknowledgedResponse deleteIndexResponse = client.indices().delete(deleteRequest, RequestOptions.DEFAULT);
            LOGGER.info("DELETERESPONSE: index={}, acknowledged={}", indexName, deleteIndexResponse.isAcknowledged());
        } catch (IOException ioe) {
            LOGGER.error("Feilet å slette index", ioe);
            throw new OperationalException(ioe);
        }
    }

    @Override
    public void index(EsCv esCv, String indexName) {
        try {
            String jsonString = mapper.writeValueAsString(esCv);
            LOGGER.debug("DOKUMENTET: " + jsonString);

            IndexRequest request = new IndexRequest(indexName).id(esCv.getKandidatnr());
            request.setRefreshPolicy(refreshPolicy);
            request.source(jsonString, XContentType.JSON);
            IndexResponse indexResponse = esExec(() -> client.index(request, RequestOptions.DEFAULT), indexName);
            LOGGER.debug("INDEXRESPONSE: " + indexResponse.toString());
        } catch (JsonProcessingException jpe) {
            throw new ApplicationException("Feil ved serialisering til JSON", jpe);
        }
    }

    @Override
    public int bulkIndex(List<EsCv> esCver, String indexName) {
        BulkRequest bulkRequest = Requests.bulkRequest();
        String currentKandidatnr = "";

        try {
            for (EsCv esCv : esCver) {
                currentKandidatnr = esCv.getKandidatnr();
                String jsonString = mapper.writeValueAsString(esCv);
                IndexRequest ir = Requests.indexRequest().source(jsonString, XContentType.JSON)
                        .index(indexName).id(currentKandidatnr);

                bulkRequest.add(ir);
            }
        } catch (JsonProcessingException e) {
            throw new ApplicationException("Feil ved serialisering til JSON", e);
        }

        LOGGER.debug("Sender bulk indexrequest med {} cv'er", esCver.size());
        bulkRequest.setRefreshPolicy(refreshPolicy);
        BulkResponse bulkResponse = esExec(() -> client.bulk(bulkRequest, RequestOptions.DEFAULT), indexName);
        int antallIndeksert = esCver.size();

        if (bulkResponse.hasFailures()) {
            long antallFeil = 0;
            for (BulkItemResponse bir : bulkResponse.getItems()) {
                if (bir.getFailure() != null && bir.getFailure().getType().equals("unavailable_shards_exception")) {
                    LOGGER.warn("Kaster OperationalException for å trigge retry grunnet feil mot ES: {}", bulkResponse.buildFailureMessage());
                    throw new OperationalException("Unavailable shards i kandidatsok ES", bir.getFailure().getCause());
                }
                antallFeil += bir.isFailed() ? 1 : 0;
                try {
                    if (bir.getFailure() != null) {
                        Optional<EsCv> cvMedFeil = esCver.stream().filter(
                                esCv -> (esCv.getKandidatnr()).trim().equals(bir.getFailure().getId()))
                                .findFirst();

                        LOGGER.warn("Feilet ved indeksering av CV {}: " + bir.getFailure().getMessage(),
                                cvMedFeil.isPresent() && LOGGER.isTraceEnabled() ? mapper.writeValueAsString(cvMedFeil.get()) : "",
                                bir.getFailure().getCause());
                    }
                } catch (Exception e) {
                    LOGGER.warn("Feilet ved parsing av bulkitemresponse..", e);
                    meterRegistry.counter("cv.es.index.feil", Tags.of("type", "infrastruktur"))
                            .increment();
                }
                meterRegistry.counter("cv.es.index.feil", Tags.of("type", "applikasjon"))
                        .increment(antallFeil);
            }
            antallIndeksert -= antallFeil;
            LOGGER.warn(
                    "Feilet under indeksering av CVer: " + bulkResponse.buildFailureMessage());
        }
        LOGGER.debug("BULKINDEX tidsbruk: " + bulkResponse.getTook());
        return antallIndeksert;
    }

    @Override
    public int bulkSlettAktorId(List<String> aktorIdListe, String indexName) {
        // Dette er essensielt, ellers risikere man at alle dokumenter i indeks slettes (en bool query uten clauses matcher
        // tydeligvis alt, på tross av at 'minimum_should_match' er satt til 1).
        if (aktorIdListe.isEmpty()) {
            return 0;
        }

        BoolQueryBuilder deleteQueryBuilder = QueryBuilders.boolQuery().minimumShouldMatch(1);
        aktorIdListe.forEach(aktorId -> {
            deleteQueryBuilder.should(QueryBuilders.termQuery("aktorId", aktorId));
        });

        DeleteByQueryRequest deleteByQueryRequest = new DeleteByQueryRequest(indexName);
        deleteByQueryRequest.setQuery(deleteQueryBuilder);
        // When many separate batches are deleted within a short time, scroll contexts build up in ES causing resource exhaustion issue.
        // Keep timeout very short. Each batch can at most be around 1000 docs, so a long lasting scroll should not be necessary here.
        deleteByQueryRequest.setScroll(TimeValue.timeValueSeconds(20));
        deleteByQueryRequest.setRefresh(refreshPolicy != WriteRequest.RefreshPolicy.NONE);

        BulkByScrollResponse bulkByScrollResponse = esExec(() -> client.deleteByQuery(deleteByQueryRequest, RequestOptions.DEFAULT), indexName);
        if (! bulkByScrollResponse.getBulkFailures().isEmpty()) {
            long antallFeil = bulkByScrollResponse.getBulkFailures().size();
            meterRegistry.counter("cv.es.slett.feil", Tags.of("type", "infrastruktur"))
                    .increment(antallFeil);
            throw new OperationalException("Feil i forsøk på batch-sletting av CV-er basert på aktør-ider med deleteByQuery");
        }

        return (int)bulkByScrollResponse.getDeleted();
    }


    @Override
    public int bulkSlettKandidatnr(List<String> kandidatnr, String indexName) {
        BulkRequest bulkRequest = Requests.bulkRequest();

        for (String id : kandidatnr) {
            bulkRequest.add(Requests.deleteRequest(indexName).id(id));
        }

        LOGGER.debug("Sender bulksletting av {} cv'er", kandidatnr.size());
        bulkRequest.setRefreshPolicy(refreshPolicy);
        BulkResponse bulkResponse = esExec(() -> client.bulk(bulkRequest, RequestOptions.DEFAULT), indexName);
        if (bulkResponse.hasFailures()) {
            LOGGER.warn("Feilet under sletting av CVer: " + bulkResponse.buildFailureMessage());
            long antallFeil =
                    Arrays.stream(bulkResponse.getItems()).filter(i -> i.isFailed()).count();
            meterRegistry.counter("cv.es.slett.feil", Tags.of("type", "infrastruktur"))
                    .increment(antallFeil);
            throw new OperationalException("Feil i forsøk på batch-sletting av CV-er basert på kandidatnumre");
        }
        LOGGER.debug("BULKDELETERESPONSE: " + bulkResponse);

        return (int)Arrays.stream(bulkResponse.getItems()).filter(
                r -> !r.isFailed() && r.getOpType() == DocWriteRequest.OpType.DELETE).count();
    }

    @Override
    public boolean doesIndexExist(String indexName) {
        try {
            return client.indices().exists(new GetIndexRequest(indexName), RequestOptions.DEFAULT);
        } catch (IOException ioe) {
            throw new OperationalException(ioe);
        }
    }

    /**
     * Pakk inn kall mot elastic search i sjekk på om index finnes. Hvis index ikke finnes så
     * opprettes den, og kallet forsøkes på nytt
     *
     * @param fun
     * @param <T>
     * @return
     * @throws IOException
     */
    private <T> T esExec(IOSupplier<T> fun, String indexName) {
        try {
            try {
                return fun.get();
            } catch (OpenSearchStatusException ese) {
                if (ese.status().getStatus() == 404
                        && ese.getMessage().contains("index_not_found_exception")) {
                    LOGGER.info(
                            "Greide ikke å utfore operasjon mot elastic search. Prøver å opprette index og forsøke på nytt.");
                    createIndex(indexName);
                    return fun.get();
                }
                throw ese;
            }
        } catch (IOException ioe) {
            throw new OperationalException(ioe);
        }
    }

    /**
     * Tilsvarer java.functions.Supplier bare at get metoden kan kaste IOException
     */
    private interface IOSupplier<T> {
        T get() throws IOException;
    }

    @Override
    public long antallIndeksert(String indexName) {
        return tellDokumenter(null, indexName);
    }

    private long tellDokumenter(String query, String indexName) {
        try {
            CountRequest countReqest = new CountRequest(indexName);
            if (query != null) {
                countReqest.query(QueryBuilders.queryStringQuery(query));
            }

            CountResponse count = client.count(countReqest, RequestOptions.DEFAULT);
            return count.getCount();
        } catch (Exception e) {
            LOGGER.warn("Greide ikke å hente ut antall dokumenter for indeks {}, spørring '{}': {}",
                    indexName, query != null ? query : "<no query>", e.getMessage(), e);
            throw new OperationalException(e);
        }
    }

    @Override
    public long antallIndeksertSynligForVeileder(String indexName) {
        return tellDokumenter("synligForVeilederSok:true", indexName);
    }

    @Override
    public long antallIndeksertSynligForArbeidsgiver(String indexName) {
        return tellDokumenter("synligForArbeidsgiverSok:true", indexName);
    }

    @Override
    public Collection<String> getTargetsForAlias(String alias, String indexPattern) {
        try {
            GetAliasesResponse aliases = client.indices().getAlias(new GetAliasesRequest(alias).indices(indexPattern), RequestOptions.DEFAULT);
            return aliases.getAliases().keySet();
        } catch (IOException io) {
            throw new OperationalException(io);
        }
    }

    @Override
    public boolean updateIndexAlias(String alias, String removeForIndexPattern, String addForIndexName) {

        IndicesAliasesRequest request = new IndicesAliasesRequest();
        request.addAliasAction(new AliasActions(AliasActions.Type.REMOVE).index(removeForIndexPattern).alias(alias));
        request.addAliasAction(new AliasActions(AliasActions.Type.ADD).index(addForIndexName).alias(alias));
        try {
            AcknowledgedResponse response = client.indices().updateAliases(request, RequestOptions.DEFAULT);
            LOGGER.info("Satt alias {} til å peke mot {}: {}", alias, addForIndexName, response.isAcknowledged() ? "ACK" : "ERR");
            return response.isAcknowledged();
        } catch (IOException e) {
            throw new OperationalException(e);
        }

    }

    public void ventPaaOppdatertIndeks(String indexName) {
        try {
            client.indices().refresh(new RefreshRequest(indexName), RequestOptions.DEFAULT);
        } catch (IOException io) {
            throw new OperationalException("Feilet under oppfriskning av indeks "+ indexName, io);
        }
    }

    @Override
    public void close() throws IOException {
        client.close();
    }

}
