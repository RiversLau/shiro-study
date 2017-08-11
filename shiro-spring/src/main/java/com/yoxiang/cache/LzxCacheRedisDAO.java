package com.yoxiang.cache;

import com.yoxiang.serialization.SessionSerializer;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.Serializable;
import java.util.*;

/**
 * Author: RiversLau
 * Date: 2017/8/10 17:50
 */
public class LzxCacheRedisDAO {

    private JedisPool jedisPool;

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**Session序列化实现器*/
    private SessionSerializer sessionSerializer;

    public void setSessionSerializer(SessionSerializer sessionSerializer) {
        this.sessionSerializer = sessionSerializer;
    }

    public <K, V> Cache<K, V> doGetCache(String name) {

        Jedis conn = null;
        try {
            conn = jedisPool.getResource();
            byte[] cacheBytes = conn.get(name.getBytes());
            if (cacheBytes != null) {
                Map cacheMap = (Map) sessionSerializer.deserialize(cacheBytes);
                return new LzxCache<K, V>(name, cacheMap);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    public <K, V> Cache<K, V> doCreateCache(String name) {

        Map<K, V> cacheMap = new HashMap<K, V>();
        byte[] cacheBytes = sessionSerializer.serialize(cacheMap);

        Jedis conn = null;
        try {
            conn = jedisPool.getResource();
            conn.set(name.getBytes(), cacheBytes);
            return new LzxCache<K, V>(name, cacheMap);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    /**
     * 内部类
     * @param <K>
     * @param <V>
     */
    public class LzxCache<K, V> implements Cache<K, V>, Serializable {

        // Cache名称
        private final String name;
        // Cache存放的数据，使用Map来存储
        private final Map<K, V> map;

        public LzxCache(String name, Map<K, V> backingMap) {

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

            V result = map.put(key, value);

            updateCache();
            return result;
        }

        public V remove(K key) throws CacheException {

            V result = map.remove(key);

            updateCache();
            return result;
        }

        public void clear() throws CacheException {

            map.clear();
            updateCache();
        }

        public int size() {

            // TODO 是否有必要获取Redis上的数据，然后进行比对再返回呢？
            // 个人感觉只要保证put/remove/clear三个方法能够操作成功，就能够保证其他操作的正确性
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

            return new StringBuilder("LzxCache '").append(name).append("' (")
                    .append(map.size()).append(" entries)").toString();
        }

        /**
         * 更新Cache数据
         */
        private synchronized void updateCache() {

            Jedis conn = null;
            try {
                conn = jedisPool.getResource();
                byte[] cacheBytes = sessionSerializer.serialize(map);
                conn.set(name.getBytes(), cacheBytes);
            } finally {
                if (conn != null) {
                    conn.close();
                }
            }
        }
    }
}
