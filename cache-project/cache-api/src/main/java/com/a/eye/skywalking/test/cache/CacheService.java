package com.a.eye.skywalking.test.cache;

/**
 * Created by xin on 2016/12/13.
 */
public interface CacheService {

    String findCache(String key);

    void updateCache(String key, String cacheValue);

    String findCacheWithException(String key);
}
