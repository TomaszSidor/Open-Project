package com.github.magdanadratowska.web;


import com.github.magdanadratowska.dao.UserBookDAO;
import com.github.magdanadratowska.model.User;
import com.github.magdanadratowska.model.UserBook;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = {"/books"}, name = "BooksServlet")
public class BooksServlet extends HttpServlet {

    private UserBookDAO userBookDAO;

    public void init() {
        userBookDAO = new UserBookDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            getBooks(request, response);
        } catch (SQLException e) {
            System.out.println("error");
        }

    }

    private void getBooks(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        Optional<Object> objectUserId = Optional.ofNullable(session.getAttribute("userId"));
        long userId = objectUserId.map(o -> Long.parseLong(o.toString())).orElse(0L);
        User user = new User(userId);

        RequestDispatcher dispatcher;
        Optional<String> id = Optional.ofNullable(request.getParameter("id"));
        if (id.isPresent()){
//            Optional<UserBook> book = userBookDAO.(Long.parseLong(id.get()));
//            book.ifPresent(b -> request.setAttribute("bookList", Collections.singletonList(b)));
            dispatcher = request.getRequestDispatcher("booklist.jsp");
        } else {
            List<UserBook> userBookList = userBookDAO.getAllBookListForCurrentUserWithDeletedBooks(user);
            request.setAttribute("userBookList", userBookList);
            dispatcher = request.getRequestDispatcher("booklist.jsp");
        }
        dispatcher.forward(request, response);
    }
}
