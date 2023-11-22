package com.fibertools.controllers;

import com.fibertools.dao.SpliceRecordsSQL.SpliceRecords.SpliceRecordsSQL;
import com.fibertools.models.SpliceRecords;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class SpliceRecordsController {

    @FXML
    BorderPane contents;

    public void setContents(BorderPane contents) {
        this.contents = contents;
    }

    // Splice Records Table
    public TableView spliceRecordsTable;
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

    // Fibers Records Table

    public TableView fibersRecordsTable;
    public TableColumn fibersRecordsColId;
    public TableColumn fibersRecordsColDistance;
    public TableColumn fibersRecordsColSpanLoss;
    public TableColumn fibersRecordsColAvgLoss;
    public TableColumn fibersRecordsColMaxLoss;
    public TableColumn fibersRecordsColNotes;

    public MFXButton fiberRecordAddFiberButton;
    public MFXButton fiberRecordModifyFiberButton;
    public MFXButton fiberRecordDeleteFiberButton;

    public void initialize(){

        splicesTableColId.setCellValueFactory(new PropertyValueFactory<SpliceRecords, Integer>("spliceId"));
        splicesTableColName.setCellValueFactory(new PropertyValueFactory<SpliceRecords, String>("spliceName"));
        splicesTableColCustomerName.setCellValueFactory(new PropertyValueFactory<SpliceRecords, String>("customerName"));
        splicesTableColLocation.setCellValueFactory(new PropertyValueFactory<SpliceRecords, String>("spliceLocation"));
        splicesTableColCount.setCellValueFactory(new PropertyValueFactory<SpliceRecords, Integer>("spliceCount"));
        splicesTableColNotes.setCellValueFactory(new PropertyValueFactory<SpliceRecords, String>("spliceNotes"));
        splicesTableColAssignedJob.setCellValueFactory(new PropertyValueFactory<SpliceRecords, String>("spliceAssignedJob"));
        splicesTableColDate.setCellValueFactory(new PropertyValueFactory<SpliceRecords, String>("spliceDate"));


        spliceRecordsTable.setItems(SpliceRecordsSQL.getAllSpliceRecords());
    }



}
