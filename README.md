## Dropwizard Kotlin Example

[![Build Status](https://travis-ci.org/jecklgamis/dropwizard-kotlin-example.svg?branch=master)](https://travis-ci.org/jecklgamis/dropwizard-kotlin-example)

This is an example Dropwizard app using Kotlin. 

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
curl -v -k https://localhost:8443/
```
Operational menu endpoint:
* `http://localhost:8081`


