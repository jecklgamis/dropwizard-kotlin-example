package dropwizard.kotlin.example.resource

import com.codahale.metrics.annotation.Timed
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

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