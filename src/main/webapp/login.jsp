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
<%--    ${applicationScope}--%>
<p>
<h3>applicationScope</h3>
<c:forEach items="${applicationScope.keySet()}" var="key">
    ${key}<br/>
</c:forEach>
</p>
<p>-------------------------------</p>
<p>
<h3>requestScope</h3>
<c:forEach items="${requestScope.keySet()}" var="key">
    ${key}<br/>
</c:forEach>
</p>

<p>-------------------------------</p>
<p>
<h3>sessionScope</h3>
    <c:forEach items="${sessionScope.keySet()}" var="key">
        ${key}<br/>
    </c:forEach>
</p>
<p>-------------------------------</p>
<p>
<h3>pageScope</h3>
<c:forEach items="${pageScope.keySet()}" var="key">
    ${key}<br/>
</c:forEach>
</p>

<p>-------------------------------</p>
<p>
<h3>param</h3>
<c:forEach items="${param.keySet()}" var="key">
    ${key}<br/>
</c:forEach>
</p>

<p>-------------------------------</p>
<p>
<h3>header</h3>
<c:forEach items="${ header.keySet()}" var="key">
    ${key}<br/>
</c:forEach>
</p>

<p>-------------------------------</p>
<p>${shiroLoginFailure}</p>
</body>
</html>
