package com.fibertools.dao.SpliceRecordsSQL.SpliceRecords;

import com.fibertools.dao.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ModifySpliceRecordsSQL {

    public static void modifySpliceRecord(int splice_id, String splice_name, String splice_customer_name, String splice_location, int splice_count, String splice_notes, String splice_assigned_job, String splice_date){
        String query = "UPDATE splice_records SET splice_name = ?, splice_customer_name = ?, splice_location = ?, splice_count = ?, splice_notes = ?, splice_assigned_job = ?, splice_date = ? WHERE splice_id = ?";
        try {
            Connection connection = JDBC.connection;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, splice_name);
            statement.setString(2, splice_customer_name);
            statement.setString(3, splice_location);
            statement.setInt(4, splice_count);
            statement.setString(5, splice_notes);
            statement.setString(6, splice_assigned_job);
            statement.setString(7, splice_date);
            statement.setInt(8, splice_id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
