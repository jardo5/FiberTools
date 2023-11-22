package com.fibertools.controllers.InventoryControllers;

import com.fibertools.dao.InventorySQL.AddInventorySQL;
import com.fibertools.dao.InventorySQL.ModifyInventorySQL;
import com.fibertools.models.Inventory;
import com.fibertools.utils.FXMLLoaderUtils;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static com.fibertools.controllers.InventoryControllers.AddInventoryController.errorAlert;
import static com.fibertools.controllers.InventoryControllers.AddInventoryController.successAlert;

public class ModifyInventoryController {

    @FXML
    private BorderPane contents;


    public void setContents(BorderPane contents) {
        this.contents = contents;
    }

    @FXML
    public TextField modifyInventoryID;
    @FXML
    public TextField modifyInventorySerialNumber;
    public TextField modifyInventoryName;
    public ComboBox<String> modifyInventoryType;
    public TextField modifyInventoryDescription;
    public TextField modifyInventoryQuantity;
    public TextField modifyInventoryPrice;
    public ComboBox modifyInventoryAssignedJob;
    public TextField modifyInventoryLastUpdated;

    public MFXButton modifyInventoryAddButton;
    public MFXButton modifyInventoryCancelButton;

    private LocalDateTime currentDateTime;

    public void initialize(){

        //Sets auto increment inventory id
        modifyInventoryID.setText(String.valueOf(AddInventorySQL.autoInventoryID()));

        //Populated Assigned Job ComboBox with Jobs from database
        modifyInventoryAssignedJob.setItems(AddInventorySQL.getAllJobs());

        //Populated Type ComboBox with Types
        ObservableList<String> typeList = modifyInventoryType.getItems();
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
        modifyInventoryLastUpdated.setText(formattedDateTime);
    }

    private boolean validateFields() {
        return !modifyInventoryID.getText().isEmpty() &&
                !modifyInventorySerialNumber.getText().isEmpty() &&
                !modifyInventoryName.getText().isEmpty() &&
                modifyInventoryType.getValue() != null &&
                !modifyInventoryDescription.getText().isEmpty() &&
                !modifyInventoryQuantity.getText().isEmpty() &&
                !modifyInventoryPrice.getText().isEmpty() &&
                modifyInventoryAssignedJob.getValue() != null;
    }

    //Duplicate Serial Number Check not necessary for modify inventory (disabled)
    public void onClickModifyInventoryAddButton(ActionEvent actionEvent) {
        if (validateFields()) {
            try {
                int id = Integer.parseInt(modifyInventoryID.getText());
                String serialNumber = modifyInventorySerialNumber.getText();
                String name = modifyInventoryName.getText();
                String type = modifyInventoryType.getValue();
                String description = modifyInventoryDescription.getText();
                int quantity = Integer.parseInt(modifyInventoryQuantity.getText());
                double price = Double.parseDouble(modifyInventoryPrice.getText());
                String assignedJob = modifyInventoryAssignedJob.getValue().toString();
                String lastUpdated = Timestamp.valueOf(currentDateTime).toString();

                ModifyInventorySQL.modifyInventoryItem(id, serialNumber, name, type, description, quantity, price, assignedJob, Timestamp.valueOf(lastUpdated));
                successAlert("Inventory Item Modified Successfully");
                FXMLLoaderUtils.loadContent(contents, "/com/fibertools/main/pages/inventory/inventory.fxml");
            } catch (Exception e) {
                e.printStackTrace();
                errorAlert("Error Adding Inventory Item. Check All Inputs", "Error Adding Inventory Item");
            }
        } else {
            if (!validateFields()) {
                errorAlert("Please fill out all fields", "Empty Fields");
            }
        }
    }

    public void onClickModifyInventoryCancelButton(ActionEvent actionEvent) {
        FXMLLoaderUtils.loadContent(contents, "/com/fibertools/main/pages/inventory/inventory.fxml");
    }

    public void setInventory(Inventory selectedItem) {
        modifyInventoryID.setText(String.valueOf(selectedItem.getId()));
        modifyInventorySerialNumber.setText(selectedItem.getSerialNumber());
        modifyInventoryName.setText(selectedItem.getName());
        modifyInventoryType.setValue(selectedItem.getType());
        modifyInventoryDescription.setText(selectedItem.getDescription());
        modifyInventoryQuantity.setText(String.valueOf(selectedItem.getQuantity()));
        modifyInventoryPrice.setText(String.valueOf(selectedItem.getPrice()));
        modifyInventoryAssignedJob.setValue(selectedItem.getAssignedJob());
        modifyInventoryLastUpdated.setText(selectedItem.getLastUpdated());
    }

}
