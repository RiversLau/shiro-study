package com.yoxiang.auth;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: RiversLau
 * Date: 2017/7/31 17:40
 */
public class RedisManager {

    private Jedis jedis;

    public RedisManager() {

        jedis = new Jedis("39.108.141.144", 6379);
        jedis.auth("foobared");
    }

    public <K, V> RedisCache<K, V> getRedisCache(String name) {

        boolean flag = jedis.exists(name);
        if (flag) {
            String value = jedis.get(name);
            Map<Object, Object> cache = new HashMap<Object, Object>();
            cache.put(name, value);
            return new RedisCache(name, cache);
        }

        return null;
    }

    public <K, V> RedisCache<K, V> createCache(String name) {

        jedis.set(name, "");
        Map<String, Object> map = new HashMap();
        map.put(name, "");
        return new RedisCache(name, map);
    }

    public void clear() {


    }
}
