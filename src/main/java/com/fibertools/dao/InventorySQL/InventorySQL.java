package com.fibertools.dao.InventorySQL;

import com.fibertools.dao.JDBC;
import com.fibertools.models.Inventory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class InventorySQL {

    public static ObservableList<Inventory> getAllInventory() {
        ObservableList<Inventory> inventoryList = FXCollections.observableArrayList();
        String query = "SELECT * FROM inventory";

        try {
            Connection connection = JDBC.connection;
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int resInventoryId = result.getInt("id");
                String resInventorySerialNumber = result.getString("serial_number");
                String resInventoryName = result.getString("name");
                String resInventoryType = result.getString("type");
                String resInventoryDescription = result.getString("description");
                int resInventoryQuantity = result.getInt("quantity");
                double resInventoryPrice = result.getDouble("price");
                String resInventoryAssignedJob = result.getString("assigned_job");
                String resInventoryLastUpdated = result.getString("last_updated");
                Inventory queryResults = new Inventory(resInventoryId, resInventorySerialNumber, resInventoryName, resInventoryType, resInventoryDescription, resInventoryQuantity, resInventoryPrice, resInventoryAssignedJob, resInventoryLastUpdated);
                inventoryList.add(queryResults);

            }
            return inventoryList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void removeInventoryItem(int id){
        String query = "DELETE FROM inventory WHERE id = ?";
        try {
            Connection connection = JDBC.connection;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
