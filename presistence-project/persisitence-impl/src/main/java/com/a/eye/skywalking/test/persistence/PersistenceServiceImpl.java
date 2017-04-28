package com.a.eye.skywalking.test.persistence;


import com.a.eye.skywalking.test.persistence.mysql.MySqlServiceManager;
import com.alibaba.dubbo.config.annotation.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by xin on 2016/12/13.
 */
@Service
public class PersistenceServiceImpl implements PersistenceService {

    private Logger logger = LogManager.getLogger(PersistenceService.class);

    @Autowired
    private MySqlServiceManager manager;

    public CacheItem query(String key) {
        logger.info("query cache value for key [{}]", key);
        return manager.find(key);
    }
}
