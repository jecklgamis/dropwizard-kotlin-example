default:
	cat ./Makefile
dist: 
	mvn clean package
image:
	 docker build -t dropwizard-kotlin-example .
run:
	 docker run -p 8080:8080 dropwizard-kotlin-example
run-bash:
	 docker run -i -t dropwizard-kotlin-example /bin/bash
