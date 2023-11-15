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
                    "?connectionTimeZone=SERVER&createDatabaseIfNotExist=true";
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String userName = "root";
    private static String password = System.getenv("DB_PASSWORD");
    public static Connection connection;

    public static void openConnection() {
        try {
            Class.forName(driver);

            if (databaseExists()) {
                System.out.println("Existing FiberTools Database Found - Skipping Creation");
            } else {
                createDatabase();
            }

            connection = DriverManager.getConnection(jdbcUrl, userName, password);
            System.out.println("DB Connection successful!");

            createUsersTable();
            insertSampleUser();

            createInventoryTable();
            insertSampleInventory();

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

    private static void createDatabase() {
        try (Connection tempConnection = DriverManager.getConnection("jdbc:mysql://localhost/", userName, password);
             Statement statement = tempConnection.createStatement()) {
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + databaseName);
            System.out.println("FiberTools Database Created!");
        } catch (SQLException e) {
            System.out.println("Error creating database:" + e.getMessage());
        }
    }

    //Users Table
    private static void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SHOW TABLES LIKE 'users'");
            if (!resultSet.next()) {
                String createTableSQL = "CREATE TABLE users (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "username VARCHAR(50) NOT NULL," +
                        "password VARCHAR(50) NOT NULL" +
                        ")";
                statement.executeUpdate(createTableSQL);
                System.out.println("Users Table Created!");
            } else {
                System.out.println("users Table already exists - Skipping creation");
            }
        } catch (SQLException e) {
            System.out.println("Error creating users table:" + e.getMessage());
        }
    }

    private static boolean userExists(String username) {
        try (Statement statement = connection.createStatement()) {
            String checkUserSQL = "SELECT * FROM users WHERE username = '" + username + "'";
            ResultSet resultSet = statement.executeQuery(checkUserSQL);
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Error checking user existence:" + e.getMessage());
            return false;
        }
    }

    //Sample User
    private static void insertUser(String username, String password) {
        if (!userExists(username)) {
            try (Statement statement = connection.createStatement()) {
                String insertUserSQL = "INSERT INTO users (username, password) VALUES ('" + username + "', '" + password + "')";
                statement.executeUpdate(insertUserSQL);
                System.out.println("User '" + username + "' inserted!");
            } catch (SQLException e) {
                System.out.println("Error inserting user:" + e.getMessage());
            }
        } else {
            System.out.println("User '" + username + "' already exists - Skipping insertion");
        }
    }

    private static void insertSampleUser() {
        insertUser("user", "user");
    }


    //End Users Table

    //Inventory Table
    private static void createInventoryTable() {
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery("SHOW TABLES LIKE 'inventory'");
            if (!resultSet.next()){
                String createTableSQL = "CREATE TABLE inventory (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "serial_number VARCHAR(50) NOT NULL," +
                        "name VARCHAR(50) NOT NULL," +
                        "type VARCHAR(50) NOT NULL," +
                        "description VARCHAR(100) NOT NULL," +
                        "quantity INT NOT NULL," +
                        "price DOUBLE NOT NULL," +
                        "assigned_job VARCHAR(50) NOT NULL," +
                        "last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP" +
                        ")";
                statement.executeUpdate(createTableSQL);
                System.out.println("Inventory Table Created!");
            } else {
                System.out.println("Inventory Table already exists - Skipping creation");
            }
        } catch (SQLException e) {
            System.out.println("Error creating inventory table:" + e.getMessage());
        }
    }
    
    private static boolean inventoryExists(String serial_number) {
        try (Statement statement = connection.createStatement()) {
            String checkInventorySQL = "SELECT * FROM inventory WHERE serial_number = '" + serial_number + "'";
            ResultSet resultSet = statement.executeQuery(checkInventorySQL);
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Error checking inventory existence:" + e.getMessage());
            return false;
        }
    }

    //Sample Inventory
    private static void insertInventory(String serial_number, String test_item, String test_type, String test_description, int test_quantity, double test_price, String test_job) {
        if (!inventoryExists(serial_number)) {
            try (Statement statement = connection.createStatement()) {
                String insertInventorySQL = "INSERT INTO inventory (serial_number, name, type, description, quantity, price, assigned_job) VALUES ('" + serial_number + "', '" + test_item + "', '" + test_type + "', '" + test_description + "', '" + test_quantity + "', '" + test_price + "', '" + test_job + "')";
                statement.executeUpdate(insertInventorySQL);
                System.out.println("Inventory '" + test_item + "' inserted!");
            } catch (SQLException e) {
                System.out.println("Error inserting inventory:" + e.getMessage());
            }
        } else {
            System.out.println("Inventory '" + test_item + "' already exists - Skipping insertion");
        }
    }

    private static void insertSampleInventory() {
        insertInventory("FOSC450B66241B3V", "Commscope FOSC 450 B Gel Closure", "Splice Enclosure", "Underground splice case for up to 96 splices", 32, 335.00, "Google Fiber");
    }
    //End Inventory Table



    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("DB Connection closed!");
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
