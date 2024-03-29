package com.fibertools.controllers.EmployeeControllers;

import com.fibertools.dao.EmployeeSQL.AddEmployeeSQL;
import com.fibertools.dao.JobsSQL;
import com.fibertools.models.Jobs;
import com.fibertools.utils.FXMLLoaderUtils;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AddEmployeeController {

    @FXML
    public BorderPane contents;
    public TextField addEmployeeID;
    public TextField addEmployeePhone;
    public TextField addEmployeeAddress;
    public ComboBox<String> addEmployeePosition;
    public TextField addEmployeeName;
    public TextField addEmployeeEmail;
    public TextField addEmployeeRate;
    public ComboBox<Jobs> addEmployeeAssignedJob;
    public MFXButton addEmployeeAddButton;
    public MFXButton addEmployeeCancelButton;

    public static void errorAlert(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error");
        alert.setContentText(error);
        alert.showAndWait();
    }

    public static void successAlert(String success) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Success");
        alert.setContentText(success);
        alert.showAndWait();
    }

    public void setContents(BorderPane contents) {
        this.contents = contents;
    }

    public void initialize() {
        //Sets auto increment employee id
        addEmployeeID.setText(String.valueOf(AddEmployeeSQL.autoIncrementID()));

        //Populated Assigned Job ComboBox with Jobs from database
        addEmployeeAssignedJob.setItems(JobsSQL.getAllJobs());

        //Populated Position ComboBox with Positions
        ObservableList<String> typeList = addEmployeePosition.getItems();
        typeList.add("Foreman");
        typeList.add("Project Manager");
        typeList.add("Splicer");
        typeList.add("Lineman");
        typeList.add("Operator");
        typeList.add("Groundman");
        typeList.add("Apprentice");
        typeList.add("Office Staff");
        typeList.add("Other");


    }

    private boolean validateFields() {
        return !addEmployeeName.getText().isEmpty() &&
                !addEmployeePhone.getText().isEmpty() &&
                !addEmployeeEmail.getText().isEmpty() &&
                !addEmployeeAddress.getText().isEmpty() &&
                !addEmployeePosition.getSelectionModel().isEmpty() &&
                !addEmployeeRate.getText().isEmpty() &&
                !addEmployeeAssignedJob.getSelectionModel().isEmpty();
    }

    private boolean validateEmail() {
        String email = addEmployeeEmail.getText();
        if (email.contains("@") && email.contains(".")) {
            return true;
        } else {
            errorAlert("Invalid Email");
            return false;
        }
    }

    private boolean validatePhone() {
        String phone = addEmployeePhone.getText();
        if (phone.length() == 10) {
            return true;
        } else {
            errorAlert("Invalid Phone Number (10 digits no spaces)");
            return false;
        }
    }

    private boolean validateInput() {
        return validateFields() && validateEmail() && validatePhone() && !AddEmployeeSQL.duplicateEmployeeName(addEmployeeName.getText());
    }

    public void onClickAddEmployeeAddButton(ActionEvent actionEvent) {
        if (validateInput()) {
            try {
                int id = Integer.parseInt(addEmployeeID.getText());
                String employee_name = addEmployeeName.getText();
                String employee_phone = addEmployeePhone.getText();
                String employee_email = addEmployeeEmail.getText();
                String employee_address = addEmployeeAddress.getText();
                String employee_position = addEmployeePosition.getValue();
                double employee_rate = Double.parseDouble(addEmployeeRate.getText());
                String employee_assigned_job = addEmployeeAssignedJob.getValue().toString();

                AddEmployeeSQL.addEmployee(id, employee_name, employee_phone, employee_email, employee_address, employee_position, employee_rate, employee_assigned_job);
                successAlert("Employee Added Successfully");
                FXMLLoaderUtils.loadContent(contents, "/com/fibertools/main/pages/employee/employee.fxml");
            } catch (Exception e) {
                e.printStackTrace();
                errorAlert("Error Adding Employee");
            }
        } else {
            if (!validateFields()) {
                errorAlert("Please fill out all fields");
            } else {
                errorAlert("Duplicate Employee Name Number");
            }
        }

    }

    public void onClickAddEmployeeCancelButton(ActionEvent actionEvent) {
        FXMLLoaderUtils.loadContent(contents, "/com/fibertools/main/pages/employee/employee.fxml");
    }
}
