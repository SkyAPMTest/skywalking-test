package com.a.eye.skywalking.test.cache;

import com.a.eye.skywalking.test.cache.h2.H2ServiceManager;
import com.a.eye.skywalking.test.cache.jedis.JedisServiceManager;
import com.a.eye.skywalking.test.cache.mongodb.MongoDBServiceManager;
import com.a.eye.skywalking.test.cache.util.StringUtils;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by xin on 2016/12/13.
 */
public class CacheServiceImpl implements CacheService {

    private Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);

    @Autowired
    private JedisServiceManager manager;

    @Autowired
    private H2ServiceManager h2ServiceManager;

    @Autowired
    private MongoDBServiceManager mongoDBServiceManager;

    private static Map<String, String> forTest = new ConcurrentHashMap<>();

    static {
        for (int i = 0; i < 10000000; i++) {
            forTest.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }
    }

    public String findCache(String key) {
        Map<String, String> forTest = new ConcurrentHashMap<String, String>();
        for (int i = 0; i < 1000; i++) {
            forTest.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }
        String value = manager.find(key);
        if (value != null)
            return value;

        return h2ServiceManager.find(key);
    }

    @Override
    public void updateCache(String key, String cacheValue) {
        Map<String, String> forTest = new ConcurrentHashMap<String, String>();
        for (int i = 0; i < 1000; i++) {
            forTest.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(cacheValue)) {
            logger.warn("Key is empty");
            return;
        }

        manager.updateCache(key, cacheValue);
        h2ServiceManager.updateCache(key, cacheValue);
        mongoDBServiceManager.updateCache(key, cacheValue);
    }

    @Override
    public String findCacheWithException(String key) {
        String value = manager.findWithException(key);
        if (value != null)
            return value;

        return h2ServiceManager.find(key);
    }
}
