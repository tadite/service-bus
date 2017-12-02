<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Service Bus Test Page</title>
</head>
<body>
<div id="base">
    <form:form modelAttribute="mainForm" method="post">
        <div class="enter-name">Enter name</div>
        <form:input path="name" cssClass="name-field"/>
        <input type="submit" class="enter-button" value="Enter"/>
    </form:form>
    <c:if test="${not empty error}">
        <div class="error-message" style="color:red">
            ${error}
        </div>
    </c:if>
</div>
</body>
</html>
