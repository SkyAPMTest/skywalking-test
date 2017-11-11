FROM openjdk:8-jdk-alpine

ENV MYSQL_ADDRESS=127.0.0.1:3306 \
    MYSQL_USER=root \
    MYSQL_PASSWORD=root \
    ZK_ADDRESS=127.0.0.1:2181 \
    COLLECTOR_SERVERS=127.0.0.1:10800

RUN mkdir -p /usr/local/skywalking/agent

ADD persistence-server.tar.gz /usr/local/skywalking
COPY docker-entrypoint.sh /
COPY config.properties /usr/local/skywalking/persistence-server/config/config.properties
RUN chmod +x /docker-entrypoint.sh

VOLUME /usr/local/skywalking/agent

ENTRYPOINT ["/docker-entrypoint.sh"]
CMD ["/usr/local/skywalking/persistence-server/bin/persistence-start.sh"]
