package com.yoxiang.utils;

import com.yoxiang.serialization.FSTSessionSerializer;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: RiversLau
 * Date: 2017/8/11 11:13
 */
public class ShiroCacheTest {

    @Test
    public void testShiroCache() {

        Jedis jedis = new Jedis("39.108.141.144", 6379);
        jedis.auth("zhaoxiang@85&35");
        jedis.select(10);

        byte[] values = jedis.get("authorization_cache".getBytes());
        FSTSessionSerializer serializer = new FSTSessionSerializer();
        Object obj = serializer.deserialize(values);
        if (obj instanceof HashMap) {
            HashMap<String, Object> cache = (HashMap) obj;
            for (Map.Entry entry : cache.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        }

        byte[] values2 = jedis.get("iniRealm.authorizationCache".getBytes());
        Object obj2 = serializer.deserialize(values2);
        if (obj2 instanceof HashMap) {
            HashMap<String, Object> cache = (HashMap) obj2;
            for (Map.Entry entry : cache.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        }

        jedis.close();
    }
}