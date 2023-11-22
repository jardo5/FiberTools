package com.fibertools.dao.SpliceRecordsSQL.SpliceRecords;

import com.fibertools.dao.JDBC;
import com.fibertools.models.SpliceRecords;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SpliceRecordsSQL {

    public static ObservableList<SpliceRecords> getAllSpliceRecords() {
        ObservableList<SpliceRecords> spliceRecordsList = FXCollections.observableArrayList();
        String query = "SELECT * FROM splice_records";
        try {
            Connection connection = JDBC.connection;
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int resSpliceId = result.getInt("splice_id");
                String resSpliceName = result.getString("splice_name");
                String resCustomerName = result.getString("splice_customer_name");
                String resSpliceLocation = result.getString("splice_location");
                int resSpliceCount = result.getInt("splice_count");
                String resSpliceNotes = result.getString("splice_notes");
                String resSpliceAssignedJob = result.getString("splice_assigned_job");
                String resSpliceDate = result.getString("splice_date");
                SpliceRecords queryResults = new SpliceRecords(resSpliceId, resSpliceName, resCustomerName, resSpliceLocation, resSpliceCount, resSpliceNotes, resSpliceAssignedJob, resSpliceDate);
                spliceRecordsList.add(queryResults);
            }
            return spliceRecordsList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void removeSpliceRecord(int splice_id) {
        try {
            Connection connection = JDBC.connection;

            String deleteInventoryItemSQL = "DELETE FROM splice_records WHERE splice_id = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteInventoryItemSQL);
            deleteStatement.setInt(1, splice_id);
            deleteStatement.executeUpdate();

            String updateIdsSQL = "UPDATE splice_records SET splice_id = splice_id - 1 WHERE splice_id > ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateIdsSQL);
            updateStatement.setInt(1, splice_id);
            updateStatement.executeUpdate();

            System.out.println("Splice Record with ID " + splice_id + " removed successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

