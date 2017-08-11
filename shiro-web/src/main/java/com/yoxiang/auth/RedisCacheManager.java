package com.yoxiang.auth;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: RiversLau
 * Date: 2017/7/31 17:33
 */
public class RedisCacheManager implements CacheManager, Destroyable {

    private final Logger logger = LoggerFactory.getLogger(RedisCacheManager.class);

    private RedisManager redisManager;

    public RedisManager getRedisManager() {
        return redisManager;
    }

    public void setRedisManager(RedisManager redisManager) {
        this.redisManager = redisManager;
    }

    public <K, V> Cache<K, V> getCache(String name) throws CacheException {

        logger.info(">>>>>> Try to acquire the cache named " + name);

        RedisCache cache = redisManager.getRedisCache(name);
        if (cache == null) {
            cache = redisManager.createCache(name);
        }
        return cache;
    }

    public void destroy() throws Exception {

        logger.info(">>>>>> Clear caches!");
        redisManager.clear();
    }
}
