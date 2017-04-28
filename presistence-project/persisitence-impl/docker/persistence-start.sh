#!/bin/bash

SKYWALKING_AGENT_FILE="/usr/local/skywalking/agent/skywalking-agent.jar"
SKYWALKING_OPTS=""

if [ ! -f "$SKYWALKING_AGENT_FILE" ]; then
    echo "skywalking agent file cannot be found."
else
    SKYWALKING_OPTS=" -Dusername=${USER_NAME} -DapplicationCode=${APPLICATION_CODE} -Dservers=${SERVER_LIST} -Dagent=${SKYWALKING_AGENT_FILE} -javaagent:$SKYWALKING_AGENT_FILE"
fi

$JAVA_HOME/bin/java $SKYWALKING_OPTS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000 -jar /usr/local/skywalking/persistence-server/persistence-server.jar