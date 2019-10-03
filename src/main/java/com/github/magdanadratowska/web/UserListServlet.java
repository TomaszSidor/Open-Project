package com.github.magdanadratowska.web;

import com.github.magdanadratowska.dao.UserDAO;
import com.github.magdanadratowska.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user-list")
public class UserListServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userType = String.valueOf(req.getSession().getAttribute("userType"));
        boolean isAdmin = userType.equals("ADMIN");
        if (!isAdmin) {
            resp.sendRedirect("/login");

        } else {

            List<User> allUsers = new ArrayList<>();
            try {
                allUsers = userDAO.getAllUsers();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            req.setAttribute("users", allUsers);
            req.getRequestDispatcher("user-list.jsp").forward(req, resp);
        }
    }
}
