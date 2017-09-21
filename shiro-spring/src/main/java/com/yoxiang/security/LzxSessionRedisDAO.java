package com.yoxiang.security;

import com.yoxiang.serialization.SessionSerializer;
import org.apache.shiro.session.Session;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Author: RiversLau
 * Date: 2017/8/8 11:00
 */
public class LzxSessionRedisDAO {

    private static final String SESSION_PREFIX = "SESSION:";

    /**Session默认过期时间，-1表示永不过期*/
    private static final int DEFAULT_SESSION_TIMEOUT = -1;

    private JedisPool jedisPool;

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**Session序列化实现器*/
    private SessionSerializer sessionSerializer;

    public void setSessionSerializer(SessionSerializer sessionSerializer) {
        this.sessionSerializer = sessionSerializer;
    }

    /**
     * session有效时间，单位秒
     */
    private int sessionTimeout = DEFAULT_SESSION_TIMEOUT;

    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
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
            if (DEFAULT_SESSION_TIMEOUT != sessionTimeout) {
                conn.setex(sessionKey.getBytes(), sessionTimeout, sessionSerializer.serialize(session));
            }
            conn.set(sessionKey.getBytes(), sessionSerializer.serialize(session));
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
                Session session = (Session) sessionSerializer.deserialize(sessionBytes);
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

        List<Session> sessionList = new ArrayList<Session>();

        Jedis conn = null;
        try {
            conn = jedisPool.getResource();
            Set<String> keySet = conn.keys(SESSION_PREFIX + "*");
            for (String key : keySet) {
                byte[] bytes = conn.get(key.getBytes());
                if (bytes != null) {
                    Session session = (Session) sessionSerializer.deserialize(bytes);
                    sessionList.add(session);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        return sessionList;
    }
}
