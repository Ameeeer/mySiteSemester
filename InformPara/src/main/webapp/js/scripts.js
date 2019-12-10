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

let button = document.getElementById("changeColor");
button.onclick = function () {
    if (document.body.style.backgroundColor !== "red") {
        document.body.style.backgroundColor = "red";
    }
    if (document.body.style.backgroundColor === "red") {
        document.body.style.backgroundColor = "green";
    }
    if (document.body.style.backgroundColor === "green") {
        document.body.style.backgroundColor = "snow";
    }
};
// document.addEventListener('click',function (button) {
//     let myEvent = button.target;
//     if (myEvent.value === 'blue'){
//
//     }
// })