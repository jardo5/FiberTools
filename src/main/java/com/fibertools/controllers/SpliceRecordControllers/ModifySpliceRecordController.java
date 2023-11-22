package com.fibertools.controllers.SpliceRecordControllers;

import com.fibertools.dao.InventorySQL.AddInventorySQL;
import com.fibertools.dao.JobsSQL;
import com.fibertools.dao.SpliceRecordsSQL.SpliceRecords.AddSpliceRecordSQL;
import com.fibertools.dao.SpliceRecordsSQL.SpliceRecords.ModifySpliceRecordsSQL;
import com.fibertools.models.Jobs;
import com.fibertools.models.SpliceRecords;
import com.fibertools.utils.FXMLLoaderUtils;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ModifySpliceRecordController {

    public BorderPane contents;
    public void setContents(BorderPane contents) {
        this.contents = contents;
    }

    @FXML
    public TextField modifySpliceRecordID;
    public TextField modifySpliceRecordCustomerName;
    public TextField modifySpliceRecordCount;
    public ComboBox modifySpliceRecordAssignedJob;
    public TextField modifySpliceRecordName;
    public TextField modifySpliceRecordLocation;
    public TextField modifySpliceRecordNotes;
    public TextField modifySpliceRecordDate;

    public MFXButton modifySpliceRecordAddButton;
    public MFXButton modifySpliceRecordCancelButton;

    public void initialize(){

        modifySpliceRecordID.setText(String.valueOf(AddInventorySQL.autoInventoryID()));

        modifySpliceRecordAssignedJob.setItems(JobsSQL.getAllJobs());


    }

    private boolean validateFields(){
        return !modifySpliceRecordName.getText().isEmpty() &&
                !modifySpliceRecordCustomerName.getText().isEmpty() &&
                !modifySpliceRecordCount.getText().isEmpty() &&
                !modifySpliceRecordLocation.getText().isEmpty() &&
                !modifySpliceRecordNotes.getText().isEmpty() &&
                !modifySpliceRecordDate.getText().isEmpty() &&
                modifySpliceRecordAssignedJob.getValue() != null;
    }

    private boolean validateDate() {
        String dateText = modifySpliceRecordDate.getText();

        try {
            LocalDate.parse(dateText);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private boolean validateInput(){
        return validateFields() && validateDate();
    }



    public void onClickModifySpliceAddButton(ActionEvent actionEvent) {
        if(validateInput()){
            try {
                int id = Integer.parseInt(modifySpliceRecordID.getText());
                String name = modifySpliceRecordName.getText();
                String customerName = modifySpliceRecordCustomerName.getText();
                String location = modifySpliceRecordLocation.getText();
                int count = Integer.parseInt(modifySpliceRecordCount.getText());
                String notes = modifySpliceRecordNotes.getText();
                String assignedJob = modifySpliceRecordAssignedJob.getValue().toString();
                String date = modifySpliceRecordDate.getText();

                ModifySpliceRecordsSQL.modifySpliceRecord(id, name, customerName, location, count, notes, assignedJob, date);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Splice Record Modified", ButtonType.OK);
                alert.showAndWait();
                FXMLLoaderUtils.loadContent(contents, "/com/fibertools/main/pages/spliceRecords/spliceRecords.fxml");
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Input", ButtonType.OK);
                alert.showAndWait();
            }
        }
    }

    public void onClickModifySpliceCancelButton(ActionEvent actionEvent) {
        FXMLLoaderUtils.loadContent(contents, "/com/fibertools/main/pages/spliceRecords/spliceRecords.fxml");
    }

    public void setSpliceRecord(SpliceRecords selectedItem) {
        modifySpliceRecordID.setText(String.valueOf(selectedItem.getSpliceId()));
        modifySpliceRecordName.setText(selectedItem.getSpliceName());
        modifySpliceRecordCustomerName.setText(selectedItem.getCustomerName());
        modifySpliceRecordCount.setText(String.valueOf(selectedItem.getSpliceCount()));
        modifySpliceRecordLocation.setText(selectedItem.getSpliceLocation());
        modifySpliceRecordNotes.setText(selectedItem.getSpliceNotes());
        modifySpliceRecordDate.setText(selectedItem.getSpliceDate().toString());
        modifySpliceRecordAssignedJob.setValue(selectedItem.getSpliceAssignedJob());
    }

}
