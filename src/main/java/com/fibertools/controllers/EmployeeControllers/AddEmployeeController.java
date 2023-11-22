package com.fibertools.controllers.EmployeeControllers;

import com.fibertools.dao.JobsSQL;
import com.fibertools.models.Employees;
import com.fibertools.models.Jobs;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.ObservableList;
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
    public ComboBox<String> addEmployeePosition;
    public TextField addEmployeeName;
    public TextField addEmployeeEmail;
    public TextField addEmployeeRate;
    public ComboBox<Jobs> addEmployeeAssignedJob;

    public MFXButton addEmployeeAddButton;
    public MFXButton addEmployeeCancelButton;

    public void initialize(){
        //Sets auto increment employee id
        //addEmployeeID.setText(String.valueOf(Employees.autoEmployeeID()));

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

    public void onClickAddEmployeeAddButton(ActionEvent actionEvent) {
    }

    public void onClickAddEmployeeCancelButton(ActionEvent actionEvent) {
    }
}
