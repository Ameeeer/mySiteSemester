<%--
  Created by IntelliJ IDEA.
  User: amir
  Date: 18.11.2019
  Time: 18:51
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <title>Players</title>
    <link rel="stylesheet" href="/hello/css/main.css" type="text/css">
    <link rel="stylesheet" href="/hello/css/bootstrap.css" type="text/css">
    <script src="/hello/js/jQuery_3.4.1.js" type="text/javascript"></script>
    <script src="/hello/js/bootstrap.bundle.js" type="text/javascript"></script>
    <script src="/hello/js/CurrentServerTime.js" type="text/javascript"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="<%=request.getContextPath()+"/profile"%>">WWW</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#">Home <span class="sr-only"></span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Create chat</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/players">Players</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Tops
                </a>
                <div class="dropdown-menu nav-item" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Top players
                    </a>
                    <a class="dropdown-item" href="#">Top teams</a>
                </div>
            </li>
            <li class="nav-item"<%=request.getSession().getAttribute("adminPanel")%>>
                <a class="nav-link" href="#">Admin Panel</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="true">
                    ${UserNameProfile}
                </a>
                <div class="dropdown-menu nav-item" aria-labelledby="navbarDropdown" id="userProfileDropDown"
                     style="right: 5em;">
                    <a class="dropdown-item" href="<%=request.getContextPath()%>/profile/user">Profile</a>
                    <a class="dropdown-item" href="<%=request.getContextPath()%>/profile/friends">Friends</a>
                    <a class="dropdown-item" href="<%=request.getContextPath()%>/quit">Quit</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
<label>Current Server Time: </label><label id="currentTime" class="ti"></label>
</body>
</html>
