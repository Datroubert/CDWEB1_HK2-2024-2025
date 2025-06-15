<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Đăng ký tài khoản</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/register.css">
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #f0f2f5;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
}
.container {
    background-color: #ffffff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    width: 400px;
    max-width: 100%;
}
h2 {
    text-align: center;
    margin-bottom: 20px;
}
.form-group {
    margin-bottom: 15px;
}
.form-group label {
    display: block;
    margin-bottom: 5px;
}
.form-group input {
    width: calc(100% - 22px);
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}
.form-group input[type="submit"], .form-group button {
    background-color: #007bff;
    color: #fff;
    border: none;
    cursor: pointer;
    transition: background-color 0.3s;
}
.form-group input[type="submit"]:hover, .form-group button:hover {
    background-color: #0056b3;
}
.mess {
    color: red;
    text-align: center;
    margin-bottom: 15px;
}
.error {
    color: red;
    margin-top: 2px;
    display: block;
    min-height: 18px;
}
@media (max-width: 600px) {
    .container {
        width: 100%;
        padding: 10px;
    }
}
</style>
<script>
$(function() {
    function validateUsername() {
        let val = $("#username").val().trim();
        let err = "";
        if(val === "") err = "Tên tài khoản không được để trống";
        else if(val.length < 5 || val.length > 15) err = "Tên tài khoản phải từ 5 đến 15 ký tự";
        $("#username-error").text(err);
        return err === "";
    }
    function validatePassword() {
        let val = $("#password").val();
        let err = "";
        if(val === "") err = "Mật khẩu không được để trống";
        else if(val.length < 8) err = "Mật khẩu phải chứa ít nhất 8 ký tự";
        $("#password-error").text(err);
        return err === "";
    }
    function validateAgainPassword() {
        let val = $("#againPassword").val();
        let pw = $("#password").val();
        let err = "";
        if(val === "") err = "Nhập lại mật khẩu không được để trống";
        else if(val !== pw) err = "Mật khẩu nhập lại không khớp";
        $("#againPassword-error").text(err);
        return err === "";
    }
    function validateDob() {
        let val = $("#dob").val();
        let err = "";
        if(val === "") err = "Ngày sinh không được để trống";
        $("#dob-error").text(err);
        return err === "";
    }
    function validateEmail() {
        let val = $("#email").val().trim();
        let err = "";
        let re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if(val === "") err = "Email không được để trống";
        else if(!re.test(val)) err = "Email không hợp lệ";
        $("#email-error").text(err);
        return err === "";
    }
    function validateAddress() {
        let val = $("#address").val().trim();
        let err = "";
        if(val === "") err = "Địa chỉ không được để trống";
        $("#address-error").text(err);
        return err === "";
    }

    $("#username").on("blur", validateUsername);
    $("#password").on("blur", validatePassword);
    $("#againPassword").on("blur", validateAgainPassword);
    $("#dob").on("blur", validateDob);
    $("#email").on("blur", validateEmail);
    $("#address").on("blur", validateAddress);

    $("#registerForm").on("submit", function(e) {
        let valid = true;
        valid = validateUsername() && validatePassword() && validateAgainPassword() && validateDob() && validateEmail() && validateAddress() && valid;
        if(!valid) e.preventDefault();
    });
});
</script>
</head>
<body>
    <div class="container">
        <h2>Đăng ký tài khoản</h2>
        <h3 class="mess" align="center">${message}</h3>
        <form id="registerForm" action="registerPerfome" method="POST" autocomplete="off">
            <div class="form-group">
                <label for="username">Tên tài khoản:</label>
                <input type="text" id="username" name="username" />
                <span class="error" id="username-error"></span>
            </div>
            <div class="form-group">
                <label for="password">Mật khẩu:</label>
                <input type="password" id="password" name="password" />
                <span class="error" id="password-error"></span>
            </div>
            <div class="form-group">
                <label for="againPassword">Nhập lại mật khẩu:</label>
                <input type="password" id="againPassword" name="againPassword" />
                <span class="error" id="againPassword-error"></span>
            </div>
            <div class="form-group">
                <label for="dob">Ngày sinh:</label>
                <input type="date" id="dob" name="dob" />
                <span class="error" id="dob-error"></span>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" />
                <span class="error" id="email-error"></span>
            </div>
            <div class="form-group">
                <label for="address">Địa chỉ:</label>
                <input type="text" id="address" name="address" />
                <span class="error" id="address-error"></span>
            </div>
            <div class="form-group">
                <button type="submit">Đăng Ký</button>
                <a class="btn" href="loginPerfome">Login</a>
            </div>
        </form>
    </div>
</body>
</html>