<%--
  Created by IntelliJ IDEA.
  User: m_nad
  Date: 27.09.2019
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>

    <title>My books</title>
    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>

<body style="background-color:#ffebee">
<nav>
    <div class="nav-wrapper">
        <a href="#" class="brand-logo">Readers</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li><a href="account.jsp">My account</a></li>
            <li class="active"><a href="userlist.jsp">My books</a></li>
            <li><a href="booklist.jsp">Find book</a></li>
            <li><a href="login.jsp">Logout</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="card-panel red lighten-5">
        <h1 class="red-text">My books</h1>
        <table class="highlight">
            <thead>
            <tr>
                <th>Author</th>
                <th>Title</th>
                <th>Date</th>
                <th>Remove</th>
            </tr>
            </thead>

            <tbody>
            <tr>
                <th>J.R.R. Martin</th>
                <th>Games of throne</th>
                <th>12.01.2014</th>
                <th><a href="#" class="btn-small red lighten-2">x</a></th>
            </tr>
            <tr>
                <th>Robert C. Martin</th>
                <th>Clean Code</th>
                <th>21.09.2019</th>
                <th><a href="#" class="btn-small red lighten-2">x</a></th>
            </tr>
            </tbody>

        </table>

    </div>
</div>
