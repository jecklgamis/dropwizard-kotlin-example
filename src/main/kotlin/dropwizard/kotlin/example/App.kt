package dropwizard.kotlin.example

import com.google.common.io.Resources
import dropwizard.kotlin.example.filter.DiagnosticContextFilter
import dropwizard.kotlin.example.healthcheck.DefaultHealthCheck
import dropwizard.kotlin.example.resource.BuildInfoResource
import dropwizard.kotlin.example.resource.RootResource
import io.dropwizard.core.Application
import io.dropwizard.core.setup.Environment


class App : Application<AppConfig>() {

    override fun run(config: AppConfig, env: Environment) {
        env.jersey().register(RootResource(config.appName))
        env.jersey().register(DiagnosticContextFilter())
        env.healthChecks().register("default", DefaultHealthCheck())

        val resource = BuildInfoResource(
            Resources.toString(Resources.getResource("build-info.json"), Charsets.UTF_8)
        )
        env.jersey().register(resource)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            App().run(*args)
        }
    }

}

