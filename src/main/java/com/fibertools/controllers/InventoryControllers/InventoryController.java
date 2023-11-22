package com.fibertools.controllers.InventoryControllers;

import com.fibertools.dao.InventorySQL.InventorySQL;
import com.fibertools.models.Inventory;
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

public class InventoryController {
    
    public TableView<Inventory> inventoryTable;

    public TableColumn inventoryColID;
    public TableColumn inventoryColSerialNumber;
    public TableColumn inventoryColName;
    public TableColumn inventoryColType;
    public TableColumn inventoryColDescription;
    public TableColumn inventoryColQuantity;
    public TableColumn inventoryColPrice;
    public TableColumn inventoryColAssignedJob;
    public TableColumn inventoryColLastUpdated;

    public MFXButton inventoryAddButton;
    public MFXButton inventoryModifyButton;
    public MFXButton inventoryDeleteButton;

    @FXML
    private BorderPane contents;

    public void setContents(BorderPane contents) {
        this.contents = contents;
    }

    public void initialize() {
        inventoryColID.setCellValueFactory(new PropertyValueFactory<Inventory, Integer>("id"));
        inventoryColSerialNumber.setCellValueFactory(new PropertyValueFactory<Inventory, String>("serialNumber"));
        inventoryColName.setCellValueFactory(new PropertyValueFactory<Inventory, String>("name"));
        inventoryColType.setCellValueFactory(new PropertyValueFactory<Inventory, String>("type"));
        inventoryColDescription.setCellValueFactory(new PropertyValueFactory<Inventory, String>("description"));
        inventoryColQuantity.setCellValueFactory(new PropertyValueFactory<Inventory, Integer>("quantity"));
        inventoryColPrice.setCellValueFactory(new PropertyValueFactory<Inventory, Double>("price"));
        inventoryColAssignedJob.setCellValueFactory(new PropertyValueFactory<Inventory, String>("assignedJob"));
        inventoryColLastUpdated.setCellValueFactory(new PropertyValueFactory<Inventory, String>("lastUpdated"));

        //Sets sample inventory data
        inventoryTable.setItems(InventorySQL.getAllInventory());
    }

    public void onClickInventoryAddButton(ActionEvent actionEvent) {
        FXMLLoaderUtils.loadContent(contents, "/com/fibertools/main/pages/inventory/addInventory/addInventory.fxml");
    }

    public void onClickInventoryModifyButton(ActionEvent actionEvent) {
        Inventory selectedItem = inventoryTable.getSelectionModel().getSelectedItem();
        if(selectedItem != null){
            FXMLLoaderUtils.loadModifyController(contents, "/com/fibertools/main/pages/inventory/modifyInventory/modifyInventory.fxml", selectedItem);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Item Selected");
            alert.setContentText("Please select an item to modify.");
            alert.showAndWait();
        }

    }


    public void onClickInventoryDeleteButton(ActionEvent actionEvent) {
        Inventory selectedItem = inventoryTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Item");
            alert.setHeaderText("Are you sure you want to delete this item?");
            alert.setContentText("Click OK to delete.");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    InventorySQL.removeInventoryItem(selectedItem.getId());
                    inventoryTable.setItems(InventorySQL.getAllInventory());
                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Item Selected");
            alert.setContentText("Please select an item to delete.");
            alert.showAndWait();
        }
    }


}
