package dropwizard.kotlin.example

import dropwizard.kotlin.example.api.User
import io.dropwizard.testing.ResourceHelpers
import io.dropwizard.testing.junit.DropwizardAppRule
import org.glassfish.jersey.client.ClientConfig
import org.glassfish.jersey.client.ClientProperties
import org.glassfish.jersey.client.JerseyClientBuilder
import org.junit.Assert.assertEquals
import org.junit.ClassRule
import org.junit.Test
import java.lang.String.format
import javax.ws.rs.client.Client
import javax.ws.rs.client.Entity
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Response

class UserResourceIntTest {

    companion object {
        @ClassRule @JvmField
        val rule = DropwizardAppRule(ExampleApp::class.java, ResourceHelpers.resourceFilePath("config.yml"))
    }

    @Test
    fun testGet() {
        val response = client().target("http://127.0.0.1:${rule.localPort}/user")
                .queryParam("username", "me")
                .request().get(Response::class.java)
        assertEquals(204, response.status)
    }

    @Test
    fun testPut() {
        val response = client().target("http://127.0.0.1:${rule.localPort}/user")
                .request().put(Entity.entity(User("me", "me@example.com"), APPLICATION_JSON),
                Response::class.java)
        assertEquals(200, response.status)
    }

    @Test
    fun testPost() {
        val response = client().target("http://127.0.0.1:${rule.localPort}/user")
                .request().post(Entity.entity(User("me", "me@example.com"), APPLICATION_JSON), Response::class.java)
        assertEquals(200, response.status)
    }

    @Test
    fun testDelete() {
        var response = client().target("http://127.0.0.1:${rule.localPort}/user")
                .request().put(Entity.entity(User("me", "me@example.com"), APPLICATION_JSON), Response::class.java)
        assertEquals(200, response.status)

        response = client().target(format("http://127.0.0.1:%d/user", rule.localPort)).queryParam("username", "me").request().delete(Response::class.java)
        assertEquals(200, response.status)
    }

    private fun client(): Client {
        val config = ClientConfig()
        config.property(ClientProperties.CONNECT_TIMEOUT, 5000)
        config.property(ClientProperties.READ_TIMEOUT, 15000)
        return JerseyClientBuilder.createClient(config)
    }


}


