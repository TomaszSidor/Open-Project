package com.github.modsezam;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String nameUser;
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

            Connection connection = driver.connect("jdbc:mysql://mysql10.mydevil.net:3306/m1448_proj_read?user=m1448_javagda24&password=j@vaGda24!&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", null);

            Statement statement = connection.createStatement();
            if(statement.execute("SELECT * from book")){
                ResultSet resultSet = statement.getResultSet();

                response.getWriter().println("test");

                while (resultSet.next()){
                    nameUser = resultSet.getString("title") + " " + resultSet.getString("a_surname");
                    response.getWriter().println(nameUser);
                }
            }
            connection.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}