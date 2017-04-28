FROM openjdk:8-jdk

ENV USER_NAME=test \
    APPLICATION_CODE=test \
    SERVER_LIST=127.0.0.1:34000

RUN mkdir -p /usr/local/skywalking/agent && mkdir -p /usr/local/skywalking/cache-server/libs

COPY cache-server.jar /usr/local/skywalking/cache-server
ADD libs /usr/local/skywalking/cache-server/libs
COPY cache-start.sh /usr/local/skywalking/cache-server
RUN chmod +x /usr/local/skywalking/cache-server/cache-start.sh

VOLUME /usr/local/skywalking/agent
ENTRYPOINT ["/usr/local/skywalking/cache-server/cache-start.sh"]