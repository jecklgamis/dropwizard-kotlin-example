package dropwizard.kotlin.example.resource

import com.codahale.metrics.annotation.Timed
import com.google.gson.JsonParser
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.CacheControl
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.apache.commons.lang3.Validate

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