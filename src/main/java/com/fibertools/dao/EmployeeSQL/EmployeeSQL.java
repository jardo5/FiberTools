package com.fibertools.dao.EmployeeSQL;

import com.fibertools.dao.JDBC;
import com.fibertools.models.Employees;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeSQL {


    public static ObservableList<Employees> getAllEmployees() {
        ObservableList<Employees> employeesList = FXCollections.observableArrayList();
        String query = "SELECT * FROM employees";

        try {
            Connection connection = JDBC.connection;
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int resEmployeeId = result.getInt("id");
                String resEmployeeName = result.getString("employee_name");
                String resEmployeePhoneNumber = result.getString("employee_phone");
                String resEmployeeEmail = result.getString("employee_email");
                String resEmployeeAddress = result.getString("employee_address");
                String resEmployeePosition = result.getString("employee_position");
                double resEmployeePayRate = result.getDouble("employee_rate");
                String resEmployeeCurrentAssignedJob = result.getString("employee_assigned_job");
                Employees queryResults = new Employees(resEmployeeId, resEmployeeName, resEmployeePhoneNumber, resEmployeeEmail, resEmployeeAddress, resEmployeePosition, resEmployeePayRate, resEmployeeCurrentAssignedJob);
                employeesList.add(queryResults);
            }
            return employeesList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void removeEmployee(int id) {
        try {
            Connection connection = JDBC.connection;


            String deleteEmployeeSQL = "DELETE FROM employees WHERE id = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteEmployeeSQL);
            deleteStatement.setInt(1, id);
            deleteStatement.executeUpdate();

            String updateIdsSQL = "UPDATE employees SET id = id - 1 WHERE id > ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateIdsSQL);
            updateStatement.setInt(1, id);
            updateStatement.executeUpdate();

            System.out.println("Employee with ID " + id + " removed successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
