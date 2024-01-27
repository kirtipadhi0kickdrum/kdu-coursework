package com.kdu.caching.config;

import org.springframework.cache.caffeine.CaffeineCacheManager;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager()
    {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(caffeineCacheBuilder());
        return cacheManager;

    }
    private com.github.benmanes.caffeine.cache.Caffeine<Object, Object> caffeineCacheBuilder() {
        return com.github.benmanes.caffeine.cache.Caffeine.newBuilder()
                .maximumSize(10)
                .recordStats();
    }
}
