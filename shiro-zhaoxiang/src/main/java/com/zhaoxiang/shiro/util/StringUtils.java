package com.zhaoxiang.shiro.util;

import java.util.Collection;
import java.util.Iterator;

/**
 * Author: RiversLau
 * Date: 2017/9/30 10:37
 */
public class StringUtils {

    /**
     * 空字符串
     */
    public static final String EMPTY_STRING = "";

    /**
     * 默认分隔符
     */
    public static final char DEFAULT_DELIMITER_CHAR = ',';

    /**
     * 默认引用符
     */
    public static final char DEFAULT_QUOTE = '"';

    /**
     * 如果字符串不为null，且长度大于0，且包含至少一个非空字符就返回true
     * <code>
     *     StringUtils.hasText(null) == false
     *     StringUtils.hasText("") == false
     *     StringUtils.hasText("    ") == false
     *     StringUtils.hasText("abc") == true
     *     StringUtils.hasText(" abc ") == true
     * </code>
     * @param str
     * @return
     */
    public static boolean hasText(String str) {
        if (!hasLength(str)) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串是否为null或长度为0
     * <code>
     *     StringUtils.hasLength(null) == false
     *     StringUtils.hasLength("") == false
     *     StringUtils.hasLength("   ") == true
     *     StringUtils.hasLength("abc") == true
     * </code>
     * @param str
     * @return
     */
    public static boolean hasLength(String str) {
        return str != null && str.length() > 0;
    }

    /**
     * 判断字符串是否已某个前缀开头，忽略大小写
     * @param str 待校验的字符串
     * @param prefix 前缀
     * @return
     */
    public static boolean startsWithIgnoreCase(String str, String prefix) {
        if (str == null || prefix == null) {
            return false;
        }
        if (str.startsWith(prefix)) {
            return true;
        }
        if (str.length() < prefix.length()) {
            return false;
        }
        String lcStr = str.substring(0, prefix.length()).toLowerCase();
        String lcPrefix = prefix.toLowerCase();
        return lcStr.equalsIgnoreCase(lcPrefix);
    }

    /**
     * 返回已整理的字符串，已整理代表意义如下：
     * 如果in为null，则out为null；如果in不为null，则进行trim操作，如果trim后的结果为空字符，则out为null，否则
     * 返回trim后的字符串
     * @param in
     * @return
     */
    public static String clean(String in) {
        String out = in;
        if (in != null) {
            out = in.trim();
            if (EMPTY_STRING.equals(out)) {
                out = null;
            }
        }
        return out;
    }

    /**
     * 将数组转换为字符串，元素用','分隔
     * @param array
     * @return
     */
    public static String toString(Object[] array) {
        return toDelimitedString(array, ",");
    }

    /**
     * 将数组转换为字符串，元素用指定的分隔符来分隔
     * @param array 待转换的数组
     * @param delimiter 分隔符
     * @return
     */
    public static String toDelimitedString(Object[] array, String delimiter) {
        if (array == null || array.length == 0) {
            return EMPTY_STRING;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (i > 0) {
                builder.append(delimiter);
            }
            builder.append(array[i]);
        }
        return builder.toString();
    }

    /**
     * 将集合转换为字符串，元素用指定的分隔符分隔
     * @param c 带转换的集合
     * @param delimiter 分隔符
     * @return
     */
    public static String toDelimitedString(Collection c, String delimiter) {
        if (c == null || c.isEmpty()) {
            return EMPTY_STRING;
        }
        return join(c.iterator(), delimiter);
    }

    public static String join(Iterator<?> iterator, String delimiter) {
        final String empty = "";
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return empty;
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return first == null ? empty : first.toString();
        }
        StringBuilder builder = new StringBuilder();
        if (first != null) {
            builder.append(first);
        }
        while (iterator.hasNext()) {
            if (delimiter != null) {
                builder.append(delimiter);
            }
            Object obj = iterator.next();
            if (obj != null) {
                builder.append(obj);
            }
        }
        return builder.toString();
    }
}
