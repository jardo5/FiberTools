package com.fibertools.controllers.SpliceRecordControllers;

import com.fibertools.dao.SpliceRecordsSQL.FiberRecords.FiberRecordsSQL;
import com.fibertools.dao.SpliceRecordsSQL.SpliceRecords.SpliceRecordsSQL;
import com.fibertools.models.SpliceRecords;
import com.fibertools.utils.FXMLLoaderUtils;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

//TODO Add Functionality to change/delete splice records

public class SpliceRecordsController {

    // Splice Records Table
    public TableView<SpliceRecords> spliceRecordsTable;
    public TableColumn splicesTableColId;
    public TableColumn splicesTableColName;
    public TableColumn splicesTableColCustomerName;
    public TableColumn splicesTableColLocation;
    public TableColumn splicesTableColCount;
    public TableColumn splicesTableColNotes;
    public TableColumn splicesTableColAssignedJob;
    public TableColumn splicesTableColDate;
    public MFXButton spliceRecordAddSpliceButton;
    public MFXButton spliceRecordModifySpliceButton;
    public MFXButton spliceRecordDeleteSpliceButton;
    public TableView fibersRecordsTable;
    public TableColumn fibersRecordsColId;

    // Fibers Records Table
    public TableColumn fibersRecordsColNumber;
    public TableColumn fibersRecordsColDistance;
    public TableColumn fibersRecordsColSpanLoss;
    public TableColumn fibersRecordsColAvgLoss;
    public TableColumn fibersRecordsColMaxLoss;
    public TableColumn fibersRecordsColNotes;
    public MFXButton fiberRecordAddFiberButton;
    public MFXButton fiberRecordModifyFiberButton;
    public MFXButton fiberRecordDeleteFiberButton;

    @FXML
    BorderPane contents;

    public void setContents(BorderPane contents) {
        this.contents = contents;
    }

    public void initialize() {

        splicesTableColId.setCellValueFactory(new PropertyValueFactory<SpliceRecords, Integer>("spliceId"));
        splicesTableColName.setCellValueFactory(new PropertyValueFactory<SpliceRecords, String>("spliceName"));
        splicesTableColCustomerName.setCellValueFactory(new PropertyValueFactory<SpliceRecords, String>("customerName"));
        splicesTableColLocation.setCellValueFactory(new PropertyValueFactory<SpliceRecords, String>("spliceLocation"));
        splicesTableColCount.setCellValueFactory(new PropertyValueFactory<SpliceRecords, Integer>("spliceCount"));
        splicesTableColNotes.setCellValueFactory(new PropertyValueFactory<SpliceRecords, String>("spliceNotes"));
        splicesTableColAssignedJob.setCellValueFactory(new PropertyValueFactory<SpliceRecords, String>("spliceAssignedJob"));
        splicesTableColDate.setCellValueFactory(new PropertyValueFactory<SpliceRecords, String>("spliceDate"));


        spliceRecordsTable.setItems(SpliceRecordsSQL.getAllSpliceRecords());

        fibersRecordsColId.setCellValueFactory(new PropertyValueFactory<SpliceRecords, Integer>("fiberId"));
        fibersRecordsColNumber.setCellValueFactory(new PropertyValueFactory<SpliceRecords, Integer>("fiberNumber"));
        fibersRecordsColDistance.setCellValueFactory(new PropertyValueFactory<SpliceRecords, Double>("fiberDistance"));
        fibersRecordsColSpanLoss.setCellValueFactory(new PropertyValueFactory<SpliceRecords, Double>("fiberSpanLoss"));
        fibersRecordsColAvgLoss.setCellValueFactory(new PropertyValueFactory<SpliceRecords, Double>("fiberAvgLoss"));
        fibersRecordsColMaxLoss.setCellValueFactory(new PropertyValueFactory<SpliceRecords, Double>("fiberMaxLoss"));
        fibersRecordsColNotes.setCellValueFactory(new PropertyValueFactory<SpliceRecords, String>("fiberNotes"));

        // Listener for when a Splice Record is selected
        spliceRecordsTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        int spliceId = newValue.getSpliceId();
                        fibersRecordsTable.setItems(FiberRecordsSQL.getAllFibersBySpliceID(spliceId));
                    } else {
                        fibersRecordsTable.setItems(FXCollections.observableArrayList());
                    }
                }
        );


        spliceRecordsTable.setItems(SpliceRecordsSQL.getAllSpliceRecords());

    }


    public void onClickSpliceAddButton(ActionEvent actionEvent) {
        FXMLLoaderUtils.loadContent(contents, "/com/fibertools/main/pages/spliceRecords/addSpliceRecord/addSpliceRecord.fxml");
    }

    public void onClickSpliceModifyButton(ActionEvent actionEvent) {
        SpliceRecords selectedItem = spliceRecordsTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            FXMLLoaderUtils.loadSpliceModifyController(contents, "/com/fibertools/main/pages/spliceRecords/modifySpliceRecord/modifySpliceRecord.fxml", selectedItem);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Splice Record Selected");
            alert.setContentText("Please select a splice record to modify.");
            alert.showAndWait();
        }

    }

    public void onClickSpliceDeleteButton(ActionEvent actionEvent) {
        SpliceRecords selectedItem = spliceRecordsTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            SpliceRecordsSQL.removeSpliceRecord(selectedItem.getSpliceId());
            spliceRecordsTable.setItems(SpliceRecordsSQL.getAllSpliceRecords());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Splice Record Selected");
            alert.setContentText("Please select a splice record to delete.");
            alert.showAndWait();
        }
    }


}
