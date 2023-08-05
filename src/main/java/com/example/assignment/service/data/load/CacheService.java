package com.example.assignment.service.data.load;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Log4j2
@Service
public class CacheService<T>{

    @PostConstruct
    public void init() {
        cache =
        CacheBuilder.newBuilder()
        .expireAfterAccess(30, TimeUnit.MINUTES)
        .build(new CacheLoader<>() {
            @Override
            public T load(String key) throws Exception {
                return null;
            }
        });
    }

    private LoadingCache<String, T> cache;

    public T getById(String id) {
        log.info("get cache by id = {}", id);
        return cache.getIfPresent(id);
    }

    public long getCacheSize() {
        var size = cache.size();
        log.info("Get cache size = {}", size);
        return size;
    }

    public List<T> getCache() {
        var cachedData = cache.asMap().values().stream().toList();
        log.info("Get cached data: size = {}", cachedData.size());
        return cachedData;
    }

    public void setCache(String key, T value) {
        log.info("set cache key {} players", key);
        cache.put(key, value);
    }


}
