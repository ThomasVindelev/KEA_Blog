package com.keablog.demo.Database;
import com.keablog.demo.Objects.Message;
import com.keablog.demo.Objects.User;

import java.sql.*;

public class Database {

    private PreparedStatement preparedStatement;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private String query;

    public Database() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost/blog?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", "root", "");
    }

    public void createPost(Message message) throws SQLException {
        query = "INSERT INTO blogposts (`id_username`,`post_title`, `post_text`) VALUES (?, ?, ?)";
        executeQuery(query, message);
    }

    public void executeQuery(String query, Message message) throws SQLException {
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, message.getTitle());
        preparedStatement.setString(3, message.getText());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public ResultSet getNewestPosts() throws SQLException {
        query = "SELECT id_user, post_title, post_text FROM blogposts INNER JOIN users ON blogposts.id_user = users.username LIMIT 10 ORDER BY id DESC";
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        return resultSet;
    }







}