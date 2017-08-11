package com.yoxiang.auth;

import com.yoxiang.serialization.FSTSessionSerializer;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;
import redis.clients.jedis.Jedis;

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

        Jedis jedis = new Jedis("39.108.141.144", 6379);
        jedis.auth("zhaoxiang@85&35");

        V result = map.put(key, value);

        // 更新Redis缓存数据
        FSTSessionSerializer serializer = new FSTSessionSerializer();
        byte[] bytes = serializer.serialize(map);
        jedis.set(name.getBytes(), bytes);
        jedis.close();
        return result;
    }

    public V remove(K key) throws CacheException {

        V result = map.remove(key);

        // 更新Redis缓存数据
        Jedis jedis = new Jedis("39.108.141.144", 6379);
        jedis.auth("zhaoxiang@85&35");
        FSTSessionSerializer serializer = new FSTSessionSerializer();
        byte[] bytes = serializer.serialize(map);
        jedis.set(name.getBytes(), bytes);
        jedis.close();

        return result;
    }

    public void clear() throws CacheException {

        map.clear();

        // 更新Redis缓存数据
        Jedis jedis = new Jedis("39.108.141.144", 6379);
        jedis.auth("zhaoxiang@85&35");
        FSTSessionSerializer serializer = new FSTSessionSerializer();
        byte[] bytes = serializer.serialize(map);
        jedis.set(name.getBytes(), bytes);
        jedis.close();
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
