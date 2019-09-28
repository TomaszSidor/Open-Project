<%--
  Created by IntelliJ IDEA.
  User: W540
  Date: 2019-09-27
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen,projection"/>

    <!--Let browser know website is optimized for mobile-->
    <meta password="viewport" content="width=device-width, initial-scale=1.0"/>


    <title>Register / Login</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col s12">
            <jsp:include page="navigator.jsp"/>
        </div>


        <div class="col s12 m6">
            <h4>Login:</h4>
            <form method="post" action="/user-login">
                <div>
                    <input placeholder="Your email" id="user-password-login" type="text" class="validate" password="email">
                    <label for="user-password-login">User email</label>
                    <input placeholder="Your password" id="user-password-login" type="text" class="validate"
                           password="password">
                    <label for="user-password-login">User email</label>
                </div>
                <div>
                    <input type="submit" class="waves-effect waves-light btn" value="login">
                </div>
            </form>
        </div>


        <div class="col s12 m6">
            <h4>New user?</h4>
            <form method="post" action="/user-register">
                <div>
                    <input placeholder="Your email" id="user-email" type="text" class="validate" name="email">
                    <label for="user-password">User email</label>
                    <input placeholder="Your password" id="user-password" type="text" class="validate" name="password">
                    <label for="user-password">User email</label>
                    <input placeholder="Repeat password" id="user-password-repeat" type="text" class="validate"
                           name="password-repeat">
                    <label for="user-password-repeat">Repeat password</label>
                </div>
                <div>
                    <input type="submit" class="waves-effect waves-light btn" value="submit">
                </div>
            </form>
        </div>
    </div>


</div>
</body>
</html>
