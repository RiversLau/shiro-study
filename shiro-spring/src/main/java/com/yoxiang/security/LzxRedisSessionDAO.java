package com.yoxiang.security;

import com.yoxiang.utils.LzxSerializationUtil;
import org.apache.shiro.session.Session;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import java.io.Serializable;
import java.util.Collection;

/**
 * Author: RiversLau
 * Date: 2017/8/8 11:00
 */
public class LzxRedisSessionDAO {

    private static final String SESSION_PREFIX = "SESSION:";

    private JedisPool jedisPool;

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 将Session存储到Redis
     * @param session
     */
    public void put(Session session) {

        String sessionKey = SESSION_PREFIX + session.getId();

        Jedis conn = null;
        try {
            conn = jedisPool.getResource();
            conn.setex(sessionKey.getBytes(), 100, LzxSerializationUtil.serialize(session));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     * 从Redis读取Session
     * @param sessionID
     */
    public Session readSession(Serializable sessionID) {

        String sessionKey = SESSION_PREFIX + sessionID;

        Jedis conn = null;
        try {
            conn = jedisPool.getResource();
            byte[] sessionBytes = conn.get(sessionKey.getBytes());
            if (sessionBytes != null) {
                Session session = (Session) LzxSerializationUtil.deserialize(sessionBytes);
                return session;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Fetch Redis connection failed！Exception msg ：" + e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     * 将Session从Redis中删除
     * @param session
     */
    public void delete(Session session) {

        String sessionKey = SESSION_PREFIX + session.getId();

        Jedis conn = null;
        try {
            conn = jedisPool.getResource();
            conn.del(sessionKey.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     * 获取Redis中未过期的Session
     * @return
     */
    public Collection<Session> listActives() {

        return null;
    }
}
