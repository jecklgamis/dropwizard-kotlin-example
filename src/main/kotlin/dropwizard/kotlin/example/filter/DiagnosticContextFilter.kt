package dropwizard.kotlin.example.filter

import org.slf4j.LoggerFactory
import org.slf4j.MDC
import java.util.UUID.randomUUID
import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerRequestFilter
import javax.ws.rs.container.ContainerResponseContext
import javax.ws.rs.container.ContainerResponseFilter

class DiagnosticContextFilter : ContainerRequestFilter, ContainerResponseFilter {
    private val log = LoggerFactory.getLogger(DiagnosticContextFilter::class.java)
    private val requestId = "id"

    override fun filter(requestContext: ContainerRequestContext) {
        val id = randomUUID().toString()
        log.info("[${Thread.currentThread().name}] PUT : $id")
        MDC.put(requestId, id)
    }

    override fun filter(requestContext: ContainerRequestContext, responseContext: ContainerResponseContext) {
        val id = MDC.get(requestId)
        log.info("[${Thread.currentThread().name}] REMOVE : $requestId")
        MDC.remove(id)
    }
}
