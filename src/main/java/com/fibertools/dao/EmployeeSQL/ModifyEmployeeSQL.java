package com.fibertools.dao.EmployeeSQL;

import com.fibertools.dao.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ModifyEmployeeSQL {

    public static void addModifiedEmployee(int id, String employee_name, String employee_phone, String employee_email, String employee_address, String employee_position, double employee_rate, String employee_assigned_job) {
        String query = "UPDATE employees SET employee_name = ?, employee_phone = ?, employee_email = ?, employee_address = ?, employee_position = ?, employee_rate = ?, employee_assigned_job = ? WHERE id = ?";
        try {
            Connection connection = JDBC.connection;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, employee_name);
            statement.setString(2, employee_phone);
            statement.setString(3, employee_email);
            statement.setString(4, employee_address);
            statement.setString(5, employee_position);
            statement.setDouble(6, employee_rate);
            statement.setString(7, employee_assigned_job);
            statement.setInt(8, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
