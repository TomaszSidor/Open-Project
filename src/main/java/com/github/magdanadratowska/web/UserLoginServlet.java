package com.github.magdanadratowska.web;

import com.github.magdanadratowska.dao.UserDAO;
import com.github.magdanadratowska.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
    UserDAO userDAO = new UserDAO();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        User user;
        try {
            Optional<User> optionalUser = userDAO.getUserByEmail(email);
            if (!optionalUser.isPresent()) {
                session.setAttribute("loginError", "userNotFound");
                resp.sendRedirect("/login.jsp");
            } else {
                user = optionalUser.get();
                if (user.getPassword().equals(password)) {
                    session.setAttribute("userId", user.getId());
                    session.setAttribute("userName", user.getUsername());
                    session.setAttribute("userType", user.getUserType());
                    resp.sendRedirect("/account");
                } else {
                    System.out.println("Błędne hasło");//dla sprawdzenia
                    session.setAttribute("loginError", "wrongPassword");
                    resp.sendRedirect("/login.jsp");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
