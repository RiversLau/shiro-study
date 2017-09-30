package com.zhaoxiang.shiro.config;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Author: RiversLau
 * Date: 2017/9/29 16:22
 */
public class Ini implements Map<String, Ini.Section> {

    public int size() {
        return 0;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean containsKey(Object key) {
        return false;
    }

    public boolean containsValue(Object value) {
        return false;
    }

    public Section get(Object key) {
        return null;
    }

    public Section put(String key, Section value) {
        return null;
    }

    public Section remove(Object key) {
        return null;
    }

    public void putAll(Map<? extends String, ? extends Section> m) {

    }

    public void clear() {

    }

    public Set<String> keySet() {
        return null;
    }

    public Collection<Section> values() {
        return null;
    }

    public Set<Entry<String, Section>> entrySet() {
        return null;
    }

    public boolean equals(Object o) {
        return false;
    }

    public int hashCode() {
        return 0;
    }

    public static class Section implements Map<String, String> {

        private final String name;
        private final Map<String, String> props;

        private Section(String name) {
            if (name == null) {
                throw new NullPointerException("name");
            }
            this.name = name;
            this.props = new LinkedHashMap<String, String>();
        }

        private Section(String name, String sectionContent) {
            if (name == null) {
                throw new NullPointerException("name");
            }
            this.name = name;
            Map<String, String> props;
            this.props = new LinkedHashMap<String, String>();
        }

        public int size() {
            return 0;
        }

        public boolean isEmpty() {
            return false;
        }

        public boolean containsKey(Object key) {
            return false;
        }

        public boolean containsValue(Object value) {
            return false;
        }

        public String get(Object key) {
            return null;
        }

        public String put(String key, String value) {
            return null;
        }

        public String remove(Object key) {
            return null;
        }

        public void putAll(Map<? extends String, ? extends String> m) {

        }

        public void clear() {

        }

        public Set<String> keySet() {
            return null;
        }

        public Collection<String> values() {
            return null;
        }

        public Set<Entry<String, String>> entrySet() {
            return null;
        }
    }
}
