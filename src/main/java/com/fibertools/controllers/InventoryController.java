package com.fibertools.controllers;

import com.fibertools.dao.InventorySQL;
import com.fibertools.models.Inventory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
}
