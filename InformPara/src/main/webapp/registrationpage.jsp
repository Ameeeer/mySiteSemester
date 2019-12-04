<html>
<head lang="en">
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" href="css/main.css" type="text/css">
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css">
    <script>
        // document.addEventListener("DOMLoaded",
        var checkPass = function () {
            if (document.getElementById('PasswordOnRegistration').value == document.getElementById('PasswordRepeat').value) {
                document.getElementById('message').style.color = 'green';
                document.getElementById('message').innerHTML = 'Совпадают';
                document.getElementById('registrationCheck').removeAttribute("disabled");
            } else {
                document.getElementById('message').style.color = 'red';
                document.getElementById('message').innerHTML = 'Пароли не совпадают';
                document.getElementById('registrationCheck').setAttribute('disabled', '');
            }
            $(document).ready(function () {
                $().keyup(checkPasswordMatch);
            });
        };
    </script>
</head>
<body>
<div id="controlPanel_2">
    <div id="bordered">
        <form action="<%=session.getServletContext().getContextPath()%>/registration" method="post"
              id="registrationForm">
            <label style="color: red; margin-right: 6em" <%=session.getAttribute("hide") != null && session.getAttribute("hide").equals("hide") ? "" : "hidden"%>>Не
                удалось
                зарегистрировать<br> Данные не введены либо введены неверно</label>
            <div class="form-group">
                <label>Введите Email</label><br>
                <input type="email" name="Email" placeholder="Email"
                       class="rounded-top rounded-bottom col-2 controlRecize" required value="${errEmail}">
            </div>
            <div class="form-group">
                <label>Введите Логин</label><br>
                <input type="text" name="Login" placeholder="Login" id="LoginOnRegistration"
                       class="rounded-top rounded-bottom col-2 controlRecize" required value="${errLogin}">
            </div>

            <div class="form-group">
                <label>Введите пароль</label><br>
                <input type="text" name="Password" placeholder="Password" id="PasswordOnRegistration"
                       class="rounded-top rounded-bottom col-2 controlRecize" required onkeyup="checkPass()">
            </div>
            <div class="form-group">
                <label>Повторите пароль</label><br>
                <input type="text" name="PasswordRepeat" placeholder="Repeat Password" id="PasswordRepeat"
                       class="rounded-top rounded-bottom col-2 controlRecize" required
                       onkeyup="checkPass()">
                <div>
                    <span id="message" aria-required="true"></span>
                </div>
            </div>
            <div class="form-group">
                <label>Выберите страну</label><br>
                <select required name="Country">
                    <option disabled>Выберите страну</option>
                    <option value="Россия">Россия</option>
                    <option value="США">США</option>
                    <option value="Канада">Канада</option>
                </select>
            </div>
            <div class="form-group">
                <label>Выберите роль</label><br>
                <select required size="3" name="roles[]" multiple id="roleSelector">
                    <option disabled ></option>
                    <option value="Admin">Admin</option>
                    <option value="simpleUser">Simple User</option>
                    <option value="Moderator">Moderator</option>
                    <option value="Helper">Helper</option>
                    <option value="Jesus">Jesus</option>
                    <option value="Lecturer">Lecturer</option>
                </select>
            </div>
            <div class="form-group">
                <label>О себе</label><br>
                <textarea name="Info" placeholder="Введите информацию о себе" style="min-height: 8em"
                          class="controlRecize rounded-bottom rounded-top col-2">${errInfo}</textarea>
            </div>
            <div class="form-group" style="margin-right: 0.8em">
                <input type="checkbox" name="LicenseCheckBox" id="LicenseCheckBox" required>
                <label title="Лицензионное соглашение" for="LicenseCheckBox">
                    Лицензионное соглашение</label>
            </div>
            <div class="">
                <input type="submit" name="RegistrateButton" value="Registrate"
                       class="btn btn-outline-success" style="margin-right: 15em" id="registrationCheck" disabled>
            </div>
        </form>
    </div>
</div>
</body>
</html>