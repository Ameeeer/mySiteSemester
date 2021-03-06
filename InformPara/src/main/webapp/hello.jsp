<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>InnerPage</title>
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="css/main.css" type="text/css">
    <script src="/hello/js/bootstrap.js" type="text/javascript"></script>
    <script src="/hello/js/bootstrap.bundle.js" type="text/javascript"></script>
    <script type="text/javascript">
        function open_window(Location, w, h) //opens new window
        {
            let options = "width=" + 400 + ",height=" + 400;
            options += ",menubar=no,location=no,resizable,scrollbars,top=500,left=500";
            let newwin = window.open("miVamNeRadi.html", 'newWin', options);
            // if (newwin == null)
            // {
            //     // The popup got blocked, notify the user
            //     return false;
            // }
            newwin.focus();
        }
    </script>
</head>
<body class="mainPage">
<div id="controlPanel_1">
    <button class="colorChanges" value="red">RED</button>
    <button class="colorChanges" value="green">GREEN</button>
    <button class="colorChanges" value="blue">BLUE</button>
    <button class="colorChanges" value="snow">snow</button>
    <div id="">
        <div class="form-group div_correcter">
            ${Error_data}
        </div>
        <div>
            <form action="<%=request.getContextPath()%>/login" role="form" method="post" style="max-height: 92px">
                <div class="form-group">
                    <label style="position: relative;
    left: -25px;">Email</label>
                    <input type="email" id="email" name="emailToCheck" class="rounded-top rounded-bottom col-2"
                           placeholder="Email" value="${emailErrored}">
                </div>
                <div class="form-group">
                    <label style="position: relative;
    left: -2em;">Password</label>
                    <input type="password" id="password" name="passwordToCheck" class="rounded-top rounded-bottom col-2"
                           placeholder="Password" style="left: -1em;">
                </div>
                <div>
                    <input type="submit" value="SignIn" id="signIn"
                           class="rounded-top rounded-bottom btn btn-outline-success signInButton">
                </div>
            </form>
            <form action="<%=request.getContextPath()%>/registration" method="get">
                <div>
                    <input type="submit" value="Registration" id="signUp"
                           class="rounded-top rounded-bottom btn btn-outline-success registrateButton" style="max-height: 40px;
    position: relative;
    right: -1.3em;
    bottom: -0.5em;">
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    let elem = document.getElementsByClassName("colorChanges");
    for (let i = 0; i < elem.length; i++) {
        elem[i].addEventListener("click", function (event) {
            let b = event.target;
            document.body.style.backgroundColor = '' + b.value;
        })
    }
</script>
</body>
</html>