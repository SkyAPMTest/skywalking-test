package com.a.eye.skywalking.test.persistence;


import com.a.eye.skywalking.test.persistence.mysql.MySqlServiceManager;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by xin on 2016/12/13.
 */
@Service
public class PersistenceServiceImpl implements PersistenceService {

    private Logger logger = LogManager.getLogger(PersistenceService.class);

    private static Map<String, String> forTest = new ConcurrentHashMap<>();

    static {
        for (int i = 0; i < 1000000; i++) {
            forTest.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }
    }

    @Autowired
    private MySqlServiceManager manager;

    public CacheItem query(String key) {
        Map<String, String> forTest = new ConcurrentHashMap<String, String>();
        for (int i = 0; i < 10000; i++) {
            forTest.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }
        logger.info("query cache value for key [{}]", key);
        return manager.find(key);
    }
}
