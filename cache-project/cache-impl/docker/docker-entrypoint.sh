#!/bin/sh

echo "replace {REDIS_HOST} to $REDIS_HOST"
eval sed -i -e 's/\{REDIS_HOST\}/$REDIS_HOST/' /usr/local/skywalking/cache-server/config/config.properties

echo "replace {REDIS_PORT} to $REDIS_PORT"
eval sed -i -e 's/\{REDIS_PORT\}/$REDIS_PORT/' /usr/local/skywalking/cache-server/config/config.properties

echo "replace {MONGO_HOST} to $MONGO_HOST"
eval sed -i -e 's/\{MONGO_HOST\}/$MONGO_HOST/' /usr/local/skywalking/cache-server/config/config.properties

echo "replace {MONGO_PORT} to $MONGO_PORT"
eval sed -i -e 's/\{MONGO_PORT\}/$MONGO_PORT/' /usr/local/skywalking/cache-server/config/config.properties

echo "replace {ZK_ADDRESS} to $ZK_ADDRESS"
eval sed -i -e 's/\{ZK_ADDRESS\}/$ZK_ADDRESS/' /usr/local/skywalking/cache-server/config/config.properties

exec "$@"
