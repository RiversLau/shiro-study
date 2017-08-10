package com.yoxiang.auth;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * Author: RiversLau
 * Date: 2017/7/31 17:52
 */
public class RedisCache<K, V> implements Cache<K, V>, Serializable {

    private final String name;
    private final Map<K, V> map;

    public RedisCache(String name, Map<K, V> backingMap) {

        if (name == null) {
            throw new IllegalArgumentException("Cache name cannot be null.");
        }
        if (backingMap == null) {
            throw new IllegalArgumentException("Backing map cannot be null.");
        }
        this.name = name;
        this.map = backingMap;
    }

    public V get(K key) throws CacheException {

        return map.get(key);
    }

    public V put(K key, V value) throws CacheException {

        return map.put(key, value);
    }

    public V remove(K key) throws CacheException {

        return map.remove(key);
    }

    public void clear() throws CacheException {

        map.clear();
    }

    public int size() {

        return map.size();
    }

    public Set<K> keys() {

        Set<K> keys = map.keySet();
        if (!keys.isEmpty()) {
            return Collections.unmodifiableSet(keys);
        }
        return Collections.emptySet();
    }

    public Collection<V> values() {

        Collection<V> values = map.values();
        if (!CollectionUtils.isEmpty(values)) {
            return Collections.unmodifiableCollection(values);
        }
        return Collections.emptySet();
    }

    public String toString() {

        return new StringBuilder("RedisCache '")
                .append(name).append("' (")
                .append(map.size())
                .append(" entries)")
                .toString();
    }
}
