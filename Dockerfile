FROM jecklgamis/oracle-java-runtime:latest
MAINTAINER Jerrico Gamis <jecklgamis@gmail.com>

RUN apt-get update -y && apt-get install -y supervisor
RUN groupadd -r app && useradd -r -gapp app
RUN mkdir -m 0755 -p /usr/local/app/bin
RUN mkdir -m 0755 -p /usr/local/app/config
RUN mkdir -m 0755 -p /usr/local/app/logs/

COPY target/dropwizard-kotlin-example.jar /usr/local/app/bin
COPY run-app.sh /usr/local/app/bin
COPY src/main/resources/config.yml /usr/local/app/config

COPY app-supervisor.conf /etc/supervisor/conf.d

RUN chown -R app:app /usr/local/app
RUN chmod +x /usr/local/app/bin/run-app.sh

CMD ["/usr/local/app/bin/run-app.sh"]

# or use the supervisor to start the app (see app-supervisor.conf)
#CMD ["/usr/bin/supervisord"]



