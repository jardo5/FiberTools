package com.fibertools.dao;

import com.fibertools.models.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSQL {

    public static ObservableList<Users> getAllUsers() throws SQLException {
        ObservableList<Users> allUsers = FXCollections.observableArrayList();
        String query = "SELECT * FROM users";

        try (Connection connection = JDBC.connection;
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                int resUserId = result.getInt("id");
                String resUserName = result.getString("username");
                Users queryResults = new Users(resUserId, resUserName);
                allUsers.add(queryResults);
            }

            return allUsers;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Users loggedInUser(String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ? LIMIT 1";

        try {
            Connection connection = JDBC.connection;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                int resUserId = result.getInt("id");
                String resUserName = result.getString("username");
                Users loggedInUser = new Users(resUserId, resUserName);
                Users.setLoggedInUser(loggedInUser);
                return loggedInUser;
            } else {
                throw new SQLException("Invalid username or password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Error Logging In User: " + e.getMessage());
            return null;
        }
    }

    public static boolean isLoggedIn() {
        return Users.getLoggedInUser() != null;
    }
}
