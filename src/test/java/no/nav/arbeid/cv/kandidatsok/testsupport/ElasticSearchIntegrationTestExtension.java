package no.nav.arbeid.cv.kandidatsok.testsupport;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

/**
 * Ensures that ElasticSearch is running before integration tests start.
 *
 * <p>On a Linux host with Docker, you may need to set an OS option for ES to work.</p>
 * <p>Add file:</p>
 * <pre>/etc/sysctl.d/01-increase_vm_max_map_count.conf</pre>
 * <p>with contents:</p>
 * <pre>vm.max_map_count = 262144</pre>
 *
 * Requires that tests are run within a single JVM (no forks across test individual test classes).
 */
public class ElasticSearchIntegrationTestExtension implements BeforeAllCallback, ExtensionContext.Store.CloseableResource {

    private static DockerComposeEnv dce = null;
    private static final DockerComposeEnv.Builder builder =
            DockerComposeEnv.builder("src/test/resources/docker-compose-opensearch.yml")
             .addAutoPortVariable("ES_PORT")
             .readyOnHttpGet2xx("http://localhost:{VALUE}", "ES_PORT")
             .dockerComposeLogDir("target");

    static Integer getEsPort() {
        return Integer.parseInt(builder.getEnvVariable("ES_PORT"));
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        if (dce != null) {
            return;
        }
        dce = builder.up();
        extensionContext.getRoot().getStore(GLOBAL).put(getClass().getName(), this);
    }

    @Override
    public void close() throws Exception {
        if (dce != null) {
            dce.down();
        }
    }

}
