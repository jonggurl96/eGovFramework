package com.zzong.egovframework.cmmn.cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class CacheConfig {
    
    @Bean("ehcache")
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean bean =  new EhCacheManagerFactoryBean();
        bean.setConfigLocation(new ClassPathResource("cmmn/cache/cache.xml"));
        return bean;
    }
    
    @Bean("cacheManager")
    @SuppressWarnings("all")
    public EhCacheCacheManager ehCacheCacheManager() {
        return new EhCacheCacheManager(ehCacheManagerFactoryBean().getObject());
    }
    
    @Value("${cache.name}")
    private String cacheName;
    
    @Bean("cache")
    public Cache cache() {
        return ehCacheCacheManager().getCache(cacheName);
    }
}
