package com.github.magdanadratowska.dao;

import com.github.magdanadratowska.model.Book;
import com.github.magdanadratowska.model.User;
import com.github.magdanadratowska.model.UserBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    private String jdbcURL = "jdbc:mysql://mysql10.mydevil.net:3306/m1448_proj_read?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String jdbcUsername = "m1448_javagda24";
    private String jdbcpassword = "j@vaGda24!";

    private Logger logger = LoggerFactory.getLogger(AccountDAO.class);

    private static final String SELECT_LAST_BOOKS = "select * from (select * from user_book, book where user_book.id_book = book.id) T where id_user = ? order by addition_date desc limit 1";
    private static final String ADD_BOOK_TO_USER_LIST = "insert into user_book (id_user, id_book, addition_date) values (?, ?, ?)";
    private static final String REMOVE_BOOK_FROM_USER_LIST = "delete from user_book where id_user=? and id_book=?";
    private static final String SELECT_USERS_LIST = "select * from (select * from user_book, book where (user_book.id_book = book.id AND is_active=true)) T where id_user =?";
    private static final String SELECT_ALL_BOOKS_LIST_FOR_CURRENT_USER_WITH_DELETED_BOOKS = "select * from book B left join (select id as id2, is_active from (select * from book, user_book where (user_book.id_book = book.id)) T where id_user =?) U on (U.id2 = B.id)";
    private static final String DELETE_BOOK_FROM_USER_LIST = "update user_book set is_active = false WHERE (id_user=? AND id_book=?)";
    private static final String RESTORE_BOOK_TO_USER_LIST = "update user_book set is_active = true WHERE (id_user=? AND id_book=?)";

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
                String addition_date = resultSet.getString("addition_date");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime additionDate = LocalDateTime.parse(addition_date, formatter);
                usersBookList.add(new UserBook(book, additionDate, rate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersBookList;
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

    @Deprecated
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

    public List<UserBook> getAllBookListForCurrentUserWithDeletedBooks(User user) {
        List<UserBook> usersBookList = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS_LIST_FOR_CURRENT_USER_WITH_DELETED_BOOKS);) {
            preparedStatement.setLong(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                String authorName = resultSet.getString("author_name");
                String authorSurname = resultSet.getString("author_surname");
                Book book = new Book(id, title, authorName, authorSurname);
                boolean isActive = resultSet.getBoolean("is_active");
                boolean isOwned = resultSet.getBoolean("id2");
                usersBookList.add(new UserBook(book, isActive, isOwned));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersBookList;
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

    public void deleteBookFromUserList(User user, Long bookId) {
        changeFlagIsActive(user, bookId, DELETE_BOOK_FROM_USER_LIST);
        logger.info("delete book from user list - userId {} idBook {}", user.getId(), bookId);
    }

    public void restoreBookToUserList(User user, Long bookId) {
        changeFlagIsActive(user, bookId, RESTORE_BOOK_TO_USER_LIST);
        logger.info("restore book from user list - userId {} idBook {}", user.getId(), bookId);
    }

    private void changeFlagIsActive(User user, Long bookId, String sql) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setLong(2, bookId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
