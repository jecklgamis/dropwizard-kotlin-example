package dropwizard.kotlin.example

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.core.Configuration
import jakarta.validation.constraints.NotEmpty

class ExampleAppConfig : Configuration() {
    @JsonProperty
    @NotEmpty
    val appName: String = "appName";
}
