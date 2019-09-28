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
            <nav>
                <div class="nav-wrapper">
                    <a href="#" class="brand-logo">Readers</a>
                    <ul id="nav-mobile" class="right hide-on-med-and-down">
                        <li class="active"><a href="account.jsp">My account</a></li>
                        <li><a href="userlist.jsp">My books</a></li>
                        <li><a href="booklist.jsp">Find book</a></li>
                        <li><a href="login.jsp">Logout</a></li>
                    </ul>
                </div>
            </nav>
        </div>


        <div class="col s12 m6">
            <h4>Login:</h4>
            <form method="post" action="/login">
                <div>
                    <input placeholder="Your email" id="user-email-login" type="text" class="validate" name="email">
                    <label for="user-email-login">User email</label>
                    <input placeholder="Your password" id="user-password-login" type="text" class="validate"
                           name="password">
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
                    <input placeholder="Your name" id="user-name" type="text" class="validate" name="name">
                    <label for="user-name">User name</label>

                    <input placeholder="Your email" id="user-email" type="text" class="validate" name="email">
                    <label for="user-email">User email</label>

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