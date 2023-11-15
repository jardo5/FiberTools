package com.fibertools.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class JDBC {

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "FiberTools";
    private static final String jdbcUrl =
            protocol +
                    vendor +
                    location +
                    databaseName +
                    "?connectionTimeZone=SERVER&createDatabaseIfNotExist=true"; // Add createDatabaseIfNotExist parameter
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "root"; // Username
    private static String password = System.getenv("DB_PASSWORD"); // Password
    public static Connection connection; // Connection Interface

    public static void openConnection() {
        try {
            Class.forName(driver);

            // Check if the database already exists
            if (databaseExists()) {
                System.out.println("Existing FiberTools Database Found - Skipping Creation");
            } else {
                createDatabase(); // Create the database if it doesn't exist
            }

            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            System.out.println("DB Connection successful!");

            createUsersTable(); // Create the users table
            insertSampleUser(); // Insert a sample user

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    private static void createDatabase() {
        try (Connection tempConnection = DriverManager.getConnection("jdbc:mysql://localhost/", userName, password);
             Statement statement = tempConnection.createStatement()) {
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + databaseName);
            System.out.println("FiberTools Database Created!");
        } catch (SQLException e) {
            System.out.println("Error creating database:" + e.getMessage());
        }
    }

    private static boolean databaseExists() throws SQLException {
        try (Connection tempConnection = DriverManager.getConnection("jdbc:mysql://localhost/", userName, password);
             Statement statement = tempConnection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SHOW DATABASES LIKE '" + databaseName + "'");
            return resultSet.next();
        }
    }

    private static void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "username VARCHAR(50) NOT NULL," +
                    "password VARCHAR(50) NOT NULL" +
                    ")";
            statement.executeUpdate(createTableSQL);
            System.out.println("Users Table Created!");
        } catch (SQLException e) {
            System.out.println("Error creating users table:" + e.getMessage());
        }
    }

    private static void insertSampleUser() {
        try (Statement statement = connection.createStatement()) {
            String insertUserSQL = "INSERT INTO users (username, password) VALUES ('user', 'user')";
            statement.executeUpdate(insertUserSQL);
            System.out.println("Sample user inserted!");
        } catch (SQLException e) {
            System.out.println("Error inserting sample user:" + e.getMessage());
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
                System.out.println("DB Connection closed!");
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
