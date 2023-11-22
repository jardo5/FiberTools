package com.fibertools.controllers.EmployeeControllers;

import com.fibertools.dao.EmployeeSQL.AddEmployeeSQL;
import com.fibertools.dao.EmployeeSQL.ModifyEmployeeSQL;
import com.fibertools.dao.JobsSQL;
import com.fibertools.models.Employees;
import com.fibertools.utils.FXMLLoaderUtils;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import static com.fibertools.controllers.EmployeeControllers.AddEmployeeController.errorAlert;

//TODO: Fix ComboBoxes that show empty when trying to save a modifying an employee

public class ModifyEmployeeController {

    public BorderPane contents;
    public TextField modifyEmployeeID;
    public TextField modifyEmployeePhone;
    public TextField modifyEmployeeAddress;
    public ComboBox modifyEmployeePosition;
    public TextField modifyEmployeeName;
    public TextField modifyEmployeeEmail;
    public TextField modifyEmployeeRate;
    public ComboBox modifyEmployeeAssignedJob;
    public MFXButton modifyEmployeeAddButton;
    public MFXButton modifyEmployeeCancelButton;

    public void setContents(BorderPane contents) {
        this.contents = contents;
    }

    public void initialize() {
        //Sets auto increment employee id
        modifyEmployeeID.setText(String.valueOf(AddEmployeeSQL.autoIncrementID()));

        //Populated Assigned Job ComboBox with Jobs from database
        modifyEmployeeAssignedJob.setItems(JobsSQL.getAllJobs());

        //Populated Position ComboBox with Positions
        ObservableList<String> typeList = modifyEmployeePosition.getItems();
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
        return !modifyEmployeeName.getText().isEmpty() &&
                !modifyEmployeePhone.getText().isEmpty() &&
                !modifyEmployeeEmail.getText().isEmpty() &&
                !modifyEmployeeAddress.getText().isEmpty() &&
                !modifyEmployeeRate.getText().isEmpty();
    }


    private boolean validateEmail() {
        String email = modifyEmployeeEmail.getText();
        if (email.contains("@") && email.contains(".")) {
            return true;
        } else {
            errorAlert("Invalid Email");
            return false;
        }
    }

    private boolean validatePhone() {
        String phone = modifyEmployeePhone.getText();
        if (phone.length() == 10) {
            return true;
        } else {
            errorAlert("Invalid Phone Number (10 digits no spaces)");
            return false;
        }
    }

    private boolean validateInput() {
        return validateFields() && validateEmail() && validatePhone();
    }


    public void onClickModifyEmployeeSaveButton(ActionEvent actionEvent) {
        if (validateInput()) {
            try {
                int id = Integer.parseInt(modifyEmployeeID.getText());
                String name = modifyEmployeeName.getText();
                String phone = modifyEmployeePhone.getText();
                String email = modifyEmployeeEmail.getText();
                String address = modifyEmployeeAddress.getText();
                String position = modifyEmployeePosition.getValue().toString();
                double rate = Double.parseDouble(modifyEmployeeRate.getText());
                String assignedJob = modifyEmployeeAssignedJob.getValue().toString();

                ModifyEmployeeSQL.addModifiedEmployee(id, name, phone, email, address, position, rate, assignedJob);
                FXMLLoaderUtils.loadContent(contents, "/com/fibertools/main/pages/employee/employee.fxml");
            } catch (Exception e) {

                e.printStackTrace();

            }
        } else {
            errorAlert("Invalid Input");
        }

    }

    public void onClickModifyEmployeeCancelButton(ActionEvent actionEvent) {
        FXMLLoaderUtils.loadContent(contents, "/com/fibertools/main/pages/employee/employee.fxml");
    }

    public void setEmployee(Employees selectedItem) {
        modifyEmployeeID.setText(String.valueOf(selectedItem.getEmployeeId()));
        modifyEmployeeName.setText(selectedItem.getEmployeeName());
        modifyEmployeePhone.setText(selectedItem.getEmployeePhone());
        modifyEmployeeEmail.setText(selectedItem.getEmployeeEmail());
        modifyEmployeeAddress.setText(selectedItem.getEmployeeAddress());
        modifyEmployeePosition.setValue(selectedItem.getEmployeePosition());
        modifyEmployeeRate.setText(String.valueOf(selectedItem.getEmployeeRate()));
        modifyEmployeeAssignedJob.setValue(selectedItem.getEmployeeAssignedJob());
    }
}
