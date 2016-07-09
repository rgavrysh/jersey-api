package Client;

import ch.keyon.security.provider.capi.CAPI;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.net.URI;
import java.security.KeyStore;
import java.security.Security;

/**
 * RDP REST ENDPOINTS
 * Created by G538807 on 25/04/2016.
 */
public class WebTargetInitialisation {

    public static WebResource getWebResource(URI baseUri) throws Exception{
        Security.insertProviderAt(new CAPI(), 2);

        KeyStore myKeyStore = KeyStore.getInstance("CAPI_JSSE_Client");
        myKeyStore.load(null, null);

        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(myKeyStore, null);

        KeyStore trustKeyStore = KeyStore.getInstance("CAPI_Trust");
        trustKeyStore.load(null, null);

        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(trustKeyStore);

        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
            public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
                return true;
            }
        });

        SSLContext m_sslContext = SSLContext.getInstance("TLS");
        m_sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

        ClientConfig config = new DefaultClientConfig();
        config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(null, m_sslContext));
        config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        Client client = Client.create(config);

        return client.resource(baseUri);
    }
}
