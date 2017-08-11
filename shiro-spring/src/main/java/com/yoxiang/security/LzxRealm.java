package com.yoxiang.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import java.util.HashSet;
import java.util.Set;

/**
 * Author: RiversLau
 * Date: 2017/7/27 14:56
 */
public class LzxRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {

        return super.supports(token);
    }

    /**
     * 处理授权逻辑
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        Subject currentUser = SecurityUtils.getSubject();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roleSet = new HashSet<String>();
        roleSet.add("Capatian");
        roleSet.add("Officer");
        authorizationInfo.setRoles(roleSet);

        return authorizationInfo;
    }

    /**
     * 处理认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("do authentication");
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(authenticationToken.getPrincipal(), authenticationToken.getCredentials(), "realName");
        return info;
    }
}
