package com.github.magdanadratowska.web;

import com.github.magdanadratowska.dao.UserDAO;
import com.github.magdanadratowska.model.User;
import com.github.magdanadratowska.model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

@WebServlet ("/user-register")
public class UserRegisterServlet extends HttpServlet {
    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String passwordRepeat = req.getParameter("password-repeat");
        HttpSession session = req.getSession();
        if (password.equals(passwordRepeat)) {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setRegisterDate(LocalDateTime.now());
            user.setUserType(UserType.USER);


            session.setAttribute("loginError", null);
            session.setAttribute("userId", user.getId());
            session.setAttribute("userName", user.getUsername());
            session.setAttribute("userType", user.getUserType());
            resp.sendRedirect("/account");


            try {
                userDAO.addUser(user);
            } catch (SQLException e) {
                e.printStackTrace();
                //TODO przekazanie informacji o błędzie
            }
        } else {
            session.setAttribute("registerError", "repeatPasswordError");
            resp.sendRedirect("/login.jsp");
        }

    }
}
