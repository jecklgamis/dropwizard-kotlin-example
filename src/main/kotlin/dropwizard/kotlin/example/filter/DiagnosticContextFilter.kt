package dropwizard.kotlin.example.filter

import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerRequestFilter
import jakarta.ws.rs.container.ContainerResponseContext
import jakarta.ws.rs.container.ContainerResponseFilter
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import java.util.UUID.randomUUID

class DiagnosticContextFilter : ContainerRequestFilter, ContainerResponseFilter {
    private val log = LoggerFactory.getLogger(DiagnosticContextFilter::class.java)
    private val requestId = "id"

    override fun filter(requestContext: ContainerRequestContext) {
        val id = randomUUID().toString()
        log.debug("[${Thread.currentThread().name}] PUT : $id")
        MDC.put(requestId, id)
    }

    override fun filter(requestContext: ContainerRequestContext, responseContext: ContainerResponseContext) {
        val id = MDC.get(requestId)
        log.debug("[${Thread.currentThread().name}] REMOVE : $id")
        MDC.remove(requestId)
    }
}
