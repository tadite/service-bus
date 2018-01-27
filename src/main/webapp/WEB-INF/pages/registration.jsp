<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<div id="reg">
    <form:form modelAttribute="newUser" method="post" action="/registration">
        <div>Login</div>
        <form:input path="login"/>
        <div>Password</div>
        <form:password path="password"/>
        <div>Role</div>
        <form:select path="role">
            <option>ADMIN</option>
            <option>USER</option>
        </form:select>
        <input type="submit" value="Add user"/>
    </form:form>
    <c:if test="${not empty error}">
        <div style="color: red;">${error}</div>
    </c:if>
</div>
</body>
</html>
