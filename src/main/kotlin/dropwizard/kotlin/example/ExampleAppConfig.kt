package dropwizard.kotlin.example

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import org.hibernate.validator.constraints.NotEmpty

class ExampleAppConfig : Configuration() {
    @JsonProperty
    @NotEmpty
    val appName: String = "appName";
}
