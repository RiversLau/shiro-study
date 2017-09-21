package com.rivers.study;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: Rivers
 * Date: 2017/7/12 22:04
 */
public class RiversRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger("RiversRealm");

    /**
     * 某些情况下，只想支持授权操作，不想支持认证操作，可以直接返回FALSE，那么自定义的Realm对象就
     * 不会进行认证的尝试，也就是不会调用doGetAuthenticationInfo(AuthenticationToken token)方法
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {

        return super.supports(token);
//        return false;
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        logger.info(">>>>>>>>>>>>>> do authorization <<<<<<<<<<<<<<");
        return null;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        logger.info(">>>>>>>>>>>>>> do authenticate <<<<<<<<<<<<<<");
        return null;
    }
}
