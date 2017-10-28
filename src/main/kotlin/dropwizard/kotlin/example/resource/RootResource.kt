package dropwizard.kotlin.example.resource

import com.google.common.collect.ImmutableMap
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/")
class RootResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun default(): Response {
        val entity = ImmutableMap.builder<String, String>().put("message", "It works!").build()
        return Response.ok().entity(entity).build()
    }
}