package com.yoxiang.security;

import com.yoxiang.serialization.SessionSerializer;

/**
 * 集群Session存储实现
 * Author: RiversLau
 * Date: 2017/8/10 15:42
 */
public class LzxClusterSessionRedisDAO {

    private static final String SESSION_PREFIX = "SESSION:";

    /**Session默认过期时间，-1表示永不过期*/
    private static final int DEFAULT_SESSION_TIMEOUT = -1;

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


}
