package com.yoxiang;

import com.yoxiang.serialization.FSTSessionSerializer;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * Author: RiversLau
 * Date: 2017/9/21 10:07
 */
public class CacheSerializationTest {

    @Test
    public void testCacheSerialization() {

        Jedis jedis = new Jedis("119.23.26.77", 6379);
        jedis.auth("zhaoxiang@85&35");
        jedis.select(10);

        byte[] authcBytes = jedis.get("authentication_cache".getBytes());
        FSTSessionSerializer fstSessionSerializer = new FSTSessionSerializer();
        Map cache = (Map) fstSessionSerializer.deserialize(authcBytes);
        System.out.println(cache);

        byte[] authzBytes = jedis.get("authorization_cache".getBytes());
        Map authzCache = (Map) fstSessionSerializer.deserialize(authzBytes);
        System.out.println(authzCache);

        jedis.close();
    }
}
