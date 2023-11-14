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
                return;
            }

            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            System.out.println("DB Connection successful!");
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    private static boolean databaseExists() throws SQLException {
        try (Connection tempConnection = DriverManager.getConnection("jdbc:mysql://localhost/", userName, password);
             Statement statement = tempConnection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SHOW DATABASES LIKE '" + databaseName + "'");
            return resultSet.next();
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("DB Connection closed!");
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
