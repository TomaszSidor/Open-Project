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
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen,projection"/>

    <title>List of books</title>
    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>

<body>
<nav>
    <div class="nav-wrapper">
        <a href="#" class="brand-logo">Readers</a>
        <ul id="nav-mobile" class="right hide-on-med-and-down">
            <li class="active"><a href="account.jsp">My account</a></li>
            <li><a href="userlist">My books</a></li>
            <li><a href="list">Find book</a></li>
            <li><a href="login.jsp">Logout</a></li>
        </ul>
    </div>
</nav>


<div class="section no-pad-bot">
    <div class="container">
        <%--        <br><br>--%>
        <%--        <h1 class="header center orange-text">Starter Template</h1>--%>
        <%--        <div class="row center">--%>
        <%--            <h5 class="header col s12 light">A modern responsive front-end framework based on Material Design</h5>--%>
        <%--        </div>--%>
        <%--        <div class="row center">--%>
        <%--            <a href="http://materializecss.com/getting-started.html" id="download-button" class="btn-large waves-effect waves-light orange">Get Started</a>--%>
        <%--        </div>--%>
        <%--        <br><br>--%>

        <h4 class="red-text lighten-2">List of all books</h4>

        <table class="highlight">
            <thead>
            <tr>
                <th>Title</th>
                <th>Author name</th>
                <th>Author surname</th>
                <th>My list</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="book" items="${requestScope.bookList}">
                <tr>
                    <td>${book.title}</td>
                    <td>${book.authorName}</td>
                    <td>${book.authorSurname}</td>

                    <td>
                        <a href="index.jsp"><i class="material-icons red-text lighten-2">add</i></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>


        <br>
        <ul class="pagination center">
            <li class="disabled"><a href="#!"><i class="material-icons">chevron_left</i></a></li>
            <li class="active"><a href="#!">1</a></li>
            <li class="waves-effect"><a href="#!">2</a></li>
            <li class="waves-effect"><a href="#!">3</a></li>
            <li class="waves-effect"><a href="#!">4</a></li>
            <li class="waves-effect"><a href="#!">5</a></li>
            <li class="waves-effect"><a href="#!"><i class="material-icons">chevron_right</i></a></li>
        </ul>

    </div>
</div>


<!--JavaScript at end of body for optimized loading-->
<script type="text/javascript" src="js/materialize.min.js"></script>
</body>
</html>