<%--
  Created by IntelliJ IDEA.
  User: lijun
  Date: 2016/12/15
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String service = (String) request.getAttribute("service");
%>
<html>
<head>
    <title>Login</title>
</head>

<body>

<form name="form" action="login?service=<%=service%>" method="post">
    username<input type="text" id="username" name="username">
    password<input type="password" id="password" name="password">
    <button type="submit">登录</button>
</form>

</body>
</html>
