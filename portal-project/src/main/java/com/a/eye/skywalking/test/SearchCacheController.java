package com.a.eye.skywalking.test;

import com.a.eye.skywalking.test.cache.CacheService;
import com.a.eye.skywalking.test.persistence.CacheItem;
import com.a.eye.skywalking.test.persistence.PersistenceService;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cache")
public class SearchCacheController {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private PersistenceService persistenceService;

    private static Map<String, String> forTest = new ConcurrentHashMap<>();

    static {
        for (int i = 0; i < 10000000; i++) {
            forTest.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }
    }

    @RequestMapping(value = "/{key}")
    public ModelAndView search(@PathVariable String key) {
        Map<String, String> forTest = new ConcurrentHashMap<String, String>();
        for (int i = 0; i < 10000; i++) {
            forTest.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        }

        String cacheValue = cacheService.findCache(key);
        if (cacheValue == null || cacheValue.length() == 0) {
            CacheItem cacheItem = persistenceService.query(key);
            cacheValue = cacheItem.getCacheValue();
        }
        cacheService.updateCache(key + "_NEW", cacheValue);
        ModelAndView modelAndView = new ModelAndView("resultPage");
        modelAndView.addObject("cacheValue", cacheValue);
        modelAndView.addObject("traceId", TraceContext.traceId());
        return modelAndView;
    }

    @RequestMapping(value = "/exception/{key}")
    public ModelAndView searchWithException(@PathVariable String key) {
        String cacheValue = cacheService.findCacheWithException(key);
        if (cacheValue == null || cacheValue.length() == 0) {
            CacheItem cacheItem = persistenceService.query(key);
            cacheValue = cacheItem.getCacheValue();
        }
        cacheService.updateCache(key + "_NEW", cacheValue);
        ModelAndView modelAndView = new ModelAndView("resultPage");
        modelAndView.addObject("cacheValue", cacheValue);
        modelAndView.addObject("traceId", TraceContext.traceId());
        return modelAndView;
    }
}
