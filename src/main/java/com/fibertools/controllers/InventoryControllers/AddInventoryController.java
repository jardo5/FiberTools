package com.fibertools.controllers.InventoryControllers;


import com.fibertools.dao.addInventorySQL;
import com.fibertools.models.Jobs;
import com.fibertools.utils.FXMLLoaderUtils;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class AddInventoryController {
    @FXML
    private BorderPane contents;

    public void setContents(BorderPane contents) {
        this.contents = contents;
    }

    public TextField addInventoryID;
    public TextField addInventorySerialNumber;
    public TextField addInventoryName;
    public ComboBox addInventoryType;
    public TextField addInventoryDescription;
    public TextField addInventoryQuantity;
    public TextField addInventoryPrice;
    public ComboBox<Jobs> addInventoryAssignedJob;
    public TextField addInventoryLastUpdated;

    public MFXButton addInventoryAddButton;
    public MFXButton addInventoryCancelButton;

    public void initialize() {
        //Populated Assigned Job ComboBox with Jobs from database
        addInventoryAssignedJob.setItems(addInventorySQL.getAllJobs());

        //Populated Type ComboBox with Types
        ObservableList<String> typeList = addInventoryType.getItems();
        typeList.add("Cable");
        typeList.add("Splice Enclosure");
        typeList.add("Patch Panel");
        typeList.add("Connector");
        typeList.add("Pigtail");
        typeList.add("Hardware");
        typeList.add("Other");





    }

    public void onClickAddInventoryAddButton(ActionEvent actionEvent) {
    }

    public void onClickAddInventoryCancelButton(ActionEvent actionEvent) {
        FXMLLoaderUtils.loadContent(contents, "/com/fibertools/main/pages/inventory/inventory.fxml");
    }
}
