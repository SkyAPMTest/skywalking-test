FROM openjdk:8-jdk-alpine

ENV COLLECTOR_SERVERS=127.0.0.1:10800 \
    REDIS_HOST=127.0.0.1 \
    REDIS_PORT=6379 \
    MONGO_HOST=127.0.0.1 \
    MONGO_PORT=27017 \
    ZK_ADDRESS=127.0.0.1:2181

RUN mkdir -p /usr/local/skywalking/agent

ADD cache-server.tar.gz /usr/local/skywalking
COPY docker-entrypoint.sh /
RUN chmod +x /docker-entrypoint.sh
COPY config.properties /usr/local/skywalking/cache-server/config/config.properties

VOLUME /usr/local/skywalking/agent
ENTRYPOINT ["/docker-entrypoint.sh"]
CMD ["/usr/local/skywalking/cache-server/bin/cache-start.sh"]
