package com.zzong.egovframework.web.cache;

import com.zzong.egovframework.cmmn.cache.service.CacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
@RequiredArgsConstructor
public class CacheController {
    private final CacheService cacheService;
    
    @GetMapping("/get/{key}")
    public String getFromCache(@PathVariable String key) {
        return cacheService.get(key);
    }
    
    @GetMapping("/put/{key}/{value}")
    public String putToCache(@PathVariable String key, @PathVariable String value) {
        cacheService.put(key, value);
        return cacheService.get(key);
    }
    
    @GetMapping("/name")
    public String getCacheInfo() {
        return cacheService.cacheName();
    }
}
