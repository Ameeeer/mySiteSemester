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