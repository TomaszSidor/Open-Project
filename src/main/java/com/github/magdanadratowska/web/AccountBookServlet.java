package com.github.magdanadratowska.web;

import com.github.magdanadratowska.dao.AccountDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet(urlPatterns = "/account/books", name = "AccountBookServlet")
public class AccountBookServlet extends HttpServlet {

    AccountDAO accountDAO;
    public void init() {
        accountDAO = new AccountDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        addBookToUserList(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        removeBookFromUserList(req, resp);
    }

    private void addBookToUserList(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        Optional<Object> objectUserId = Optional.ofNullable(session.getAttribute("userId"));
        long userId = objectUserId.map(o -> Long.parseLong(o.toString())).get();

        String idBookString = request.getParameter("id");
        long bookId;
        try {
            bookId = Long.parseLong(idBookString);
            accountDAO.addBookToUserList(bookId, userId);
            //przejście do strony książki
        } catch (SQLException e) {
            e.printStackTrace();
            //obsługa błędu
        }
    }

    private void removeBookFromUserList(HttpServletRequest req, HttpServletResponse resp) {
        String idBookString = req.getParameter("id");
        long idUser = 4L;
        long idBook;
        try {
            idBook = Long.parseLong(idBookString);
            accountDAO.removeBookFromUserList(idBook, idUser);
            //przejście do strony książki
        } catch (SQLException e) {
            e.printStackTrace();
            //obsługa błędu
        }
    }
}
