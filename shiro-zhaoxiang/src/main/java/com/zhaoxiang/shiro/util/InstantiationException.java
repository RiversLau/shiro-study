package com.zhaoxiang.shiro.util;

import com.zhaoxiang.shiro.ShiroException;

/**
 * Author: RiversLau
 * Date: 2017/9/29 17:38
 */
public class InstantiationException extends ShiroException {

    public InstantiationException() {
        super();
    }

    public InstantiationException(String msg) {
        super(msg);
    }

    public InstantiationException(Throwable cause) {
        super(cause);
    }

    public InstantiationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
