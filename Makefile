IMAGE_NAME:=jecklgamis/dropwizard-kotlin-example
IMAGE_TAG:=latest
default:
	cat ./Makefile
dist: 
	./mvnw clean package
image:
	 docker build -t $(IMAGE_NAME):$(IMAGE_TAG) .
run:
	 docker run -p 8080:8080 -p 8081:8081 -p 8443:8443 $(IMAGE_NAME):$(IMAGE_TAG)
run-bash:
	 docker run -it $(IMAGE_NAME):$(IMAGE_TAG) /bin/bash
keystore:
	 ./generate-keystore.sh
up: keystore dist image run
push:
	 docker push $(IMAGE_NAME):$(IMAGE_TAG)
