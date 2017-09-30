package com.zhaoxiang.shiro.subject;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Author: RiversLau
 * Date: 2017/9/30 10:11
 */
public interface PrincipalCollection extends Iterable, Serializable {

    Object getPrimaryPrincipal();

    <T> T oneByType(Class<T> type);

    <T> Collection<T> byType(Class<T> type);

    List asList();

    Set asSet();

    Collection fromRealm(String realmName);

    Set<String> getRealmNames();

    boolean isEmpty();
}
