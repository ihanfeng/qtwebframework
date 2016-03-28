<%@ page contentType="application/json;charset=UTF-8" language="java" %>
<%
response.setStatus(401);
out.print("{\"message\":\"Unauthorized\",\"error\":401}");
%>