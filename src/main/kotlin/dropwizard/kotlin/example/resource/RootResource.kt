package dropwizard.kotlin.example.resource

import com.codahale.metrics.annotation.Timed
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/")
class RootResource(private val name: String) {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    fun default(): Response {
        val entity = mapOf("name" to name,
                "java.version" to System.getProperty("java.version"),
                "java.runtime.name" to System.getProperty("java.runtime.name"))
        return Response.ok().entity(entity).build()
    }
}