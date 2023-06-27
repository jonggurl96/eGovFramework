package com.zzong.egovframework.cmmn.cache.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CacheServiceImpl implements CacheService {
    
    @Resource(name = "cache")
    private Cache cache;
    
    @Override
    public void put(String key, String value) {
        cache.put(key, value);
    }
    
    @Override
    public String get(String key) {
        Cache.ValueWrapper wrapper = Objects.requireNonNull(cache).get(key);
        return (String)(Objects.requireNonNull(wrapper).get());
    }
    
    @Override
    public String cacheName() {
        return cache.getName();
    }
}
