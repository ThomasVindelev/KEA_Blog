package com.keablog.demo.Database;
import com.keablog.demo.Objects.Message;
import com.keablog.demo.Objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class Database {

    private PreparedStatement preparedStatement;
    private Connection connection = DriverManager.getConnection("jdbc:mysql://den1.mysql3.gear.host:3306/keablog?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", "keablog", "Rf40H?f1LB?V");;
    private Statement statement;
    private ResultSet resultSet;
    private String query;

    public Database() throws SQLException {

    }

    public void createPost(Message message) throws SQLException {
        query = "INSERT INTO blogposts (`id_username`,`post_title`, `post_text`) VALUES (?, ?, ?)";
        sendPostQuery(query, message);
    }

    public void sendPostQuery(String query, Message message) throws SQLException {
        getConnection();
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, 2);
        preparedStatement.setString(2, message.getTitle());
        preparedStatement.setString(3, message.getText());
        preparedStatement.executeUpdate();
        connection.close();
        preparedStatement.close();
    }

    public ResultSet getPosts(int choice) throws SQLException {
        getConnection();
        if (choice == 1) {
            query = "SELECT * FROM blogposts INNER JOIN users ON blogposts.id_username = users.id_users ORDER BY id DESC LIMIT 10";
        } else {
            query = "SELECT * FROM blogposts INNER JOIN users ON blogposts.id_username = users.id_users ORDER BY id DESC";
        }
        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        connection.close();
        return resultSet;
    }

    public ResultSet verifyUser(User user) throws SQLException {
        getConnection();
        query = "SELECT * FROM users WHERE username = '" + user.getUsername() + "' AND password = '" + user.getPassword() + "'";
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        connection.close();
        return resultSet;
    }

    public Connection getConnection() {
        return connection;
    }
}