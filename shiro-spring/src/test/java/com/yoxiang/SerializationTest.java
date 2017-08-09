package com.yoxiang;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import com.yoxiang.utils.LzxSerializationUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.junit.Test;
import org.objenesis.strategy.StdInstantiatorStrategy;
import redis.clients.jedis.Jedis;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 * Author: RiversLau
 * Date: 2017/8/9 14:46
 */
public class SerializationTest {

    @Test
    public void testKryo() throws FileNotFoundException {

        Jedis conn = new Jedis("39.108.141.144", 6379);
        conn.auth("zhaoxiang@85&35");
        conn.select(10);

        SimpleSession simpleSession = new SimpleSession();
        simpleSession.setId(UUID.randomUUID().toString());
        simpleSession.setHost("127.0.0.1");
        simpleSession.setTimeout(3600000);
        simpleSession.setAttribute("hello", "world");
        simpleSession.setAttribute("fuck", 139);
        simpleSession.setAttribute("double", 3.58);

        long start = System.currentTimeMillis();

        Kryo kryo = new Kryo();
        kryo.register(SimpleSession.class, new JavaSerializer());
        Output output = new Output(new FileOutputStream("file.bin"));
        kryo.writeObject(output, simpleSession);

        byte[] value = output.getBuffer();
        output.flush();
        output.close();
        System.out.println("Kryo Serialize consumed " + (System.currentTimeMillis() - start));

        conn.set("test".getBytes(), value);

        byte[] bytes = conn.get("test".getBytes());

        start = System.currentTimeMillis();

        Input input = new Input(bytes);
        SimpleSession session = kryo.readObject(input, SimpleSession.class);
        System.out.println(session);
        System.out.println("Kryo deserialize cunsumed " + (System.currentTimeMillis() - start));

        conn.close();
    }

    @Test
    public void testJDK() {

        Jedis conn = new Jedis("39.108.141.144", 6379);
        conn.auth("zhaoxiang@85&35");
        conn.select(10);

        SimpleSession simpleSession = new SimpleSession();
        simpleSession.setId(UUID.randomUUID().toString());
        simpleSession.setHost("127.0.0.1");
        simpleSession.setTimeout(3600000);
        simpleSession.setAttribute("hello", "world");
        simpleSession.setAttribute("fuck", 139);
        simpleSession.setAttribute("double", 3.58);

        long start = System.currentTimeMillis();
        byte[] bytes = LzxSerializationUtil.serialize(simpleSession);
        System.out.println("JDK serialize cunsumed " + (System.currentTimeMillis() - start));
        conn.set("test-jdk".getBytes(), bytes);

        byte[] value = conn.get("test-jdk".getBytes());

        start = System.currentTimeMillis();
        Session session = (Session) LzxSerializationUtil.deserialize(value);
        System.out.println(session);
        System.out.println("Kryo cunsumed " + (System.currentTimeMillis() - start));

        conn.close();
    }
}
