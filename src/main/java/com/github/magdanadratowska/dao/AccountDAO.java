package com.github.magdanadratowska.dao;

import com.github.magdanadratowska.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;

public class AccountDAO {

    private String jdbcURL = "jdbc:mysql://mysql10.mydevil.net:3306/m1448_proj_read?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String jdbcUsername = "m1448_javagda24";
    private String jdbcpassword = "j@vaGda24!";

    private Logger logger = LoggerFactory.getLogger(AccountDAO.class);

    private static final String SELECT_LAST_BOOKS = "select * from (select * from user_book, book where user_book.id_book = book.id) T where id_user = ? order by addition_date desc limit 1";
    private static final String ADD_BOOK_TO_USER_LIST = "INSERT INTO user_book (id_user, id_book, addition_date) VALUES (?, ?, ?);";
    private static final String REMOVE_BOOK_FROM_USER_LIST = "delete from user_book where id_user=? and id_book=?";

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

    public void addBookToUserList(long idBook, long idUser) throws SQLException {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(ADD_BOOK_TO_USER_LIST, Statement.RETURN_GENERATED_KEYS)) {
                statement.setLong(1, idUser);
                statement.setLong(2, idBook);
                statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                statement.executeUpdate();
                logger.info("add book to user list - userId {} idBook {} timeStamp {}", idUser, idBook, Timestamp.valueOf(LocalDateTime.now()));
            }
        }
    }

    public void removeBookFromUserList(long idBook, long idUser) throws SQLException {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(REMOVE_BOOK_FROM_USER_LIST, Statement.RETURN_GENERATED_KEYS)) {
                statement.setLong(1, idUser);
                statement.setLong(2, idBook);
                statement.executeUpdate();
                logger.info("remove book from user list - userId {} idBook {}", idUser, idBook);
            }
        }
    }




}
