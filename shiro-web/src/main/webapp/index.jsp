<%--
  Created by IntelliJ IDEA.
  User: rrduser01
  Date: 2017/7/25
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
    <form action="/user/login">
        <input type="text" name="name" placeholder="用户名"/>
        <input type="text" name="password" placeholder="密码"/>
        <button type="submit">提交</button>
    </form>
</body>
</html>
