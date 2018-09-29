#!/bin/bash
set -ex
cd /usr/local/app
exec java -jar "bin/dropwizard-kotlin-example.jar" server "config/config.yml"

