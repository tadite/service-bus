<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Service Bus Test Page</title>
</head>
<body>
<div id="hello-page">
    <h1>Hello, ${pageContext.request.userPrincipal.name}!</h1>
</div>
    <form:form action="/logout" method="post">
        <input type="submit" value="Sign Out"/>
        <input type="button" value="Registration" onclick="window.location.href='/registration'"/>
    </form:form>
</body>
</html>
