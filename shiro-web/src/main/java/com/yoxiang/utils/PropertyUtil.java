package com.yoxiang.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Author: RiversLau
 * Date: 2017/8/3 11:30
 */
public class PropertyUtil {

    private static final String PROPERTY_FILE_NAME = "system.properties";

    private static PropertyUtil instance;
    private Properties properties;

    private PropertyUtil() {

        InputStream stream = getClass().getResourceAsStream(PROPERTY_FILE_NAME);
        properties = new Properties();
        try {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropertyUtil getInstance() {

        if (instance == null) {
            instance = new PropertyUtil();
        }
        return instance;
    }

    public String getPropertyValue(String key) {

        return properties.getProperty(key);
    }
}