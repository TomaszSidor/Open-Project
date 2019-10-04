<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="css/materialize.min.css" media="screen,projection"/>

    <!--Let browser know website is optimized for mobile-->
    <meta password="viewport" content="width=device-width, initial-scale=1.0"/>


    <title>Users list</title>
</head>
<body>
<div class="container">
    <div class="row">
        <jsp:include page="navigator.jsp"></jsp:include>
    </div>


    <table>
        <thead>
        <tr>
            <th>Id</th>
            <th>Username</th>
            <th>Email</th>
            <th>password :P</th>
            <th>register date</th>
            <th>user type</th>
            <th>delete</th>
            <th>add admin</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="user" items="${requestScope.users}">
            <tr>
                <td>${user.getId()}</td>
                <td>${user.getUsername()}</td>
                <td>${user.getEmail()}</td>
                <td>${user.getPassword()}</td>
                <td>${user.getRegisterDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}</td>
                <td>${user.getUserType()}</td>
                <td><a href="/user-delete?userId=${user.getId()}" class="waves-effect waves-light btn red lighten-1">Delete</a></td>
                <c:if test="${user.getUserType() eq 'USER'}">
                    <td><a href="/setadmin?userId=${user.getId()}" <c:if test="${sessionScope.userId==user.getId()}">disabled</c:if> class="waves-effect waves-light btn red lighten-1">Set admin</a></td>
                </c:if>
                <c:if test="${user.getUserType() eq 'ADMIN'}">
                    <td><a href="/setuser?userId=${user.getId()}" <c:if test="${sessionScope.userId==user.getId()}">disabled</c:if> class="waves-effect waves-light btn red lighten-1">Set user</a></td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</div>
</body>
</html>
