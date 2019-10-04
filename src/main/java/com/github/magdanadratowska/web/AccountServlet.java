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

@WebServlet(urlPatterns = "/account", name = "AccountServlet")

public class AccountServlet extends HttpServlet {
    private AccountDAO accountDAO;
    private int idUser = 3;

    public void init() {
        accountDAO = new AccountDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        showAccount(request, response);
    }

    private void showAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book lastBook = accountDAO.getLastReadBook();
        request.setAttribute("lastReadBook", lastBook);
        request.setAttribute("testAttribute", 5);
        RequestDispatcher dispatcher = request.getRequestDispatcher("account.jsp");
        dispatcher.forward(request, response);
    }
}
