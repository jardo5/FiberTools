package com.fibertools.dao;

import java.sql.*;
import java.time.LocalDate;

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
    private static final String password = System.getenv("DB_PASSWORD");
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

            createJobsTable();
            insertSampleJob();

            createEmployeeTable();
            insertSampleEmployee();

            createSpliceRecordsTable();
            insertSampleSpliceRecord();

            createFiberRecordsTable();
            insertSampleFiberRecord();

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
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SHOW TABLES LIKE 'inventory'");
            if (!resultSet.next()) {
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

    //Jobs Table
    private static void createJobsTable() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SHOW TABLES LIKE 'jobs'");
            if (!resultSet.next()) {
                String createTableSQL = "CREATE TABLE jobs (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "job_name VARCHAR(50) NOT NULL" + ")";
                statement.executeUpdate(createTableSQL);
                System.out.println("Jobs Table Created!");
            } else {
                System.out.println("Jobs Table already exists - Skipping creation");
            }
        } catch (SQLException e) {
            System.out.println("Error creating jobs table:" + e.getMessage());
        }
    }

    private static boolean jobExists(String job_name) {
        try (Statement statement = connection.createStatement()) {
            String checkJobSQL = "SELECT * FROM jobs WHERE job_name = '" + job_name + "'";
            ResultSet resultSet = statement.executeQuery(checkJobSQL);
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Error checking job existence:" + e.getMessage());
            return false;
        }
    }

    //Sample Job
    private static void insertJob(String job_name) {
        if (!jobExists(job_name)) {
            try (Statement statement = connection.createStatement()) {
                String insertJobSQL = "INSERT INTO jobs (job_name) VALUES ('" + job_name + "')";
                statement.executeUpdate(insertJobSQL);
                System.out.println("Job '" + job_name + "' inserted!");
            } catch (SQLException e) {
                System.out.println("Error inserting job:" + e.getMessage());
            }
        } else {
            System.out.println("Job '" + job_name + "' already exists - Skipping insertion");
        }
    }

    private static void insertSampleJob() {
        insertJob("Google");
        insertJob("AT&T");
        insertJob("Verizon");
        insertJob("CenturyLink");
        insertJob("Comcast");
    }
    //End Jobs Table

    //Employees Table

    private static void createEmployeeTable() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SHOW TABLES LIKE 'employees'");
            if (!resultSet.next()) {
                String createTableSQL = "CREATE TABLE employees (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "employee_name VARCHAR(255) NOT NULL," +
                        "employee_phone VARCHAR(15)," +
                        "employee_email VARCHAR(255)," +
                        "employee_address VARCHAR(255)," +
                        "employee_position VARCHAR(50)," +
                        "employee_rate DOUBLE," +
                        "employee_assigned_job VARCHAR(255)" +
                        ")";
                statement.executeUpdate(createTableSQL);
                System.out.println("Employees Table Created!");
            } else {
                System.out.println("Employees Table already exists - Skipping creation");
            }
        } catch (SQLException e) {
            System.out.println("Error creating employees table:" + e.getMessage());
        }
    }

    private static boolean employeeExists(String employeeName) {
        try (Statement statement = connection.createStatement()) {
            String checkEmployeeSQL = "SELECT * FROM employees WHERE employee_name = '" + employeeName + "'";
            ResultSet resultSet = statement.executeQuery(checkEmployeeSQL);
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Error checking employee existence:" + e.getMessage());
            return false;
        }
    }

    private static void insertEmployee(String employeeName, String phoneNumber, String email, String address, String position, double payRate, String currentAssignedJob) {
        if (!employeeExists(employeeName)) {
            try (Statement statement = connection.createStatement()) {
                String insertEmployeeSQL = "INSERT INTO employees (employee_name, employee_phone, employee_email, employee_address, employee_position, employee_rate, employee_assigned_job) VALUES ('"
                        + employeeName + "', '" + phoneNumber + "', '" + email + "', '" + address + "', '" + position + "', " + payRate + ", '" + currentAssignedJob + "')";
                statement.executeUpdate(insertEmployeeSQL);
                System.out.println("Employee '" + employeeName + "' inserted!");
            } catch (SQLException e) {
                System.out.println("Error inserting employee:" + e.getMessage());
            }
        } else {
            System.out.println("Employee '" + employeeName + "' already exists - Skipping insertion");
        }
    }

    private static void insertSampleEmployee() {
        insertEmployee("John Doe", "123456789", "john.doe@example.com", "123 Main St", "Splicer", 50.0, "Comcast");
    }

    //End Employees Table

    //Splice Records Table

    private static void createSpliceRecordsTable() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SHOW TABLES LIKE 'splice_records'");
            if (!resultSet.next()) {
                String createTableSQL = "CREATE TABLE splice_records (" +
                        "splice_id INT AUTO_INCREMENT PRIMARY KEY," +
                        "splice_name VARCHAR(50) NOT NULL," +
                        "splice_customer_name VARCHAR(100) NOT NULL," +
                        "splice_location VARCHAR(50) NOT NULL," +
                        "splice_count INT NOT NULL," +
                        "splice_notes VARCHAR(100) NOT NULL," +
                        "splice_assigned_job VARCHAR(50) NOT NULL," +
                        "splice_date DATE" + ")";
                statement.executeUpdate(createTableSQL);
                System.out.println("Splice Records Table Created!");
            } else {
                System.out.println("Splice Records Table already exists - Skipping creation");
            }
        } catch (SQLException e) {
            System.out.println("Error creating splice records table:" + e.getMessage());
        }
    }

    private static boolean spliceRecordExists(String splice_name) {
        try (Statement statement = connection.createStatement()) {
            String checkSpliceRecordSQL = "SELECT * FROM splice_records WHERE splice_name = '" + splice_name + "'";
            ResultSet resultSet = statement.executeQuery(checkSpliceRecordSQL);
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Error checking splice record existence:" + e.getMessage());
            return false;
        }
    }

    private static void insertSpliceRecord(String splice_name, String splice_customer_name, String splice_location, int splice_count, String splice_notes, String splice_assigned_job, LocalDate splice_date) {
        if (!spliceRecordExists(splice_name)) {
            try (Statement statement = connection.createStatement()) {
                String insertSpliceRecordSQL = "INSERT INTO splice_records (splice_name, splice_customer_name, splice_location, splice_count, splice_notes, splice_assigned_job, splice_date) VALUES ('"
                        + splice_name + "', '" + splice_customer_name + "', '" + splice_location + "', '" + splice_count + "', '" + splice_notes + "', '" + splice_assigned_job + "', '" + splice_date + "')";
                statement.executeUpdate(insertSpliceRecordSQL);
                System.out.println("Splice Record '" + splice_name + "' inserted!");
            } catch (SQLException e) {
                System.out.println("Error inserting splice record:" + e.getMessage());
            }
        } else {
            System.out.println("Splice Record '" + splice_name + "' already exists - Skipping insertion");
        }
    }

    private static void insertSampleSpliceRecord() {
        insertSpliceRecord("Google Building Patch Panel 54", "Google", "123 Main St", 24, "Example Notes", "Google", LocalDate.of(2023, 11, 21));
    }

    //End Splice Records Table

    //Fiber Records Table

    //splice_id foreign key references splice_records(splice_id)
    private static void createFiberRecordsTable() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SHOW TABLES LIKE 'fibers'");
            if (!resultSet.next()) {
                String createTableSQL = "CREATE TABLE fibers (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY," +
                        "fiber_number INT NOT NULL," +
                        "distance DOUBLE NOT NULL," +
                        "span_loss DOUBLE NOT NULL," +
                        "avg_loss DOUBLE NOT NULL," +
                        "max_loss DOUBLE NOT NULL," +
                        "notes VARCHAR(100) NOT NULL," +
                        "splice_id INT NOT NULL," +
                        "FOREIGN KEY (splice_id) REFERENCES splice_records(splice_id)" +
                        ")";
                statement.executeUpdate(createTableSQL);
                System.out.println("Fiber Records Table Created!");
            } else {
                System.out.println("Fiber Records Table already exists - Skipping creation");
            }
        } catch (SQLException e) {
            System.out.println("Error creating fiber records table:" + e.getMessage());
        }

    }

    private static boolean fiberRecordExists(int splice_id) {
        try (Statement statement = connection.createStatement()) {
            String checkFiberRecordSQL = "SELECT * FROM fibers WHERE splice_id = '" + splice_id + "'";
            ResultSet resultSet = statement.executeQuery(checkFiberRecordSQL);
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Error checking fiber record existence:" + e.getMessage());
            return false;
        }
    }

    private static void insertFiberRecord(int fiber_number, double distance, double span_loss, double avg_loss, double max_loss, String notes, int splice_id) {
        if (!fiberRecordExists(splice_id)) {
            try (Statement statement = connection.createStatement()) {
                String insertFiberRecordSQL = "INSERT INTO fibers (Fiber_Number, Distance, Span_Loss, Avg_Loss, Max_Loss, Notes, splice_id) VALUES ('"
                        + fiber_number + "', '" + distance + "', '" + span_loss + "', '" + avg_loss + "', '" + max_loss + "', '" + notes + "', '" + splice_id + "')";
                statement.executeUpdate(insertFiberRecordSQL);
                System.out.println("Fiber Record '" + splice_id + "' inserted!");
            } catch (SQLException e) {
                System.out.println("Error inserting fiber record:" + e.getMessage());
            }
        } else {
            System.out.println("Fiber Record '" + splice_id + "' already exists - Skipping insertion");
        }
    }

    private static void insertSampleFiberRecord() {
        insertFiberRecord(1, 73231.0, 4.52, .7, 1.6, "Example Notes", 1);
    }

    //End Fiber Records Table


    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("DB Connection closed!");
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
