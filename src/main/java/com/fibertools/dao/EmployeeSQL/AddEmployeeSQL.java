package com.fibertools.dao.EmployeeSQL;

import com.fibertools.dao.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddEmployeeSQL {

    public static int autoIncrementID(){
        String query = "SELECT MAX(id) FROM employees";
        try {
            Connection connection = JDBC.connection;
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            int employeeID = 0;
            while (result.next()) {
                employeeID = result.getInt("MAX(id)");
            }
            return employeeID + 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void addEmployee(int id, String employee_name, String employee_phone, String employee_email, String employee_address, String employee_position, double employee_rate, String employee_assigned_job){
        String query = "INSERT INTO employees (id, employee_name, employee_phone, employee_email, employee_address, employee_position, employee_rate, employee_assigned_job) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = JDBC.connection;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, employee_name);
            statement.setString(3, employee_phone);
            statement.setString(4, employee_email);
            statement.setString(5, employee_address);
            statement.setString(6, employee_position);
            statement.setDouble(7, employee_rate);
            statement.setString(8, employee_assigned_job);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean duplicateEmployeeName(String employee_name){
        String query = "SELECT * FROM employees WHERE employee_name = ?";
        try {
            Connection connection = JDBC.connection;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, employee_name);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
