<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<body>
    <h2>Hello World!</h2>
    <shiro:authenticated>
        <h2>Hello <% session.getAttribute("name").toString(); %></h2>
    </shiro:authenticated>
    <shiro:guest>
        <h2>Hello, boy! You are the first guy!</h2>
    </shiro:guest>
</body>
</html>
