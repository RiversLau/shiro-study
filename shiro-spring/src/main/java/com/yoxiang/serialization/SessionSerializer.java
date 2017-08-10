package com.yoxiang.serialization;

/**
 * Author: RiversLau
 * Date: 2017/8/10 10:41
 */
public interface SessionSerializer {

    byte[] serialize(Object object);

    Object deserialize(byte[] bytes);
}
