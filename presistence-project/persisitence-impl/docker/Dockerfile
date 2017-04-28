FROM openjdk:8-jdk

ENV USER_NAME=test \
    APPLICATION_CODE=test \
    SERVER_LIST=127.0.0.1:34000

RUN mkdir -p /usr/local/skywalking/agent && mkdir -p /usr/local/skywalking/persistence-server/libs

COPY persistence-server.jar /usr/local/skywalking/persistence-server
ADD libs /usr/local/skywalking/persistence-server/libs
COPY persistence-start.sh /usr/local/skywalking/persistence-server
RUN chmod +x /usr/local/skywalking/persistence-server/persistence-start.sh

VOLUME /usr/local/skywalking/agent

EXPOSE 8000

CMD ["/usr/local/skywalking/persistence-server/persistence-start.sh"]