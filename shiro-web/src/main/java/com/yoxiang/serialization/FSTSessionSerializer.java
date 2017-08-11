package com.yoxiang.serialization;

import org.apache.shiro.session.mgt.SimpleSession;
import org.nustaq.serialization.FSTConfiguration;

/**
 * 基于fast-serialization的序列化、反序列化实现
 * Author: RiversLau
 * Date: 2017/8/10 10:43
 */
public class FSTSessionSerializer implements SessionSerializer {

    private static FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();

    public FSTSessionSerializer() {
        conf.registerClass(SimpleSession.class);
    }

    public byte[] serialize(Object object) {

        return conf.asByteArray(object);
    }

    public Object deserialize(byte[] bytes) {

        return conf.asObject(bytes);
    }
}
