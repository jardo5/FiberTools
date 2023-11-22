package com.fibertools.dao.InventorySQL;

import com.fibertools.dao.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ModifyInventorySQL {

    public static void modifyInventoryItem(int id, String serial_number, String name, String type, String description, int quantity, double price, String assigned_job, Timestamp last_updated) {
        String query = "UPDATE inventory SET serial_number = ?, name = ?, type = ?, description = ?, quantity = ?, price = ?, assigned_job = ?, last_updated = ? WHERE id = ?";
        try {
            Connection connection = JDBC.connection;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, serial_number);
            statement.setString(2, name);
            statement.setString(3, type);
            statement.setString(4, description);
            statement.setInt(5, quantity);
            statement.setDouble(6, price);
            statement.setString(7, assigned_job);
            statement.setTimestamp(8, last_updated);
            statement.setInt(9, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
