#!/bin/sh

echo "replace {MYSQL_ADDRESS} to $MYSQL_ADDRESS"
eval sed -i -e 's/\{MYSQL_ADDRESS\}/$MYSQL_ADDRESS/' /usr/local/skywalking/persistence-server/config/config.properties

echo "replace {MYSQL_USER} to $MYSQL_USER"
eval sed -i -e 's/\{MYSQL_USER\}/$MYSQL_USER/' /usr/local/skywalking/persistence-server/config/config.properties

echo "replace {MYSQL_PASSWORD} to $MYSQL_PASSWORD"
eval sed -i -e 's/\{MYSQL_PASSWORD\}/$MYSQL_PASSWORD/' /usr/local/skywalking/persistence-server/config/config.properties

echo "replace {ZK_ADDRESS} to $ZK_ADDRESS"
eval sed -i -e 's/\{ZK_ADDRESS\}/$ZK_ADDRESS/' /usr/local/skywalking/persistence-server/config/config.properties

exec "$@"
