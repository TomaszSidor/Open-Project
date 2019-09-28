package com.github.magdanadratowska.controller;

import com.github.magdanadratowska.user.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet ("/user-register")
public class UserRegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("user-register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String passwordRepeat = req.getParameter("password-repeat");
        if (password.equals(passwordRepeat)) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            System.out.println(user.toString());// sprawdzenie czy działa
            //TODO - dodanie usera do bazy danych
        }

    }
}
