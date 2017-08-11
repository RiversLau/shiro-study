package com.yoxiang.auth;

import com.yoxiang.serialization.FSTSessionSerializer;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

import static com.sun.corba.se.impl.util.RepositoryId.cache;

/**
 * Author: RiversLau
 * Date: 2017/7/31 17:40
 */
public class RedisManager {

    private Jedis jedis;

    public RedisManager() {

        jedis = new Jedis("39.108.141.144", 6379);
        jedis.auth("zhaoxiang@85&35");
    }

    public <K, V> RedisCache<K, V> getRedisCache(String name) {

        boolean flag = jedis.exists(name.getBytes());
        if (flag) {
            byte[] value = jedis.get(name.getBytes());
            FSTSessionSerializer serializer = new FSTSessionSerializer();
            Map cacheMap = (HashMap) serializer.deserialize(value);
            return new RedisCache(name, cacheMap);
        }
        return null;
    }

    public <K, V> RedisCache<K, V> createCache(String name) {

        Map<String, Object> map = new HashMap<String, Object>();
        FSTSessionSerializer serializer = new FSTSessionSerializer();
        byte[] bytes = serializer.serialize(map);
        jedis.set(name.getBytes(), bytes);
        return new RedisCache(name, map);
    }

    public void clear() {


    }
}
