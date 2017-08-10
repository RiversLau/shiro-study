package com.yoxiang;

import org.apache.shiro.session.mgt.SimpleSession;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.UUID;

/**
 * Author: RiversLau
 * Date: 2017/8/9 14:46
 */
public class SerializationTest {

    @Test
    public void testKryo() throws FileNotFoundException {

        SimpleSession simpleSession = new SimpleSession();
        simpleSession.setId(UUID.randomUUID().toString());
        simpleSession.setHost("127.0.0.1");
        simpleSession.setTimeout(3600000);
        simpleSession.setAttribute("hello", "world");
        simpleSession.setAttribute("fuck", 139);
        simpleSession.setAttribute("double", 3.58);

        long start = System.currentTimeMillis();

        System.out.println("Kryo deserialize cunsumed " + (System.currentTimeMillis() - start));
    }

    @Test
    public void testJDK() {

        SimpleSession simpleSession = new SimpleSession();
        simpleSession.setId(UUID.randomUUID().toString());
        simpleSession.setHost("127.0.0.1");
        simpleSession.setTimeout(3600000);
        simpleSession.setAttribute("hello", "world");
        simpleSession.setAttribute("fuck", 139);
        simpleSession.setAttribute("double", 3.58);

        long start = System.currentTimeMillis();

        System.out.println("Kryo cunsumed " + (System.currentTimeMillis() - start));
    }

    @Test
    public void testFst() {

        SimpleSession simpleSession = new SimpleSession();
        simpleSession.setId(UUID.randomUUID().toString());
        simpleSession.setHost("127.0.0.1");
        simpleSession.setTimeout(3600000);
        simpleSession.setAttribute("hello", "world");
        simpleSession.setAttribute("fuck", 139);
        simpleSession.setAttribute("double", 3.58);

        long start = System.currentTimeMillis();

        System.out.println("Fst deserialize consumed " + (System.currentTimeMillis() - start));
    }
}
