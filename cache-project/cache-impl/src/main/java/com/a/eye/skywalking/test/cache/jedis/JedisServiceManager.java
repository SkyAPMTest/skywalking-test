package com.a.eye.skywalking.test.cache.jedis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by xin on 2016/12/13.
 */
@Repository
public class JedisServiceManager {

    private Logger logger = LogManager.getLogger(JedisServiceManager.class);

    @Autowired
    private JedisPool jedisPoolInstance;

    @PostConstruct
    public void initData() {
        JedisServiceManager.jedisPool = jedisPoolInstance;
    }

    private static JedisPool jedisPool;

    public String find(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
            logger.error("Failed to find the value of Key[{}]", key, e);
        } finally {
            if (jedis != null)
                jedis.close();
        }

        return null;
    }

    public String findWithException(String key) {
        Jedis jedis = null;
        try {
            return jedis.get(key);
        } catch (Exception e) {
            logger.error("Failed to find the value of Key[{}]", key, e);
            throw e;
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }

    public void updateCache(String key, String cacheValue) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, cacheValue);
        } catch (Exception e) {
            logger.error("Failed to find the value of Key[{}]", key, e);
        } finally {
            if (jedis != null)
                jedis.close();
        }
    }
}
