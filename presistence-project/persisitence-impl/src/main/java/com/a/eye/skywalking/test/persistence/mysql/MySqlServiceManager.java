package com.a.eye.skywalking.test.persistence.mysql;

import com.a.eye.skywalking.test.persistence.CacheItem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MySqlServiceManager {

    private Logger logger = LogManager.getLogger(MySqlServiceManager.class);

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String TABLE_SCHEMA = "test";
    private static final String TABLE_NAME = "CACHE_TABLE";

    private static final String QUERY_SQL = "SELECT id, CACHE_KEY, CACHE_VALUE FROM CACHE_TABLE WHERE CACHE_KEY= :cacheKey";
    private static final String TABLE_CHECK_SQL = "SELECT * FROM information_schema.tables WHERE table_schema = :table_schema AND table_name = :table_name LIMIT 1;";
    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS `test`.`CACHE_TABLE` (\n" +
            "  `id`    INT         NOT NULL AUTO_INCREMENT,\n" +
            "  `CACHE_KEY`   VARCHAR(30) NOT NULL,\n" +
            "  `CACHE_VALUE` VARCHAR(50) NOT NULL,\n" +
            "  PRIMARY KEY (`id`)\n" +
            ");";

    private static final String DELETE_DATA_SQL = "DELETE FROM `test`.`CACHE_TABLE`\n" +
            "WHERE id = '1';";

    private static final String INSERT_DATA_SQL = "INSERT INTO `test`.`CACHE_TABLE` (`id`, `CACHE_KEY`, `CACHE_VALUE`) VALUES (1, \"test\", \"test\");";

    public CacheItem find(String key) {
        initSchemaAndDataIfNecessary();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("cacheKey", key);
        return namedParameterJdbcTemplate.queryForObject(QUERY_SQL, param, new CacheItemMapper());
    }


    public class CacheItemMapper implements RowMapper<CacheItem> {
        public CacheItem mapRow(ResultSet resultSet, int i) throws SQLException {
            CacheItem cacheItem = new CacheItem();
            cacheItem.setId(resultSet.getInt("id"));
            cacheItem.setKey(resultSet.getString("CACHE_KEY"));
            cacheItem.setCacheValue(resultSet.getString("CACHE_VALUE"));
            logger.info("Item id: {} Item key: {} Item value: {}", cacheItem.getId(), cacheItem.getKey(), cacheItem.getCacheValue());
            return cacheItem;
        }
    }

    public void initSchemaAndDataIfNecessary() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("table_schema", TABLE_SCHEMA);
        param.put("table_name", TABLE_NAME);
        List<Map<String, Object>> table = namedParameterJdbcTemplate.queryForList(TABLE_CHECK_SQL, param);
        logger.info("table is {}.", table== null || table.size() == 0 ? "exists":"not exists");
        if (table == null || table.size() == 0) {
            logger.info("Create tables.......");
            createTable();
            initData();
        }
    }

    private void initData() {
        namedParameterJdbcTemplate.update(DELETE_DATA_SQL, new HashMap<String, Object>());
        namedParameterJdbcTemplate.update(INSERT_DATA_SQL, new HashMap<String, Object>());
    }

    private void createTable() {
        namedParameterJdbcTemplate.update(CREATE_TABLE_SQL, new HashMap<String, Object>());
    }
}
