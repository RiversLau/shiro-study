package com.zhaoxiang.shiro.util;

/**
 * Author: RiversLau
 * Date: 2017/9/29 16:09
 */
public abstract class AbstractFactory<T> implements Factory<T> {

    private boolean singleton;
    private T singletonInstance;

    public AbstractFactory() {
        this.singleton = true;
    }

    public boolean isSingleton() {
        return singleton;
    }

    public void setSingleton(boolean singleton) {
        this.singleton = singleton;
    }

    public T getInstance() {
        T instance;
        if (singleton) {
            if (this.singletonInstance == null) {
                this.singletonInstance = createInstance();
            }
            instance = this.singletonInstance;
        } else {
            instance = createInstance();
        }
        if (instance == null) {
            String msg = "Factory 'createInstance' implemention method returned a null object";
            throw new IllegalStateException(msg);
        }
        return null;
    }

    public abstract T createInstance();
}
