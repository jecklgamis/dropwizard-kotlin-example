package dropwizard.kotlin.example.resource

import com.codahale.metrics.annotation.Timed
import com.google.gson.JsonParser
import org.apache.commons.lang3.Validate
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.CacheControl
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/buildInfo")
class BuildInfoResource(private val buildInfoJson: String) {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    fun default(): Response {
        require(!buildInfoJson.isNullOrEmpty())
        val versionElem = JsonParser.parseString(buildInfoJson).asJsonObject.get("version")
        val version = versionElem?.asString ?: ""
        Validate.notEmpty(version, "No version information found for the application")

        val cacheControl = CacheControl()
        cacheControl.isNoCache = true
        return Response.ok().cacheControl(cacheControl)
                .entity(buildInfoJson).build()
    }
}