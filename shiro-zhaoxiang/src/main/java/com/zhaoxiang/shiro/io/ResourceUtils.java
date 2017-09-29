package com.zhaoxiang.shiro.io;

import com.zhaoxiang.shiro.util.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Author: RiversLau
 * Date: 2017/9/29 16:35
 */
public class ResourceUtils {

    public static final String CLASSPATH_PREFIX = "classpath:";

    public static final String URL_PREFIX = "url:";

    public static final String FILE_PREFIX = "file:";

    private static final Logger log = LoggerFactory.getLogger(ResourceUtils.class);

    private ResourceUtils() {}

    public static boolean hasResourcePrefix(String resourcePath) {
        return resourcePath != null &&
                (resourcePath.startsWith(CLASSPATH_PREFIX) ||
                        resourcePath.startsWith(URL_PREFIX) ||
                        resourcePath.startsWith(FILE_PREFIX));
    }

    public static boolean resourceExists(String resourcePath) {

        InputStream stream = null;
        boolean exists = false;

        try {
            stream = getInputStreamFromPath(resourcePath);
            exists = true;
        } catch (Exception e) {
            stream = null;
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return exists;
    }

    private static InputStream getInputStreamFromPath(String resourcePath) {

        InputStream is = null;
        if (resourcePath.startsWith(CLASSPATH_PREFIX)) {
            is = loadFromClassPath(stripPrefix(CLASSPATH_PREFIX));
        } else if (resourcePath.startsWith(URL_PREFIX)) {
//            is = ;
        } else if (resourcePath.startsWith(FILE_PREFIX)) {
//            is = ;
        } else {
//            is = ;
        }
        return is;
    }

    private static InputStream loadFromClassPath(String path) {
        log.debug("Opening resource from class path [{}]", path);
        return ClassUtils.getResourceAsStream(path);
    }

    private static String stripPrefix(String resourcePath) {
        return resourcePath.substring(resourcePath.indexOf(":") + 1);
    }
}
