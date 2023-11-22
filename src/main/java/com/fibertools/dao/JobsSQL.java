package com.fibertools.dao;

import com.fibertools.models.Jobs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JobsSQL {

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
