package com.zhaoxiang.shiro.util;

import com.zhaoxiang.shiro.subject.PrincipalCollection;

import java.util.*;

/**
 * Author: RiversLau
 * Date: 2017/9/30 9:33
 */
public class CollectionUtils {

    public static <E> Set<E> asSet(E... elements) {
        if (elements == null || elements.length == 0) {
            return Collections.emptySet();
        }
        if (elements.length == 1) {
            return Collections.singleton(elements[0]);
        }

        LinkedHashSet<E> set = new LinkedHashSet<E>(elements.length * 4 / 3 + 1);
        Collections.addAll(set, elements);
        return set;
    }

    public static <E> List<E> asList(E... elements) {
        if (elements == null || elements.length == 0) {
            return Collections.emptyList();
        }
        return Arrays.asList(elements);
    }

    public static boolean isEmpty(Collection c) {
        return c == null || c.isEmpty();
    }

    public static boolean isEmpty(Map m) {
        return m == null || m.isEmpty();
    }

    public static int size(Collection c) {
        return c != null ? c.size() : 0;
    }

    public static int size(Map m) {
        return m != null ? m.size() : 0;
    }

    public static boolean isEmpty(PrincipalCollection principals) {
        return principals == null || principals.isEmpty();
    }

    static int computeListCapacity(int arraySize) {
        return (int) Math.min(5L + arraySize + (arraySize / 10), Integer.MAX_VALUE);
    }
}
