FROM amd64/eclipse-temurin:21-jre-jammy
MAINTAINER Jerrico Gamis <jecklgamis@gmail.com>

ENV APP_HOME /app

RUN groupadd -r app && useradd -r -gapp app
RUN mkdir -m 0755 -p ${APP_HOME}/bin
RUN mkdir -m 0755 -p ${APP_HOME}/config
RUN mkdir -m 0755 -p ${APP_HOME}/logs

COPY target/dropwizard-kotlin-example.jar ${APP_HOME}/bin
COPY docker-entrypoint.sh /
COPY src/main/resources/config.yaml ${APP_HOME}/config

RUN chown -R app:app ${APP_HOME}
RUN chmod +x /docker-entrypoint.sh

EXPOSE 8080
EXPOSE 8081
EXPOSE 8443

WORKDIR ${APP_HOME}
CMD ["/docker-entrypoint.sh"]

