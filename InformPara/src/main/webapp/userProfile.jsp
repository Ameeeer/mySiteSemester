<%@ page import="com.company.servlets.dto.UserDto" %><%--
  Created by IntelliJ IDEA.
  User: amir
  Date: 18.11.2019
  Time: 18:51
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <title>Profile</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-grid.css" type="text/css">
    <script src="<%=request.getContextPath()%>/js/jQuery_3.4.1.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/js/bootstrap.bundle.js" type="text/javascript"></script>
    <script type="text/javascript">
        function show() {
            $.ajax({
                url: '../CurrentTime',
                success: function (resp) {
                    $('#currentTime').text("  " + resp)
                }
            })
        }

        $(document).ready(
            show(),
            setInterval('show()', 1000)
        );
    </script>
    <style>
        .form-group {
            margin-left: 0;
        }
    </style>
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
                    ${UserDto.login}
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
<hr>
<div class="px-0 py-1 container text-md-center rounded" style="min-height: 600px; max-width: 400px;">
    <div class="form-group">
        <label <%=(request.getSession().getAttribute("errOnUpdate") != null && request.getSession().getAttribute("errOnUpdate").equals("show")) ? " " : "hidden"%>
                style="color: red">
            Обновление не удалось
        </label>
    </div>
    <form class="card card-block m-x-auto bg-faded form-width" method="post"
          action="<%=request.getContextPath()%>/profile/update">
        <legend class="m-b-1 text-xs-center">${UserDto.login} - Profile</legend>
        <div class="form-group input-group">
 <span class="has-float-label">
 <input class="form-control" style="min-width: 399px" id="first" type="text" placeholder="Email"
        value="${UserDto.login}" name="loginUpdate"/>
 <label for="first">Login</label>
 </span>
        </div>
        <div class="form-group input-group">
            <span class="input-group-addon"></span>
            <span class="has-float-label">
 <input class="form-control" style="min-width: 399px" id="email" type="email" placeholder="name@example.com"
        value="${UserDto.email}" name="emailUpdate"/>
 <label for="email">E-mail</label>
 </span>
        </div>
        <div class="form-group has-float-label">
            <select class="form-control custom-select" required name="countryUpdate" id="country">
                <option disabled>Выберите страну</option>
                <option value="Россия" ${UserDto.country.equals("Россия")?"selected":" "}>
                    Россия
                </option>
                <option value="США" ${UserDto.country.equals("США")?"selected":" "}>США
                </option>
                <option value="Канада"${UserDto.country.equals("Канада")?"selected":" "}>
                    Канада
                </option>
            </select>
            <label for="country">Country</label>
        </div>
        <div class="form-group input-group">
            <span class="input-group-addon"></span>
            <span class="has-float-label">
 <textarea class="form-control" style="min-width: 399px" id="info" type="text"
           placeholder="Info about you" name="infoUpdate">${UserDto.infoAboutUser}</textarea>
 <label for="email">Info about you</label>
        </span></div>
        <div class="form-group">
            <span class="custom-control-description">Изменить пароль</span>
        </div>
        <div class="text-xs-center">
            <button class="btn btn-block btn-primary" type="submit">Сохранить изменения</button>
        </div>
    </form>
</div>
</body>
</html>
