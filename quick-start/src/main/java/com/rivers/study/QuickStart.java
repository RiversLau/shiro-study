package com.rivers.study;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: Rivers
 * Date: 2017/7/9 16:23
 */
public class QuickStart {

    private static final Logger logger = LoggerFactory.getLogger("QuickStart");

    public static void main(String[] args) {

        logger.info("Hello Shiro");

        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager manager = factory.getInstance();

        SecurityUtils.setSecurityManager(manager);

        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        session.setAttribute("Hello", "Shiro");

        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("rivers", "secret");
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (AuthenticationException ex) {
                logger.error("错误的用户名或密码");
            }
        }

        if (currentUser.hasRole("admin")) {
            logger.info("用户【" + currentUser.getPrincipal() + "】具有【admin】角色");
        }

        if (currentUser.isPermitted("UserManagerment:deleteUser")) {
            logger.info("用户【" + currentUser.getPrincipal() + "】具有【UserManagerment:deleteUser】权限");
        }

        System.exit(0);
    }
}
