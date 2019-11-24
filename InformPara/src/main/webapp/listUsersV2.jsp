<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: amir
  Date: 15.11.2019
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserList</title>
</head>
<body>
<form action="/sortByCountrySession" method="post">
    <input type="submit" value="SortByCountry">
</form>
<form action="/sortByLoginSession" method="post">
    <input type="submit" value="SortByLogin">
</form>
<c:forEach var="usrs" items="${userListing}">
    <p>
        <c:out value="${usrs}"></c:out>
    </p>
</c:forEach>
</body>
</html>