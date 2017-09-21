package com.yoxiang.security;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import java.io.Serializable;
import java.util.Collection;

/**
 * 自定义SessionDAO
 * Author: RiversLau
 * Date: 2017/8/8 10:11
 */
public class LzxSessionDAO extends AbstractSessionDAO {

    private LzxSessionRedisDAO lzxSessionRedisDAO;

    public LzxSessionDAO(LzxSessionRedisDAO lzxSessionRedisDAO) {
        this.lzxSessionRedisDAO = lzxSessionRedisDAO;
    }

    /**
     * 创建Session
     * @param session
     * @return
     */
    protected Serializable doCreate(Session session) {

        Serializable sessionID = generateSessionId(session);
        assignSessionId(session, sessionID);

        lzxSessionRedisDAO.put(session);
        return sessionID;
    }

    /**
     * 读取Session
     * @param sessionId
     * @return
     */
    protected Session doReadSession(Serializable sessionId) {

        return lzxSessionRedisDAO.readSession(sessionId);
    }

    /**
     * 更新Session
     * @param session
     * @throws UnknownSessionException
     */
    public void update(Session session) throws UnknownSessionException {

        lzxSessionRedisDAO.put(session);
    }

    /**
     * 删除Session
     * @param session
     */
    public void delete(Session session) {

        lzxSessionRedisDAO.delete(session);
    }

    /**
     * 获取所有活跃的Session
     * @return
     */
    public Collection<Session> getActiveSessions() {

        return lzxSessionRedisDAO.listActives();
    }
}
