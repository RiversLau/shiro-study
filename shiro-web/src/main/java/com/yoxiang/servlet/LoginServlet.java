package com.yoxiang.servlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author: RiversLau
 * Date: 2017/7/25 15:00
 */
public class LoginServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if ("GET".equalsIgnoreCase(req.getMethod())) {
            doGet(req, resp);
        }

        if ("POST".equalsIgnoreCase(req.getMethod())) {
            doPost(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info(">>>>>>>>>>>>> Do GET method start <<<<<<<<<<<<<<");

        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            PrintWriter pw = resp.getWriter();
            pw.write("Hello Shiro");
            pw.close();
        }

        logger.info(">>>>>>>>>>>>> Do GET method finish <<<<<<<<<<<<<<");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info(">>>>>>>>>>>>> Do POST method start <<<<<<<<<<<<<<");

        String name = req.getParameter("name");
        String password = req.getParameter("password");
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        token.setRememberMe(true);

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(true);
        if (!subject.isAuthenticated()) {
            try {
                subject.login(token);
                session.setAttribute("name", name);
                logger.info(">>>>>>>>>>>>> authyenticate successfully!");
            } catch (AuthenticationException ex) {
                logger.error(">>>>>>>>>>>>> authenticate error occured, exception :" + ex.getMessage());
            }
        }

        logger.info(">>>>>>>>>>>>> Do POST method finish <<<<<<<<<<<<<<");
        resp.sendRedirect("/user/info");
    }
}
