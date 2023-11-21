package com.fibertools.dao;

import com.fibertools.models.Jobs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class AddInventorySQL {
    public static ObservableList<Jobs> getAllJobs() {
        ObservableList<Jobs> jobsList = FXCollections.observableArrayList();
        String query = "SELECT * FROM jobs";
        try {
            Connection connection = JDBC.connection;
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int resJobId = result.getInt("id");
                String resJobName = result.getString("job_name");
                Jobs job = new Jobs(resJobId, resJobName);
                jobsList.add(job);
            }
            return jobsList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //auto increment inventory id
    public static int autoInventoryID() {
        String query = "SELECT MAX(id) FROM inventory";
        try {
            Connection connection = JDBC.connection;
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            int inventoryID = 0;
            while (result.next()) {
                inventoryID = result.getInt("MAX(id)");
            }
            return inventoryID + 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    //add new inventory item to database
    public static void addInventoryItem(int id, String serial_number, String name, String type, String description, int quantity, double price, String assigned_job, Timestamp last_updated) {
        String query = "INSERT INTO inventory (id, serial_number, name, type, description, quantity, price, assigned_job, last_updated) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = JDBC.connection;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, serial_number);
            statement.setString(3, name);
            statement.setString(4, type);
            statement.setString(5, description);
            statement.setInt(6, quantity);
            statement.setDouble(7, price);
            statement.setString(8, assigned_job);
            statement.setTimestamp(9, last_updated);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean duplicateSerialNumber(String serial_number){
        String query = "SELECT * FROM inventory WHERE serial_number = ?";
        try {
            Connection connection = JDBC.connection;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, serial_number);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //add new assigned job to database
    public static void addNewAssignedJob(String job_name) {
        String query = "INSERT INTO jobs (job_name) VALUES (?)";
        try {
            Connection connection = JDBC.connection;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, job_name);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
