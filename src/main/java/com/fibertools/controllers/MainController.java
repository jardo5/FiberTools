package com.fibertools.controllers;

import com.fibertools.dao.UserSQL;
import com.fibertools.models.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {

    public Button traceViewerButton;
    public Button reportCreatorButton;
    public Button conversionsButton;
    public Button calculatorsButton;
    public Button spliceRecordsButton;
    public Button inventoryButton;
    public Button schedulingButton;
    public Button loginButton;


    @FXML
    private BorderPane contents;

    @FXML
    private void initialize() {
        loadContent("/com/fibertools/main/pages/default.fxml"); // Default Page
        updateButtonStatus();
    }

    private void updateButtonStatus() {
        Button[] buttons = {
                spliceRecordsButton,
                inventoryButton,
                schedulingButton,
        };
        try {
            if (UserSQL.isLoggedIn()) {
                for (Button button : buttons) {
                    button.setDisable(false);
                }
                loginButton.setText("Logout");
            } else {
                for (Button button : buttons) {
                    button.setDisable(true);
                }
                loginButton.setText("Login");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        try {
            if (UserSQL.isLoggedIn()) {
                Users.logoutUser();
                loadContent("/com/fibertools/main/pages/default.fxml");
            } else {
                loadContent("/com/fibertools/main/pages/login/login.fxml");
            }
            updateButtonStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // End of Side Menu Buttons Loaders

    //Loads the content of the page by taking the fxml file name as a parameter
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