package com.yoxiang;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: RiversLau
 * Date: 2017/8/10 16:04
 */
public class JedisClusterTest {

    @Test
    public void testJedisCluster() {

        Set<HostAndPort> nodeSet = new HashSet<HostAndPort>();
        nodeSet.add(new HostAndPort("xx.xx.xx.xx", 6379));
        nodeSet.add(new HostAndPort("xx.xx.xx.xx", 6379));

        int connTimeout = 2000;
        int soTimeout = 2000;
        int maxAttempts = 3;
        String password = "zhaoxiang@85&35";

        JedisPoolConfig gPoolConfig = new JedisPoolConfig();
        gPoolConfig.setMaxIdle(100);
        gPoolConfig.setTestOnBorrow(false);

        JedisCluster cluster = new JedisCluster(nodeSet, connTimeout, soTimeout, maxAttempts, password, gPoolConfig);
        cluster.set("Hello", "World");
    }
}
