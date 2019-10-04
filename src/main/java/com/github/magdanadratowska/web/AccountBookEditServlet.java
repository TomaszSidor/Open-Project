package com.github.magdanadratowska.web;

import com.github.magdanadratowska.dao.AccountDAO;
import com.github.magdanadratowska.model.User;
import com.github.magdanadratowska.model.UserBook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/account/books-edit", name = "AccountBookEditServlet")
public class AccountBookEditServlet extends HttpServlet {

    private AccountDAO accountDAO;
    public void init() {
        accountDAO = new AccountDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        updateBooksRate(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long userId = getLongFromRequest(request);
        long bookId = Long.parseLong(request.getParameter("id"));
        User user = new User(userId);

        UserBook userBook = accountDAO.getBookDetailForCurrentUser(bookId, user);
        request.setAttribute("userBook", userBook);
        request.setAttribute("userBook2", "elo8");
        request.getRequestDispatcher("../userbookedit.jsp").forward(request, response);

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


    private long getLongFromRequest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Optional<Object> objectUserId = Optional.ofNullable(session.getAttribute( "userId"));
        return objectUserId.map(o -> Long.parseLong(o.toString())).orElse(0L);

    }
}
