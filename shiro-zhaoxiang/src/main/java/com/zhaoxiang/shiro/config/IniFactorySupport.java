package com.zhaoxiang.shiro.config;

import com.zhaoxiang.shiro.util.AbstractFactory;
import com.zhaoxiang.shiro.io.ResourceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: RiversLau
 * Date: 2017/9/29 16:18
 */
public abstract class IniFactorySupport<T> extends AbstractFactory<T> {

    public static final String DEFAULT_INI_RESOURCE_PATH = "classpath:shiro.ini";

    private static transient final Logger log = LoggerFactory.getLogger(IniFactorySupport.class);

    private Ini ini;

    /**
     * 注意protected修饰符
     */
    protected IniFactorySupport() {
    }

    protected IniFactorySupport(Ini ini) {
        this.ini = ini;
    }

    public Ini getIni() {
        return ini;
    }

    public void setIni(Ini ini) {
        this.ini = ini;
    }

    public static Ini loadDefaultClassPathIni() {

        Ini ini = null;
        if (ResourceUtils.resourceExists(DEFAULT_INI_RESOURCE_PATH)) {

        }
        return ini;
    }
}
