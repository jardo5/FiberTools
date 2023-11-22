package com.fibertools.dao.SpliceRecordsSQL.SpliceRecords;

import com.fibertools.dao.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddSpliceRecordSQL {

    public static int autoSpliceID(){
        String query = "SELECT MAX(splice_id) FROM splice_records";
        try {
            Connection connection = JDBC.connection;
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            int spliceID = 0;
            while(result.next()){
                spliceID = result.getInt("MAX(splice_id)");
            }
            return spliceID + 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


    public static void addSpliceRecord(int splice_id, String splice_name, String splice_customer_name, String splice_location, int splice_count, String splice_notes, String splice_assigned_job, String splice_date){
        String query = "INSERT INTO splice_records (splice_id, splice_name, splice_customer_name, splice_location, splice_count, splice_notes, splice_assigned_job, splice_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = JDBC.connection;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, splice_id);
            statement.setString(2, splice_name);
            statement.setString(3, splice_customer_name);
            statement.setString(4, splice_location);
            statement.setInt(5, splice_count);
            statement.setString(6, splice_notes);
            statement.setString(7, splice_assigned_job);
            statement.setString(8, splice_date);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
