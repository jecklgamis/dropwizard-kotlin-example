package dropwizard.kotlin.example.resource

import dropwizard.kotlin.example.api.User
import java.util.concurrent.ConcurrentHashMap
import javax.validation.constraints.NotNull
import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Response


@Path("/user")
@Consumes(*arrayOf(APPLICATION_JSON))
@Produces(*arrayOf(APPLICATION_JSON))
class UserResource {
    private val userDb = ConcurrentHashMap<String, User>()

    @GET
    fun get(@QueryParam("username") username: String): Response {
        if (userDb.containsKey(username)) {
            return Response.ok().entity(userDb[username]).build()
        }
        return Response.noContent().build()
    }

    @PUT
    fun put(@NotNull user: User): Response {
        userDb.put(user.username, user)
        return Response.ok().build()
    }

    @POST
    fun post(@NotNull user: User): Response {
        userDb.put(user.username, user)
        return Response.ok().build()
    }

    @DELETE
    fun delete(@NotNull @QueryParam("username") username: String): Response {
        userDb.remove(username)
        return Response.ok().build()
    }

}
