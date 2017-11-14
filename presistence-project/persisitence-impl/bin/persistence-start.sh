#!/bin/sh

SKYWALKING_AGENT_FILE="/usr/local/skywalking-agent/skywalking-agent.jar"
SKYWALKING_OPTS=""

CACHE_SERVICE_HOME=/usr/local/skywalking/persistence-server
CLASSPATH="$CACHE_SERVICE_HOME/config:$CLASSPATH"
for i in "$CACHE_SERVICE_HOME"/libs/*.jar
do
    CLASSPATH="$i:$CLASSPATH"
done

if [ ! -f "$SKYWALKING_AGENT_FILE" ]; then
    echo "skywalking agent file cannot be found."
else
    SKYWALKING_OPTS=" -javaagent:$SKYWALKING_AGENT_FILE -Dskywalking.agent.application_code=persistence-service -Dskywalking.collector.servers=${COLLECTOR_SERVERS} "
fi

$JAVA_HOME/bin/java $SKYWALKING_OPTS -classpath $CLASSPATH com.a.eye.skywalking.test.persistence.Main
