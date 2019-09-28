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
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/userlist")
public class UsersListServlet extends HttpServlet {

    private UserBookDAO userBookDAO;

    public void init() {
        userBookDAO = new UserBookDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            userlist(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void userlist(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        User user = new User();
        user.setId(1L);
        List<UserBook> usersBookList = userBookDAO.getUsersBookList(user);
        request.setAttribute("usersBookList", usersBookList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("userlist.jsp");
        dispatcher.forward(request, response);
    }
}
