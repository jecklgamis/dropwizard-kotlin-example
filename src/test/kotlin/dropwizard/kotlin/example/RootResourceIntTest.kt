package dropwizard.kotlin.example

import io.dropwizard.testing.ConfigOverride
import io.dropwizard.testing.ResourceHelpers.resourceFilePath
import io.dropwizard.testing.junit5.DropwizardAppExtension
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport
import jakarta.ws.rs.client.Client
import jakarta.ws.rs.core.Response
import org.glassfish.jersey.client.ClientConfig
import org.glassfish.jersey.client.ClientProperties
import org.glassfish.jersey.client.JerseyClientBuilder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(DropwizardExtensionsSupport::class)
class RootResourceIntTest {

    companion object {

        @JvmField
        val ext = DropwizardAppExtension(ExampleApp::class.java,
                resourceFilePath("config.yml"),
                ConfigOverride.randomPorts()
        )
    }

    @Test
    fun testDefaultResource() {
        val response = client().target("http://127.0.0.1:${ext.localPort}/").request()
                .get(Response::class.java)
        assertEquals(200, response.status.toLong())
        assertEquals("application/json", response.headers.getFirst("Content-Type"))
        val entity = response.readEntity(Map::class.java)
        assertEquals("dropwizard-kotlin-example", entity["name"])
    }

    private fun client(): Client {
        val config = ClientConfig()
        config.property(ClientProperties.CONNECT_TIMEOUT, 5000)
        config.property(ClientProperties.READ_TIMEOUT, 15000)
        return JerseyClientBuilder.createClient(config)
    }

}


