<%@ page import="com.github.magdanadratowska.model.UserBook" %>
<%@ page import="java.util.List" %>
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

    <title>User book page</title>
    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>

<body>

<div class="row">
    <jsp:include page="navigator.jsp"></jsp:include>
</div>

<div class="section no-pad-bot">
    <div class="container">

        <div class="row">
            <h4 class="red-text lighten-2">Book</h4>
        </div>

        <div class="row">
            <form class="col s12">
                <div class="row">
                    <div class="input-field col s12">
                        <input value="elo" id="title" type="text" class="validate">
                        <label for="title">Book title</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input value="elo" id="author_name" type="text" class="validate">
                        <label for="author_name">Author name</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col s12">
                        <input value="elo" id="author_surname" type="text" class="validate">
                        <label for="author_surname">Author surname</label>
                    </div>
                </div>

                <div class="row">
                    <label class="col s6">Rate Select</label><br>
                    <select class="browser-default col s6">
                        <option value="" disabled selected>Choose your option</option>
                        <option value="1">☆</option>
                        <option value="2">☆☆</option>
                        <option value="3" selected>☆☆☆</option>
                        <option value="4">☆☆☆☆</option>
                        <option value="5">☆☆☆☆☆</option>
                    </select>
                </div>

                <%--                <div class="row">--%>
                <%--                    <div class="input-field col s12">--%>
                <%--                        <input disabled value="I am not editable" id="disabled" type="text" class="validate">--%>
                <%--                        <label for="disabled">Disabled</label>--%>
                <%--                    </div>--%>
                <%--                </div>--%>

                <div class="row">
                    <div class="input-field col s12">
                        <textarea id="review_book" class="materialize-textarea"></textarea>
                        <label for="review_book">Review book</label>
                    </div>
                </div>
            </form>
        </div>

    </div>
</div>


<!--JavaScript at end of body for optimized loading-->
<script type="text/javascript" src="js/materialize.min.js"></script>
</body>
</html>