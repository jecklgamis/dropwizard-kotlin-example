#!/bin/bash
set -ex
java -jar "target/dropwizard-kotlin-example.jar" server "src/main/resources/config.yml"

