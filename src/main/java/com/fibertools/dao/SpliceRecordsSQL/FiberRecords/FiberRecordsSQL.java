package com.fibertools.dao.SpliceRecordsSQL.FiberRecords;

import com.fibertools.dao.JDBC;
import com.fibertools.models.Fibers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FiberRecordsSQL {

    public static ObservableList<Fibers> getAllFibersBySpliceID(int splice_id) {
        ObservableList<Fibers> fibersList = FXCollections.observableArrayList();
        String query = "SELECT * FROM fibers WHERE splice_id = ?";
        try {
            Connection connection = JDBC.connection;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, splice_id);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                int resFiberId = result.getInt("id");
                int resFiberNumber = result.getInt("fiber_number");
                double resFiberDistance = result.getDouble("distance");
                double resFiberSpanLoss = result.getDouble("span_loss");
                double resFiberAvgLoss = result.getDouble("avg_loss");
                double resFiberMaxLoss = result.getDouble("max_loss");
                String resFiberNotes = result.getString("notes");
                int resFiberSpliceId = result.getInt("splice_id");
                Fibers queryResults = new Fibers(resFiberId, resFiberNumber, resFiberDistance, resFiberSpanLoss, resFiberAvgLoss, resFiberMaxLoss, resFiberNotes, resFiberSpliceId);
                fibersList.add(queryResults);
            }
            return fibersList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
