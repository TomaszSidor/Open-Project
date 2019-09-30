package com.github.magdanadratowska.web;

import com.github.magdanadratowska.dao.BookDAO;
import com.github.magdanadratowska.model.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = {"/books"}, name = "BooksServlet")
public class BooksServlet extends HttpServlet {

    private BookDAO bookDAO;

    public void init() {
        bookDAO = new BookDAO();
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

        RequestDispatcher dispatcher;
        Optional<String> id = Optional.ofNullable(request.getParameter("id"));
        if (id.isPresent()){
            Optional<Book> book = bookDAO.readBook(Long.parseLong(id.get()));
            book.ifPresent(b -> request.setAttribute("bookList", Collections.singletonList(b)));
            dispatcher = request.getRequestDispatcher("booklist.jsp");
        } else {
            List<Book> bookList = bookDAO.readBooksList();
            request.setAttribute("bookList", bookList);
            dispatcher = request.getRequestDispatcher("booklist.jsp");
        }
        dispatcher.forward(request, response);
    }
}
