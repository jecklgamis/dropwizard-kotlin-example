IMAGE_NAME:=dropwizard-kotlin-example
IMAGE_TAG:=main

default:
	cat ./Makefile
dist: build-info
	./mvnw clean package
image:
	docker build -t $(IMAGE_NAME):$(IMAGE_TAG) .
run:
	docker run -p 8080:8080 -p 8081:8081 $(IMAGE_NAME):$(IMAGE_TAG)
run-bash:
	docker run -i -t $(IMAGE_NAME):$(IMAGE_TAG) /bin/bash
build-info:
	./generate-build-info.sh
all: dist image
up: all run
