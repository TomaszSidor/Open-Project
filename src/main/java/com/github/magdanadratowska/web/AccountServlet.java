package com.github.magdanadratowska.web;

import com.github.magdanadratowska.dao.AccountDAO;
import com.github.magdanadratowska.model.Book;
import com.github.magdanadratowska.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
    private AccountDAO accountDAO;

    public void init() {
        accountDAO = new AccountDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            lastBook(request, response);

        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void lastBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        Book lastBook = accountDAO.getLastReadBook();
        request.setAttribute("lastReadBook", lastBook);
        RequestDispatcher dispatcher = request.getRequestDispatcher("account.jsp");
        dispatcher.forward(request, response);
    }
}
