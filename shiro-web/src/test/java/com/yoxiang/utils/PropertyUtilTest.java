package com.yoxiang.utils;

import org.junit.Test;

/**
 * Author: RiversLau
 * Date: 2017/8/3 11:39
 */
public class PropertyUtilTest {

    @Test
    public void testPropertyUtil() {

        String value = PropertyUtil.getInstance().getPropertyValue("hello");
        System.out.println(value);
    }
}
