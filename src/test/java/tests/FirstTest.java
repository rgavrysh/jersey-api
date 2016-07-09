package tests;

import com.sun.jersey.api.client.ClientResponse;
import jsonObjectMappers.LoginUser;
import jsonObjectMappers.SimpleMessage;
import org.testng.annotations.Test;

import javax.ws.rs.core.MediaType;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * RDP REST ENDPOINTS
 * Created by G538807 on 25/04/2016.
 */
public class FirstTest extends BaseTest {

    @Test
    public void login() {
        LoginUser response = webResource.path("auth").path("login")
                .accept(MediaType.APPLICATION_JSON)
                .post(LoginUser.class);
        assertThat(response.getPid(), equalTo("G538807"));
    }

    @Test
    public void rdpAuraWebServiceCheck() {
        SimpleMessage response = webResource.path("rdpaura").path("check")
                .accept(MediaType.APPLICATION_JSON)
                .get(SimpleMessage.class);
        assertThat(response.getMessage(), equalTo("Job is running"));
    }

    @Test
    public void rdpAuraJmxCheck() {
//        SimpleMessage response = webResource.path("rdpaurajms").path("check")
//                .accept(MediaType.APPLICATION_JSON)
//                .get(SimpleMessage.class);
        ClientResponse response = webResource.path("rdpaurajms").path("check")
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        assertThat(response.getStatus(), equalTo(ClientResponse.Status.OK.getStatusCode()));

        SimpleMessage msg = response.getEntity(SimpleMessage.class);
        assertThat(msg.getMessage(), equalTo("Job is running"));
    }

    @Test
    public void checkRdpCache() {
        ClientResponse response = webResource.path("cache").path("admin").path("checkRdpCache")
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        assertThat(response.getStatus(), equalTo(200));
    }

    @Test
    public void stopCacheScheduler() {
        SimpleMessage message = webResource.path("cache").path("admin").path("stop")
                .accept(MediaType.APPLICATION_JSON)
                .get(SimpleMessage.class);
        assertThat(message.getMessage(), equalTo("Job is shutdown"));

        message = webResource.path("cache").path("admin").path("check")
                .accept(MediaType.APPLICATION_JSON)
                .get(SimpleMessage.class);
        assertThat(message.getMessage(), equalTo("Job is not running"));
    }

    @Test(dependsOnMethods = "stopCacheScheduler")
    public void startCacheScheduler() {
        SimpleMessage message = webResource.path("cache").path("admin").path("start")
                .accept(MediaType.APPLICATION_JSON)
                .get(SimpleMessage.class);
        assertThat(message.getMessage(),
                equalTo("Started the rdpCacheBuilderjob to run in 24 hour interval"));

        message = webResource.path("cache").path("admin").path("check")
                .accept(MediaType.APPLICATION_JSON)
                .get(SimpleMessage.class);
        assertThat(message.getMessage(), equalTo("Job is running"));
    }
}