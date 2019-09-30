package com.github.magdanadratowska.web;

import com.github.magdanadratowska.dao.AccountDAO;
import com.github.magdanadratowska.model.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(urlPatterns = "/account", name = "AccountServlet")

public class AccountServlet extends HttpServlet {
    private AccountDAO accountDAO;
    private int idUser = 3;

    public void init() {
        accountDAO = new AccountDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(">in");
        String action = request.getServletPath();
        System.out.println(">" + action);
        try {
            switch (action) {
                case "/account/book/add":
                    addBookToUserList(request, response);
                    break;
            }

        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/account":
                    showAccount(request, response);
                    break;
                case "/account/books/list":
                    userListOfBook(request, response);
                    break;
            }

        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void userListOfBook(HttpServletRequest request, HttpServletResponse response) {
        //metoda z UserListServlet(userlist).
    }

    private void addBookToUserList(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String idBookString = request.getParameter("id");
        long idBook;
        try {
            idBook = Long.parseLong(idBookString);
            accountDAO.addBookToUserList(idBook, idUser);
            //przejście do strony książki
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //obsługa błędu
        }
    }

    private void showAccount(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        Book lastBook = accountDAO.getLastReadBook();
        request.setAttribute("lastReadBook", lastBook);
        RequestDispatcher dispatcher = request.getRequestDispatcher("account.jsp");
        dispatcher.forward(request, response);
    }
}
