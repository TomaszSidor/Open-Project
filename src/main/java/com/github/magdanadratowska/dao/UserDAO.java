package com.github.magdanadratowska.dao;

import com.github.magdanadratowska.model.User;

import java.sql.*;
import java.util.Optional;

public class UserDAO {

    private String jdbcURL = "jdbc:mysql://mysql10.mydevil.net:3306/m1448_proj_read?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String jdbcUsername = "m1448_javagda24";
    private String jdbcpassword = "j@vaGda24!";
    private static final String SELECT_USER_BY_ID = "select * from user where id=?;";
    private static final String SELECT_USER_BY_EMAIL = "select * from user where email=?;";
    private static final String ADD_USER = "INSERT INTO user (username, email, password, register_date) VALUES (?, ?, ?, ?);";

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

    public Optional<User> getUserById(Long i) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setLong(1, i);
            ResultSet resultSet = preparedStatement.executeQuery();
            return Optional.ofNullable(getUserFromDB(resultSet));
        }
    }

    private User getUserFromDB(ResultSet resultSet) throws SQLException {
        User user = null;
        while (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getLong("id"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setRegisterDate(resultSet.getTimestamp("register_date").toLocalDateTime());
        }
        return user;
    }

    public boolean addUser(User user) throws SQLException {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getPassword());
                statement.setTimestamp(4, Timestamp.valueOf(user.getRegisterDate()));
                statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    Long generatedKey = resultSet.getLong(1);
                    System.out.println("Utworzono rekord o numerze " + generatedKey);
                    return true;
                }
            }
            return false;
        }
    }

    public Optional<User> getUserByEmail(String email) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            return Optional.ofNullable(getUserFromDB(resultSet));
        }
    }
}
