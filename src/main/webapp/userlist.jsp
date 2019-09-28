<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page isELIgnored="false" %>
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
            <li class="active"><a href="userlist">My books</a></li>
            <li><a href="list">Find book</a></li>
            <li><a href="login.jsp">Logout</a></li>
        </ul>
    </div>
</nav>

<div class="container">
        <h1 class="red-text">My books</h1>
        <table class="highlight">
            <thead>
            <tr>
                <th>Author</th>
                <th>Title</th>
                <th>Rate</th>
                <th>Addition date</th>
                <th>Remove</th>
            </tr>
            </thead>

            <tbody>
            <jsp:useBean id="usersBookList" scope="request" type="java.util.List"/>
            <c:forEach var="userBook" items="${usersBookList}">
            <tr>
                <td>${userBook.book.authorName} ${userBook.book.authorSurname} </td>
                <td>${userBook.book.title}</td>
                <td>${userBook.rate}</td>
                <td>${userBook.additionDate}</td>
                <td><a href="#" class="btn-small red lighten-2">x</a></td>
            </tr>
            </c:forEach>
            </tbody>

        </table>


</div>
<script type="text/javascript" src="js/materialize.min.js"></script>
</body>
</html>
