package com.yoxiang.serialization;

import java.io.*;

/**
 * 基于JDK输入输出流的序列化、反序列化实现
 * ByteArrayInputStream、ByteArrayOutputStream、ObjectInputStream、ObjectOutputStream
 *
 * Author: RiversLau
 * Date: 2017/8/10 10:42
 */
public class JDKSessionSerializer implements SessionSerializer {

    /**
     * 序列化
     * @param object
     * @return
     */
    public byte[] serialize(Object object) {

        if (object == null) {
            return null;
        }

        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            oos.flush();
            bytes = bos.toByteArray ();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;
    }

    /**
     * 反序列化
     * @param bytes
     * @return
     */
    public Object deserialize(byte[] bytes) {

        if (bytes == null) {
            return null;
        }

        Object obj = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            bis = new ByteArrayInputStream (bytes);
            ois = new ObjectInputStream (bis);
            obj = ois.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }
}
