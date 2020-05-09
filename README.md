## Dropwizard Kotlin Example

[![Build Status](https://travis-ci.org/jecklgamis/dropwizard-kotlin-example.svg?branch=master)](https://travis-ci.org/jecklgamis/dropwizard-kotlin-example)

This is an example Dropwizard app using Kotlin.

Docker Hub: `docker run -p 8080:8080 -p 8443:8443 jecklgamis/dropwizard-kotlin-example:latest` 
 
## Running The App
Ensure you have Java 8 or later.
```
mvn clean package
java -jar target/dropwizard-kotlin-example.jar
```

## Running The App Using Docker
Ensure you have a working Docker environment.
```
make dist image run
```

## Testing The Endpoints
Point your browser to `http://localhost:8080` or use `curl` in command line.

```
curl -v  http://localhost:8080/
curl -v  --cacert src/main/resources/server.crt --resolve "dropwizard-kotlin-example:8443:127.0.0.1"  https://dropwizard-kotlin-example:8443/
```

Operational menu endpoint:
* `http://localhost:8081`


