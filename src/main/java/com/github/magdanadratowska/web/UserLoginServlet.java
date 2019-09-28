package com.github.magdanadratowska.web;

import com.github.magdanadratowska.dao.UserDAO;
import com.github.magdanadratowska.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet ("/login")
public class UserLoginServlet extends HttpServlet {
    UserDAO userDAO = new UserDAO();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user;
        try {
            Optional<User> optionalUser = userDAO.getUserByEmail(email);
            if (!optionalUser.isPresent()) {
                System.out.println("nie znaleziono usera"); //dla sprawdzenia
                //TODO nie znaleziono usera
            }
            user = optionalUser.get();

            if(user.getPassword().equals(password)) {
                HttpSession session=req.getSession();
                session.setAttribute("user", user);
                resp.sendRedirect("/account");
            } else {
                System.out.println("Błędne hasło");//dla sprawdzenia
//            TODO błędne hasło
            }
            System.out.println(user); //dla sprawdzenia
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/login");
    }
}
