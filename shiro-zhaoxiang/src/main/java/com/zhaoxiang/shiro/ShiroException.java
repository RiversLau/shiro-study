package com.zhaoxiang.shiro;

/**
 * Author: RiversLau
 * Date: 2017/9/29 17:21
 */
public class ShiroException extends RuntimeException {

    public ShiroException() {
        super();
    }

    public ShiroException(String msg) {
        super(msg);
    }

    public ShiroException(Throwable cause) {
        super(cause);
    }

    public ShiroException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
