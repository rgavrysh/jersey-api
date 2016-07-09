package tests;

import Client.WebTargetInitialisation;
import com.sun.jersey.api.client.WebResource;
import configs.TestConfig;
import org.testng.annotations.BeforeClass;
import ru.qatools.properties.PropertyLoader;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * RDP REST ENDPOINTS
 * Created by G538807 on 26/04/2016.
 */
public class BaseTest {
    private static final TestConfig config = PropertyLoader.newInstance().populate(TestConfig.class);

    WebResource webResource = null;
    @BeforeClass
    public void beforeClass() throws Exception {
        webResource = WebTargetInitialisation.getWebResource(
                buildUri(config.getBaseUrl()));
    }

    private URI buildUri(String serverUrl) {
        return UriBuilder.fromUri(serverUrl).build();
    }
}
