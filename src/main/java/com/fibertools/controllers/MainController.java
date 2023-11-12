package com.fibertools.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {

    @FXML
    private Button loginButton;

    @FXML
    private BorderPane contents;

    @FXML
    private void initialize() {
        loadContent("/com/fibertools/main/pages/default.fxml"); //Default Page
    }

    // Side Menu Buttons Loaders
    public void onClickTraceViewerButton(ActionEvent actionEvent) {
        loadContent("/com/fibertools/main/pages/traceviewer.fxml");
    }

    public void onClickReportButton(ActionEvent actionEvent) {
        loadContent("/com/fibertools/main/pages/report.fxml");
    }

    public void onClickConversionsButton(ActionEvent actionEvent) {
        loadContent("/com/fibertools/main/pages/conversions.fxml");
    }

    public void onClickCalculatorsButton(ActionEvent actionEvent) {
        loadContent("/com/fibertools/main/pages/calculators.fxml");
    }

    public void onClickSpliceRecordsButton(ActionEvent actionEvent) {
        loadContent("/com/fibertools/main/pages/splicerecords.fxml");
    }

    public void onClickInventoryButton(ActionEvent actionEvent) {
        loadContent("/com/fibertools/main/pages/inventory.fxml");
    }

    public void onClickSchedulingButton(ActionEvent actionEvent) {
        loadContent("/com/fibertools/main/pages/scheduling.fxml");
    }

    public void onClickLoginButton(ActionEvent actionEvent) {
        loadContent("/com/fibertools/main/pages/login.fxml");

    }

    private void loadContent(String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Node newContent = loader.load();
            contents.getChildren().clear();
            contents.getChildren().add(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}