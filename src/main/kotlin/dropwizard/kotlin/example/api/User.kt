package dropwizard.kotlin.example.api;

import javax.validation.constraints.NotNull;

class User(@NotNull val username: String = "me", @NotNull val email: String = "me@example.com")


