package com.github.magdanadratowska.dao;

import com.github.magdanadratowska.model.Book;
import com.github.magdanadratowska.model.User;
import com.github.magdanadratowska.model.UserBook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserBookDAO {

    private String jdbcURL = "jdbc:mysql://mysql10.mydevil.net:3306/m1448_proj_read?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String jdbcUsername = "m1448_javagda24";
    private String jdbcpassword = "j@vaGda24!";
    private static final String SELECT_USERS_LIST = "select * from (select * from user_book, book where user_book.id_book = book.id) T where id_user =?;";

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

    public List<UserBook> getUsersBookList(User user) {
        List<UserBook> usersBookList = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_LIST);) {
            preparedStatement.setLong(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int rate = resultSet.getInt("rate");
                long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                String authorName = resultSet.getString("author_name");
                String authorSurname = resultSet.getString("author_surname");
                Book book = new Book(id, title, authorName, authorSurname);
                usersBookList.add(new UserBook(book, rate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersBookList;
    }
}
