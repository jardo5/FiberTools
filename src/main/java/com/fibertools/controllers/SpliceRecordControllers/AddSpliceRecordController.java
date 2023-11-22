package com.fibertools.controllers.SpliceRecordControllers;

import com.fibertools.dao.JobsSQL;
import com.fibertools.dao.SpliceRecordsSQL.SpliceRecords.AddSpliceRecordSQL;
import com.fibertools.models.Jobs;
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

public class AddSpliceRecordController {

    @FXML
    public BorderPane contents;
    public TextField addSpliceRecordID;
    public TextField addSpliceRecordName;
    public TextField addSpliceRecordCustomerName;
    public TextField addSpliceRecordCount;
    public TextField addSpliceRecordLocation;
    public TextField addSpliceRecordNotes;
    public ComboBox<Jobs> addSpliceRecordAssignedJob;
    public TextField addSpliceRecordDate;
    public MFXButton addSpliceRecordAddButton;
    public MFXButton addSpliceRecordCancelButton;

    public void setContents(BorderPane contents) {
        this.contents = contents;
    }

    public void initialize() {

        addSpliceRecordID.setText(String.valueOf(AddSpliceRecordSQL.autoSpliceID()));

        addSpliceRecordAssignedJob.setItems(JobsSQL.getAllJobs());
    }

    private boolean validateFields() {
        return !addSpliceRecordName.getText().isEmpty() &&
                !addSpliceRecordCustomerName.getText().isEmpty() &&
                !addSpliceRecordCount.getText().isEmpty() &&
                !addSpliceRecordLocation.getText().isEmpty() &&
                !addSpliceRecordNotes.getText().isEmpty() &&
                !addSpliceRecordDate.getText().isEmpty() &&
                addSpliceRecordAssignedJob.getValue() != null;
    }

    private boolean validateDate() {
        String dateText = addSpliceRecordDate.getText();

        try {
            LocalDate.parse(dateText);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    public void onClickAddSpliceAddButton(ActionEvent actionEvent) {
        if (validateFields()) {
            if (validateDate()) {
                AddSpliceRecordSQL.addSpliceRecord(
                        Integer.parseInt(addSpliceRecordID.getText()),
                        addSpliceRecordName.getText(),
                        addSpliceRecordCustomerName.getText(),
                        addSpliceRecordLocation.getText(),
                        Integer.parseInt(addSpliceRecordCount.getText()),
                        addSpliceRecordNotes.getText(),
                        addSpliceRecordAssignedJob.getValue().getJobName(),
                        addSpliceRecordDate.getText()
                );
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Splice Record Added Successfully!", ButtonType.OK);
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    alert.close();
                    FXMLLoaderUtils.loadContent(contents, "/com/fibertools/main/pages/spliceRecords/spliceRecords.fxml");
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Date Format! Please use YYYY-MM-DD", ButtonType.OK);
                alert.showAndWait();
            }
        }
    }


    public void onClickAddSpliceCancelButton(ActionEvent actionEvent) {
        FXMLLoaderUtils.loadContent(contents, "/com/fibertools/main/pages/spliceRecords/spliceRecords.fxml");
    }
}
