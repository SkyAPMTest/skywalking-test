package com.a.eye.skywalking.test.cache.h2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xin on 2016/12/13.
 */
@Repository
public class H2ServiceManager {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String QUERY_SQL = "SELECT id,CACHE_VALUE, CACHE_KEY FROM CACHE_TABLE WHERE CACHE_KEY = :cacheKey";
    private static final String INSERT_SQL = "INSERT INTO CACHE_TABLE(CACHE_VALUE, CACHE_KEY) VALUES(:cacheValue, :cacheKey)";

    public String find(String key) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("cacheKey", key);
        List<CacheItem> item = namedParameterJdbcTemplate.query(QUERY_SQL, param, new CacheItemMapper());
        if (item != null && item.size() > 0) {
            return item.get(0).getCacheValue();
        }
        return null;
    }

    public void updateCache(String key, String cacheValue) {
       Map<String, Object> param = new HashMap<String, Object>();
       param.put("cacheKey", key);
       param.put("cacheValue", cacheValue);

       namedParameterJdbcTemplate.update(INSERT_SQL, param);
    }

    public class CacheItemMapper implements RowMapper<CacheItem> {

        public CacheItem mapRow(ResultSet resultSet, int i) throws SQLException {
            CacheItem cacheItem = new CacheItem();
            cacheItem.setId(resultSet.getInt("id"));
            cacheItem.setKey(resultSet.getString("CACHE_KEY"));
            cacheItem.setCacheValue(resultSet.getString("CACHE_VALUE"));
            return cacheItem;
        }
    }
}
