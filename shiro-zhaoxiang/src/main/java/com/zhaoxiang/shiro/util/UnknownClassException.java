package com.zhaoxiang.shiro.util;

import com.zhaoxiang.shiro.ShiroException;

/**
 * Author: RiversLau
 * Date: 2017/9/29 17:22
 */
public class UnknownClassException extends ShiroException {

    public UnknownClassException() {
        super();
    }

    public UnknownClassException(String msg) {
        super(msg);
    }

    public UnknownClassException(Throwable cause) {
        super(cause);
    }

    public UnknownClassException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
