package com.fibertools.controllers.EmployeeControllers;

import com.fibertools.models.Employees;
import com.fibertools.models.Jobs;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AddEmployeeController {

    @FXML
    public BorderPane contents;

    public void setContents(BorderPane contents) {
        this.contents = contents;
    }


    public TextField addEmployeeID;
    public TextField addEmployeePhone;
    public TextField addEmployeeAddress;
    public ComboBox<Employees> addEmployeePosition;
    public TextField addEmployeeName;
    public TextField addEmployeeEmail;
    public TextField addEmployeeRate;
    public ComboBox<Jobs> addEmployeeAssignedJob;

    public MFXButton addEmployeeAddButton;
    public MFXButton addEmployeeCancelButton;

    public void onClickAddEmployeeAddButton(ActionEvent actionEvent) {
    }

    public void onClickAddEmployeeCancelButton(ActionEvent actionEvent) {
    }
}
