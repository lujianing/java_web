<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="BASE" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
</head>
<body>

<h3>Error Page</h3>
<table>
    <tr>
        <td>Status Code:</td>
        <td>${requestScope["javax.servlet.error.status_code"]}</td>
    </tr>
    <tr>
        <td>Request URI:</td>
        <td>${requestScope["javax.servlet.error.request_uri"]}</td>
    </tr>
    <tr>
        <td>Servlet Name:</td>
        <td>${requestScope["javax.servlet.error.servlet_name"]}</td>
    </tr>
    <tr>
        <td>Exception:</td>
        <td>${requestScope["javax.servlet.error.exception"]}</td>
    </tr>
    <tr>
        <td>Error Message:</td>
        <td>${requestScope["javax.servlet.error.message"]}</td>
    </tr>
</table>

</body>
</html>