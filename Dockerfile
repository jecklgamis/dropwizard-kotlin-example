FROM jecklgamis/openjdk-8-jre:latest
MAINTAINER Jerrico Gamis <jecklgamis@gmail.com>

RUN groupadd -r app && useradd -r -gapp app
RUN mkdir -m 0755 -p /usr/local/app/bin
RUN mkdir -m 0755 -p /usr/local/app/config
RUN mkdir -m 0755 -p /usr/local/app/logs/

COPY target/dropwizard-kotlin-example.jar /usr/local/app/bin
COPY docker-entrypoint.sh /usr/local/app/bin
COPY src/main/resources/config.yml /usr/local/app/config
COPY src/main/resources/keystore.pfx /usr/local/app

RUN chown -R app:app /usr/local/app
RUN chmod +x /usr/local/app/bin/docker-entrypoint.sh

EXPOSE 8080
EXPOSE 8081
EXPOSE 8443

CMD ["/usr/local/app/bin/docker-entrypoint.sh"]

