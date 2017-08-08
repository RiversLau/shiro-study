package com.zhaoxiang.security;

import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.Serializable;
import java.util.Collection;

/**
 * Author: RiversLau
 * Date: 2017/8/8 11:00
 */
public class LzxRedisSessionDAO {

    private static final String SESSION_PREFIX = "TOKEN:";

    @Autowired
    private JedisPool jedisPool;

    /**
     * 将Session存储到Redis
     * @param session
     */
    public void put(Session session) {

        Jedis conn = jedisPool.getResource();
        conn.set("test", "world");
    }

    /**
     * 从Redis读取Session
     * @param sessionID
     */
    public Session readSession(Serializable sessionID) {

        return null;
    }

    /**
     * 将Session从Redis中删除
     * @param session
     */
    public void delete(Session session) {

    }

    /**
     * 获取Redis中未过期的Session
     * @return
     */
    public Collection<Session> listActives() {

        return null;
    }
}
