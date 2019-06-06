package dropwizard.kotlin.example.resource

import com.codahale.metrics.annotation.Timed
import com.google.common.collect.ImmutableMap
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
        val entity = ImmutableMap.builder<String, Any>()
                .put("name", name)
                .put("java.version", System.getProperty("java.version"))
                .put("java.runtime.name", System.getProperty("java.runtime.name"))
                .build()
        return Response.ok().entity(entity).build()
    }
}