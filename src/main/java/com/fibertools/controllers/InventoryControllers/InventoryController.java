package com.fibertools.controllers.InventoryControllers;

import com.fibertools.controllers.MainController;
import com.fibertools.dao.InventorySQL;
import com.fibertools.models.Inventory;
import com.fibertools.utils.FXMLLoaderUtils;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class InventoryController {
    
    public TableView inventoryTable;

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
        FXMLLoaderUtils.loadContent(contents, "/com/fibertools/main/pages/inventory/addInventory.fxml");
    }

    public void onClickInventoryModifyButton(ActionEvent actionEvent) {
    }

    public void onClickInventoryDeleteButton(ActionEvent actionEvent) {
    }

}
