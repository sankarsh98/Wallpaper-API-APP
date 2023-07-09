host = "http://localhost:8081"

function signup() {

    let username = document.getElementById("username");
    let password = document.getElementById("userpassword");
    let confirm_password = document.getElementById("confirmpassword");
    let email = document.getElementById("useremail");

    let error = document.getElementById("validation_error");

    var signup_url = host  + "/signup/";

    const xhr = new XMLHttpRequest();

    xhr.open("POST", signup_url, true);

    var signup_json = {
        "username": username.value,
        "password": password.value,
        "confirmPassword": confirm_password.value,
        "email": email.value
    };
    console.log(signup_json);
    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

    xhr.onload = function () {
        if (xhr.status === 200) {
            console.log(xhr.responseText);
            var response = JSON.parse(xhr.responseText);
            if (response.status == false) {
                error.setAttribute("style", "display:block");
                error.innerHTML = "<p>" + response.message + "</p>";
            }else{
                window.location.href = host + "/login";
            }
        } else {

        }
    }

    xhr.send(JSON.stringify(signup_json));

}