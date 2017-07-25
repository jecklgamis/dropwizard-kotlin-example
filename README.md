# dropwizard-kotlin-example
An example Dropwizard app using Kotlin

* Dropwizard 1.1.2
* Kotlin 1.1.3
* Java 8
* Maven 3
* Example resource , health check, filter.

# Running The App
```
mvn clean package
java -jar target/dropwizard-kotlin-example.jar server src/main/resources/config.yml
```

# Running The App In Docker
This assumes you have a working docker environment

.Create docker image
```
docker build -t dropwizard-kotlin-example .
```

Run the app inside docker
```
docker run dropwizard-kotlin-example
```

Run bash shell inside docker (you know, to check some stuff)
```
docker run -i -t dropwizard-kotlin-example /bin/bash
```

# GET, PUT, POST, DELETE Examples
PUT Request
```
curl -v -X PUT -H "Content-Type:application/json" "http://localhost:8080/user" -d'{"username":"me", "email":"me@example.com"}'
```

POST Request
```
curl -v -X POST -H "Content-Type:application/json" "http://localhost:8080/user" -d'{"username":"me", "email":"me@example.com"}'
```

GET Request
```
curl -v -X GET -H "Content-Type:application/json" "http://localhost:8080/user?username=me"
```

DELETE Request
```
curl -v -X DELETE -H "Content-Type:application/json" "http://localhost:8080/user?username=me"
```


