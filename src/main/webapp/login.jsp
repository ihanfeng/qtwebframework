<%--
  Created by IntelliJ IDEA.
  User: ZML
  Date: 2016/3/26
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户登录</title>

</head>
<body>
<form action="login.jsp" method="post">
    <input type="text" placeholder="username" name="username">
    <input type="password" placeholder="password" name="password">
    <input type="submit" value="提交">
</form>
<p>${shiroLoginFailure}</p>
<c:if test="${'org.apache.shiro.authc.UnknownAccountException'.equals(shiroLoginFailure)}">
    该账户不存在
</c:if>
<c:if test="${'org.apache.shiro.authc.LockedAccountException'.equals(shiroLoginFailure)}">
    该账户被禁止登录
</c:if>
<c:if test="${'org.apache.shiro.authc.IncorrectCredentialsException'.equals(shiroLoginFailure)}">
    密码错误
</c:if>
</body>
</html>
