package com.github.magdanadratowska.dao;

import com.github.magdanadratowska.model.Book;
import com.github.magdanadratowska.model.User;

import java.sql.*;
import java.util.List;

public class AccountDAO {
    private String jdbcURL = "jdbc:mysql://mysql10.mydevil.net:3306/m1448_proj_read?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String jdbcUsername = "m1448_javagda24";
    private String jdbcpassword = "j@vaGda24!";
    private int userId = 1;
    //    private static final String SELECT_LAST_BOOKS = "select * from book";
    private static final String SELECT_LAST_BOOKS = "select * from (select * from user_book, book where user_book.id_book = book.id) T where id_user = ? order by addition_date desc limit 1";
    private static final String ADD_BOOK_TO_USER_LIST = "INSERT INTO user_book (id_user, id_book) VALUES (?, ?);";


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

    public Book getLastReadBook() {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        Book book = new Book();
        try {
            preparedStatement = connection.prepareStatement(SELECT_LAST_BOOKS);
            {
                preparedStatement.setLong(1, 1); //user.getId());

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("author_name"));
                    book.setAuthorName(resultSet.getString("author_name"));
                    book.setAuthorSurname(resultSet.getString("author_surname"));
                    book.setTitle(resultSet.getString("title"));
                    System.out.println(book.toString());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public boolean addBookToUserList(long idBook, long idUser) throws SQLException {

        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(ADD_BOOK_TO_USER_LIST, Statement.RETURN_GENERATED_KEYS)) {
                statement.setLong(1, idUser);
                statement.setLong(2, idBook);
                statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    long generatedKey = resultSet.getLong(1);
                    System.out.println("Utworzono rekord o numerze " + generatedKey);
                    return true;
                }
            }
            return false;
        }
    }

}
