package com.yoxiang.security;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * Author: RiversLau
 * Date: 2017/8/10 17:49
 */
public class LzxCacheManager implements CacheManager {

    private LzxCacheRedisDAO lzxCacheRedisDAO;

    public LzxCacheManager(LzxCacheRedisDAO lzxCacheRedisDAO) {
        this.lzxCacheRedisDAO = lzxCacheRedisDAO;
    }

    public <K, V> Cache<K, V> getCache(String name) throws CacheException {

        return null;
    }
}
