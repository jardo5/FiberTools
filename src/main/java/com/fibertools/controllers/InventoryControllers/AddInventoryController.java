package com.fibertools.controllers.InventoryControllers;


import com.fibertools.dao.InventorySQL.AddInventorySQL;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class AddInventoryController {
    public TextField addInventoryID;
    public TextField addInventorySerialNumber;
    public TextField addInventoryName;
    public ComboBox<String> addInventoryType;
    public TextField addInventoryDescription;
    public TextField addInventoryQuantity;
    public TextField addInventoryPrice;
    public ComboBox<Jobs> addInventoryAssignedJob;
    public TextField addInventoryLastUpdated;
    public MFXButton addNewAssignedJobButton;
    public MFXButton addInventoryAddButton;
    public MFXButton addInventoryCancelButton;
    @FXML
    private BorderPane contents;
    private LocalDateTime currentDateTime;

    public static void errorAlert(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void successAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Inventory Item Added");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setContents(BorderPane contents) {
        this.contents = contents;
    }

    public void initialize() {

        //Sets auto increment inventory id
        addInventoryID.setText(String.valueOf(AddInventorySQL.autoInventoryID()));

        //Populated Assigned Job ComboBox with Jobs from database
        addInventoryAssignedJob.setItems(JobsSQL.getAllJobs());

        //Populated Type ComboBox with Types
        ObservableList<String> typeList = addInventoryType.getItems();
        typeList.add("Cable");
        typeList.add("Splice Enclosure");
        typeList.add("Patch Panel");
        typeList.add("Connector");
        typeList.add("Pigtail");
        typeList.add("Hardware");
        typeList.add("Other");

        currentDateTime = LocalDateTime.now(ZoneId.of("America/New_York"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
        String formattedDateTime = currentDateTime.format(formatter);
        addInventoryLastUpdated.setText(formattedDateTime);

    }

    private boolean validateFields() {
        return !addInventoryID.getText().isEmpty() &&
                !addInventorySerialNumber.getText().isEmpty() &&
                !addInventoryName.getText().isEmpty() &&
                addInventoryType.getValue() != null &&
                !addInventoryDescription.getText().isEmpty() &&
                !addInventoryQuantity.getText().isEmpty() &&
                !addInventoryPrice.getText().isEmpty() &&
                addInventoryAssignedJob.getValue() != null;
    }

    private boolean validateInput() {
        return validateFields() && !AddInventorySQL.duplicateSerialNumber(addInventorySerialNumber.getText());
    }

    public void onClickAddInventoryAddButton(ActionEvent actionEvent) {
        if (validateInput()) {
            try {
                int id = Integer.parseInt(addInventoryID.getText());
                String serialNumber = addInventorySerialNumber.getText();
                String name = addInventoryName.getText();
                String type = addInventoryType.getValue();
                String description = addInventoryDescription.getText();
                int quantity = Integer.parseInt(addInventoryQuantity.getText());
                double price = Double.parseDouble(addInventoryPrice.getText());
                String assignedJob = addInventoryAssignedJob.getValue().toString();
                String lastUpdated = Timestamp.valueOf(currentDateTime).toString();

                AddInventorySQL.addInventoryItem(id, serialNumber, name, type, description, quantity, price, assignedJob, Timestamp.valueOf(lastUpdated));
                successAlert("Inventory Added");
            } catch (Exception e) {
                e.printStackTrace();
                errorAlert("Error Adding Inventory", "Error Adding Inventory");
            }
        } else {
            if (!validateFields()) {
                errorAlert("Please fill out all fields", "Empty Fields");
            } else {
                errorAlert("Duplicate Serial Number", "Duplicate Serial Number");
            }
        }
    }

    public void onClickAddInventoryCancelButton(ActionEvent actionEvent) {
        FXMLLoaderUtils.loadContent(contents, "/com/fibertools/main/pages/inventory/inventory.fxml");
    }

    @FXML
    public void onClickAddNewAssignedJobButton(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("New Assigned Job");
        dialog.setHeaderText("Enter the name of the new job:");
        dialog.setContentText("Job Name:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(JobsSQL::addNewAssignedJob);
        addInventoryAssignedJob.setItems(JobsSQL.getAllJobs());
    }
}
