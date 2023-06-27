package com.zzong.egovframework.cmmn.cache.service;

public interface CacheService {
    public void put(String key, String value);
    public String get(String key);
    public String cacheName();
}
