function validateLogin() {
    let login = document.getElementById("login").value

    if (login.length >= MIN_LOGIN_LENGTH) {
        $.ajax({
                url: "validateLogin",
                type: "get",
                async: false,
                data: {"login": login},
                dataType: "html",
                success: function (data) {
                    if (data !== "") {
                        setInvalid(data, "login");
                    } else {
                        setValid("login");
                    }
                },
            }
        )
    }
}