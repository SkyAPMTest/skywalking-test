#!/bin/sh

echo "replace {ZK_ADDRESS} to $ZK_ADDRESS"
eval sed -i -e 's/\{ZK_ADDRESS\}/$ZK_ADDRESS/' /usr/local/tomcat/webapps/portal/WEB-INF/classes/config.properties

exec "$@"
