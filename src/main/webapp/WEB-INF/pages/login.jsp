<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Service Bus Test Page</title>
</head>
<body>
<div id="base">
    <form method="post" action="/login">
        <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden"/>
        <div class="enter-login">Login</div>
        <input type="text" name="username" cssClass="login-field" placeholder="Username"/>
        <div class="enter-password">Password</div>
        <input type="password" name="password" cssClass="password-field" placeholder="Password"/>
        <div>
            <input type="submit" class="enter-button" value="Enter"/>
        </div>
    </form>
    <c:if test="${not empty logout}">
        <div class="logout" style="color: green">${logout}</div>
    </c:if>
    <c:if test="${not empty error}">
        <div class="error" style="color: red">${error}</div>
    </c:if>
</div>
</body>
</html>
