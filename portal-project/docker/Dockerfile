FROM tomcat:8.0.39-jre8-alpine

ENV ZK_ADDRESS=127.0.0.1:2181 \
    COLLECTOR_SERVERS=127.0.0.1:10800

ENV JPDA_ADDRESS="8000"
ENV JPDA_TRANSPORT="dt_socket"

RUN mkdir -p /usr/local/skywalking-agent

COPY catalina.sh /usr/local/tomcat/bin
ADD portal /usr/local/tomcat/webapps/portal
COPY config.properties /usr/local/tomcat/webapps/portal/WEB-INF/classes/config.properties

COPY docker-entrypoint.sh /

VOLUME /usr/local/skywalking-agent
RUN chmod +x /usr/local/tomcat/bin/*.sh && chmod +x /docker-entrypoint.sh

EXPOSE 8080 8000

ENTRYPOINT ["/docker-entrypoint.sh"]
CMD ["/usr/local/tomcat/bin/catalina.sh", "jpda", "run"]




