package com.zhaoxiang.shiro.util;

import org.junit.Test;

/**
 * Author: RiversLau
 * Date: 2017/9/30 10:51
 */
public class StringUtilsTest {

    @Test
    public void testStringUtils() {

        String str1 = null;
        String str2 = "";
        String str3 = "    ";
        String str4 = "a b    c";

        assert StringUtils.hasLength(str1) == false;
        assert StringUtils.hasLength(str2) == false;
        assert StringUtils.hasLength(str3) == true;
        assert StringUtils.hasLength(str4) == true;

        assert StringUtils.hasText(str1) == false;
        assert StringUtils.hasText(str2) == false;
        assert StringUtils.hasText(str3) == false;
        assert StringUtils.hasText(str4) == true;
    }
}
