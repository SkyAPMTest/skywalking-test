FROM alpine:3.6

ADD skywalking-agent.tar.gz /usr/local
RUN touch /usr/local/skywalking-agent/logs/skywalking-api.log

VOLUME /usr/local/skywalking-agent

CMD ["tail", "-f", "/usr/local/skywalking-agent/logs/skywalking-api.log"]
