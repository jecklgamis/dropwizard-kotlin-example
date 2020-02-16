package dropwizard.kotlin.example

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import javax.validation.constraints.NotEmpty

class ExampleAppConfig : Configuration() {
    @JsonProperty
    @NotEmpty
    val appName: String = "appName";
}
