package com.github.magdanadratowska.dao;

import com.github.magdanadratowska.model.Book;
import com.github.magdanadratowska.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private String jdbcURL = "jdbc:mysql://mysql10.mydevil.net:3306/m1448_proj_read?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String jdbcUsername = "m1448_javagda24";
    private String jdbcpassword = "j@vaGda24!";
    private static final String SELECT_USER_BY_ID = "select * from user where id=?;";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcpassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public User getUserById(Long i) {
        User user = new User();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);){
            preparedStatement.setLong(1, i);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(i);
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                String register_date = resultSet.getString("register_date");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                user.setRegisterDate(LocalDateTime.parse(register_date,formatter));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
