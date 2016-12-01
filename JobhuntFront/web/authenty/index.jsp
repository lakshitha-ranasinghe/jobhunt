<%-- 
    Document   : indes
    Created on : Dec 7, 2015, 12:31:46 PM
    Author     : Muthu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
    <head>
        <meta charset="UTF-8">
        <title>JOBHUNT LOGIN</title>

        <link rel='stylesheet prefetch' href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900|RobotoDraft:400,100,300,500,700,900'>
        <link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>

        <link rel="stylesheet" href="${pageContext.request.contextPath}/external/css/style_1.css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="${pageContext.request.contextPath}/external/js/jquery.js"></script>
        <script>
            $(document).ready(function () { 
                var isUserExistInDB;
                $('#userexist').hide();
                $('#usernameempty').hide();
                $('#passwordmatch').hide();
                $('#passwordempty').hide();
                function checkUserExist() {
                    var username = $("#username").val();
                    var parameter = {username: username};
                    $.getJSON('CheckUserAlreadyExist', parameter, function (data) {
                        isUserExistInDB = data.isUserExist;
                        if (isUserExistInDB) {
                            $('#userexist').fadeIn();
                            disableRegister();
                        }
                        else {
                            $('#userexist').fadeOut();
                            enableRegister();
                        }
                    });
                }

                $("#username").focusout(function () {
                    checkUserExist();
                });

                $('#password1').keyup(function () {
                    var password1 = $(this).val();
                    var password2 = $('#password2').val();
                    if (password1 !== password2) {
                        $('#passwordmatch').fadeIn();
                        disableRegister();
                    }
                    else {
                        $('#passwordmatch').fadeOut();
                        enableRegister();
                    }
                });
                $('#password2').keyup(function () {
                    var password1 = $('#password1').val();
                    var password2 = $(this).val();
                    if (password1 !== password2) {
                        $('#passwordmatch').fadeIn();
                        disableRegister();
                    }
                    else {
                        $('#passwordmatch').fadeOut();
                        enableRegister();
                    }
                });

                function disableRegister() {
                    $('#register').prop('disabled', true);
                }
                function enableRegister() {
                    $('#register').prop('disabled', false);
                }

                function disableLogin() {
                    $('.loginbutton').prop('disabled', true);
                }
                function enableLogin() {
                    $('.loginbutton').prop('disabled', false);
                }

                $('#register').mouseover(function () {
                    var password1 = $('#password1').val();
                    var password2 = $('#password2').val();
                    var name = $('#name').val();
                    var username = $('#username').val();

                    if (name === "" || username === "" || password1 === "" || password2 === "") {
                        disableRegister();
                    }
                    else if (password1 !== password2) {
                        disableRegister();
                    }
                    else if (isUserExistInDB) {
                        disableRegister();
                    }
                });
                var mouseovertime=0;
                $('.loginbutton').mouseover(function () {
                    mouseovertime += 1;
                    var autologinusername = $('#loginusername:-webkit-autofill').length;
                    var autologinpassword = $('#loginpassword:-webkit-autofill').length;
                    var loginusername = $('#loginusername').val();
                    var loginpassword = $('#loginpassword').val();
                    if(mouseovertime === 1){
                        if (autologinusername !== 1 || autologinpassword !== 1) {
                            $('#usernameempty').fadeIn();
                            $('#passwordempty').fadeIn();
                            disableLogin();
                        }
                    }
                    if (loginusername === "") {
                        $('#usernameempty').fadeIn();
                        disableLogin();
                    }
                    if (loginpassword === "") {
                        $('#passwordempty').fadeIn();
                        disableLogin();
                    }
                    enableLogin();
                });
                $('input').focusin( function(){
                    $('#usernameempty').hide();
                    $('#passwordempty').hide();
                });
            });
        </script>
    </head>

    <body>

        <div class="pen-title">
            <h1>JOB HUNT</h1><span>provides you with the most comprehensive<br> online employment services in Sri Lanka.</span>
        </div>

        <div class="module form-module">
            <div class="toggle"><i class="fa fa-times fa-pencil"></i>
                <div class="tooltip">Click Me</div>
            </div>
            <div class="form">
                <h2>Login to your account</h2>
                <form role="form" data-toggle="validator" action="Login">
                    <span id="usernameempty" class="label label-danger">Empty</span>
                    <input id="loginusername" type="text" name="username" class="form-control" placeholder="Username"/>
                    <span id="passwordempty" class="label label-danger">Empty</span>
                    <input id="loginpassword" type="password" name="password" class="form-control" placeholder="Password"/>
                    <button class="loginbutton" id="loginbutton" >Login</button>
                </form>
            </div>
            <div class="form" >
                <h2>Create an account</h2>
                <form role="form" action="Register">
                    <div class="form-group">
                        <input id="name" type="text"  name="applicationUser.name" class="form-control" placeholder="Name"/>
                        <span id="userexist" class="label label-danger">Username Taken</span>
                        <input type="text" id="username" name="applicationUser.username" class="form-control" placeholder="Username"/>
                        <input type="password" id="password1" name="applicationUser.password" class="form-control" placeholder="Password" id="inputPassword"/>
                        <span id="passwordmatch" class="label label-danger">Passowrds do not match</span>
                        <input type="password" id="password2" class="form-control" placeholder="Confirm Password" id="inputPasswordConfirm">
                        <div class="form-inline form-group-radiogroup">
                            <div class="radio radio-primary radio-inline">
                                <input type="radio" id="inlineRadio1" value="seeker" name="applicationUser.type" checked>
                                <label for="inlineRadio1">Job Seeker</label>
                            </div>
                            <div class="radio radio-primary radio-inline">
                                <input type="radio" id="inlineRadio2" value="company" name="applicationUser.type">
                                <label for="inlineRadio2">Company </label>
                            </div>
                        </div>
                        <br>
                        <button id='register'>Register</button>
                    </div>
                </form>
            </div>
        </div>
        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
        <script src='${pageContext.request.contextPath}/external/js/wa.js'></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/external/js/index_1.js"></script>
    </body>
</html>