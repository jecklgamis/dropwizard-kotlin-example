# dropwizard-kotlin-example
An example Dropwizard app using Kotlin

[![Build Status](https://travis-ci.org/jecklgamis/dropwizard-kotlin-example.svg?branch=master)](https://travis-ci.org/jecklgamis/dropwizard-kotlin-example)

# Running The App
```
mvn clean package
java -jar target/dropwizard-kotlin-example.jar server src/main/resources/config.yml
```

# Running The App In Docker
Create docker image
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


