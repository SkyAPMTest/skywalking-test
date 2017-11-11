package com.a.eye.skywalking.test.cache;

import com.a.eye.skywalking.test.cache.h2.H2ServiceManager;
import com.a.eye.skywalking.test.cache.jedis.JedisServiceManager;
import com.a.eye.skywalking.test.cache.mongodb.MongoDBServiceManager;
import com.a.eye.skywalking.test.cache.util.StringUtils;
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

    public String findCache(String key) {
        String value = manager.find(key);
        if (value != null)
            return value;

        return h2ServiceManager.find(key);
    }

    @Override
    public void updateCache(String key, String cacheValue) {
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
