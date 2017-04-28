package com.a.eye.skywalking.test.cache.h2;

/**
 * Created by xin on 2016/12/13.
 */
public class CacheItem {
    private String cacheValue;
    private int id;
    private String key;

    public String getCacheValue() {
        return cacheValue;
    }

    public void setCacheValue(String cacheValue) {
        this.cacheValue = cacheValue;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
