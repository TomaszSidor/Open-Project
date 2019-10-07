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

    <title>List of books</title>
    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>

<body>

<div class="row">
    <jsp:include page="navigator.jsp"></jsp:include>
</div>

<div class="section no-pad-bot">
    <div class="container">
        <a class="btn waves-effect waves-light red lighten-2 white-text" href="/books-add">Add book
            <i class="material-icons right">add</i>
        </a>
        <h4 class="red-text lighten-2">List of all books</h4>

        <table class="highlight">
            <thead>
            <tr>
                <th>Title</th>
                <th>Author name</th>
                <th>Author surname</th>
                <th>Details</th>
                <th>My list</th>
            </tr>
            </thead>
            <tbody>

            <jsp:useBean id="userBookList" scope="request" type="java.util.List"/>
            <c:forEach var="userBook" items="${userBookList}">
                <tr>
                    <td>${userBook.book.title}</td>
                    <td>${userBook.book.authorName}</td>
                    <td>${userBook.book.authorSurname}</td>

                    <td>
                        <c:choose>
                            <c:when test="${userBook.isOwned}">
                                <form action="/account/books-edit" method="get">
                                    <input type="hidden" id="id" name="id" value="${userBook.book.id}">
                                    <button class="btn waves-effect waves-light white red-text lighten-2" type="submit">
                                        <i class="material-icons">description</i>
                                    </button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form action="/books-edit" method="get">
                                    <input type="hidden" id="id_e" name="id" value="${userBook.book.id}">
                                    <button class="btn waves-effect waves-light white red-text lighten-2" type="submit">
                                        <i class="material-icons">edit</i>
                                    </button>
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <td>
                        <c:choose>
                            <c:when test="${userBook.isOwned}">

                                <c:choose>
                                    <c:when test="${userBook.isActive}">
                                        <form action="/account/books-delete?id=${userBook.book.id}" method="post">
                                            <button class="btn waves-effect waves-light white red-text lighten-2"
                                                    type="submit"
                                                    name="action">
                                                <i class="material-icons">star</i>
                                            </button>
                                        </form>

                                    </c:when>
                                    <c:otherwise>
                                        <form action="/account/books-add?id=${userBook.book.id}&isOwned=true"
                                              method="post">
                                            <button class="btn waves-effect waves-light white red-text lighten-2"
                                                    type="submit"
                                                    name="action">
                                                <i class="material-icons">delete_forever</i>
                                            </button>
                                        </form>

                                    </c:otherwise>
                                </c:choose>

                            </c:when>
                            <c:otherwise>
                                <form action="/account/books-add?id=${userBook.book.id}" method="post">
                                    <button class="btn waves-effect waves-light white red-text lighten-2" type="submit"
                                            name="action">
                                        <i class="material-icons">star_border</i>
                                    </button>
                                </form>
                            </c:otherwise>
                        </c:choose>

                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <br>
        <ul class="pagination center">


            <%--For displaying Previous link except for the 1st page --%>
            <c:choose>
                <c:when test="${currentPage != 1}">
                    <li class="waves-effect"><a href="/books?page=${currentPage - 1}"><i class="material-icons">chevron_left</i></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="disabled"><a href=""><i
                            class="material-icons">chevron_left</i></a>
                    </li>
                </c:otherwise>
            </c:choose>
            <%--For displaying Page numbers.
            The when condition does not display a link for the current page--%>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <%--                            <td>${i}</td>--%>
                        <li class="active"><a href="#!">${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <%--                            <td><a href="/books?page=${i}">${i}</a></td>--%>
                        <li class="waves-effect"><a href="/books?page=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <%--For displaying Next link --%>
            <c:choose>
                <c:when test="${currentPage lt noOfPages}">
                    <li class="waves-effect"><a href="/books?page=${currentPage + 1}"><i class="material-icons">chevron_right</i></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="disabled"><a href=""><i class="material-icons">chevron_right</i></a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>


    </div>
</div>


<!--JavaScript at end of body for optimized loading-->
<script type="text/javascript" src="js/materialize.min.js"></script>
</body>
</html>