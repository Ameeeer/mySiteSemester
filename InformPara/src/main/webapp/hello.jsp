<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>InnerPage</title>
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="css/main.css" type="text/css">
    <script src="js/bootstrap.js" type="text/javascript"></script>
    <script src="js/bootstrap.bundle.js" type="text/javascript"></script>
    <script type="text/javascript">
        function open_window(Location, w, h) //opens new window
        {
            var options = "width=" + 400 + ",height=" + 400;
            options += ",menubar=no,location=no,resizable,scrollbars,top=500,left=500";
            var newwin = window.open("miVamNeRadi.html", 'newWin', options);
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
    <div id="">
        <div class="form-group div_correcter">
            ${Error_data}
        </div>
        <div>
            <form action="/login" role="form" method="post" style="max-height: 92px">
                <div class="form-group">
                    <input type="email" id="email" name="emailToCheck" class="rounded-top rounded-bottom col-2"
                           placeholder="Email" value="${emailErrored}">
                </div>
                <div class="form-group">
                    <input type="password" id="password" name="passwordToCheck" class="rounded-top rounded-bottom col-2"
                           placeholder="Password">
                </div>
                <div>
                    <input type="submit" value="SignIn" id="signIn"
                           class="rounded-top rounded-bottom btn btn-outline-success signInButton">
                </div>
            </form>
            <form action="/registration" method="get" style="max-height: 100px">
                <div>
                    <input type="submit" value="Registration" id="signUp"
                           class="rounded-top rounded-bottom btn btn-outline-success registrateButton">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>