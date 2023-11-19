package com.fibertools.controllers;

import javafx.scene.control.ComboBox;

public class DefaultController {

    public ComboBox defaultComboBox;

    public void initialize() {
        //Populate ComboBox
        defaultComboBox.getItems().addAll("Inventory", "Jobs", "Employees", "Customers", "Reports");
    }
}
