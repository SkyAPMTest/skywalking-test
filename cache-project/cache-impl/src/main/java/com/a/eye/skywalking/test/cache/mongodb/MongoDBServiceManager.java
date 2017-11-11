package com.a.eye.skywalking.test.cache.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ThreadLocalRandom;

@Repository
public class MongoDBServiceManager {
    private String mongoHost;
    private int mongoPort;

    public void updateCache(String key, String value) {
        MongoClient mongo = new MongoClient(mongoHost, mongoPort);
        MongoDatabase mongoDatabase = mongo.getDatabase("skywalking-test-db");
        MongoCollection<Document> table = mongoDatabase.getCollection("skywalking-test");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("key", key);
        FindIterable<Document> cursor = table.find(searchQuery);
        if (!cursor.iterator().hasNext()) {
            Document document = new Document();
            document.put("key_" + ThreadLocalRandom.current().nextLong(), key);
            document.put("value", value);
            table.insertOne(document);
        }
        mongo.close();
    }

    @Value("${mongo.host}")
    public void setMongoHost(String mongoHost) {
        this.mongoHost = mongoHost;
    }

    @Value("${mongo.port}")
    public void setMongoPort(int mongoPort) {
        this.mongoPort = mongoPort;
    }
}
