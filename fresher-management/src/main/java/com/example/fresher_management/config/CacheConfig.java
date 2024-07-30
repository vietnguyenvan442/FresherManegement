package com.example.fresher_management.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public JCacheCacheManager cacheManager() throws URISyntaxException, IOException {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        URI uri = new ClassPathResource("ehcache.xml").getURI();
        javax.cache.CacheManager cacheManager = cachingProvider.getCacheManager(uri, getClass().getClassLoader());
        return new JCacheCacheManager(cacheManager);
    }
}
