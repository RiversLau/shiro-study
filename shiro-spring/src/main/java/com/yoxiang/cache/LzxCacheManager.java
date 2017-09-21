package com.yoxiang.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * Author: RiversLau
 * Date: 2017/8/11 10:10
 */
public class LzxCacheManager implements CacheManager {

    // 基于Redis的缓存存取DAO
    private LzxCacheRedisDAO lzxCacheRedisDAO;

    public void setLzxCacheRedisDAO(LzxCacheRedisDAO lzxCacheRedisDAO) {
        this.lzxCacheRedisDAO = lzxCacheRedisDAO;
    }

    public <K, V> Cache<K, V> getCache(String name) throws CacheException {

        Cache<K, V> cache = lzxCacheRedisDAO.doGetCache(name);
        if (cache == null) {
            cache = lzxCacheRedisDAO.doCreateCache(name);
        }
        return cache;
    }
}
