<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: amir
  Date: 15.11.2019
  Time: 8:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserList</title>
</head>
<body>
<form action="/sortByCountry" method="post">
    <input type="submit" value="SortByCountry">
</form>
<form action="/sortByLogin" method="post">
    <input type="submit" value="SortByLogin">
</form>
<c:forEach var="users" items="${list}">
    <p>
        <c:out value="${users}"></c:out>
    </p>
</c:forEach>
</body>
</html>
