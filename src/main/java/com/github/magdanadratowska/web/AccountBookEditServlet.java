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

@WebServlet(urlPatterns = "/account/books-edit", name = "AccountBookEditServlet")
public class AccountBookEditServlet extends HttpServlet {

    AccountDAO accountDAO;
    public void init() {
        accountDAO = new AccountDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        updateBooksRate(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void updateBooksRate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Optional<Object> objectUserId = Optional.ofNullable(session.getAttribute("userId"));
        long userId = objectUserId.map(o -> Long.parseLong(o.toString())).orElse(0L);
        long bookId = Long.parseLong(request.getParameter("bookId"));
        int rate = Integer.parseInt(request.getParameter("rate"));

       accountDAO.updateBookRate(rate, userId, bookId);
        response.sendRedirect(request.getHeader("referer"));
    }
}
