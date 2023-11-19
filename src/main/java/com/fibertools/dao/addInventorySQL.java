package com.fibertools.dao;

import com.fibertools.models.Jobs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class addInventorySQL {
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
}
