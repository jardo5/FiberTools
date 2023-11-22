package com.fibertools.controllers.EmployeeControllers;

import com.fibertools.dao.EmployeeSQL.EmployeeSQL;
import com.fibertools.dao.InventorySQL.InventorySQL;
import com.fibertools.models.Employees;
import com.fibertools.utils.FXMLLoaderUtils;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class EmployeeController {

    @FXML
    private BorderPane contents;

    public void setContents(BorderPane contents) {
        this.contents = contents;
    }

    public TableView<Employees> employeeTable;
    public TableColumn employeeColID;
    public TableColumn employeeColName;
    public TableColumn employeeColPhone;
    public TableColumn employeeColEmail;
    public TableColumn employeeColAddress;
    public TableColumn employeeColPosition;
    public TableColumn employeeColRate;
    public TableColumn employeeColAssignedJob;

    public MFXButton employeeAddButton;
    public MFXButton employeeModifyButton;
    public MFXButton employeeDeleteButton;

    public void initialize() {
        employeeColID.setCellValueFactory(new PropertyValueFactory<Employees, Integer>("employeeId"));
        employeeColName.setCellValueFactory(new PropertyValueFactory<Employees, String>("employeeName"));
        employeeColPhone.setCellValueFactory(new PropertyValueFactory<Employees, String>("employeePhone"));
        employeeColEmail.setCellValueFactory(new PropertyValueFactory<Employees, String>("employeeEmail"));
        employeeColAddress.setCellValueFactory(new PropertyValueFactory<Employees, String>("employeeAddress"));
        employeeColPosition.setCellValueFactory(new PropertyValueFactory<Employees, String>("employeePosition"));
        employeeColRate.setCellValueFactory(new PropertyValueFactory<Employees, Double>("employeeRate"));
        employeeColAssignedJob.setCellValueFactory(new PropertyValueFactory<Employees, String>("employeeAssignedJob"));

        employeeTable.setItems(EmployeeSQL.getAllEmployees());


    }


    public void onClickEmployeeAddButton(ActionEvent actionEvent) {
        FXMLLoaderUtils.loadContent(contents, "/com/fibertools/main/pages/employee/addEmployee/addEmployee.fxml");
    }

    public void onClickEmployeeModifyButton(ActionEvent actionEvent) {
    }

    public void onClickEmployeeDeleteButton(ActionEvent actionEvent) {
        Employees selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Item");
            alert.setHeaderText("Are you sure you want to delete this employee?");
            alert.setContentText("Click OK to delete.");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    EmployeeSQL.removeEmployee(selectedEmployee.getEmployeeId());
                    employeeTable.setItems(EmployeeSQL.getAllEmployees());
                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Employee Selected");
            alert.setContentText("Please select an employee to delete.");
            alert.showAndWait();
        }
    }
}
