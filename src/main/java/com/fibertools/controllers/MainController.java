package com.fibertools.controllers;

import com.fibertools.controllers.EmployeeControllers.AddEmployeeController;
import com.fibertools.controllers.EmployeeControllers.EmployeeController;
import com.fibertools.controllers.InventoryControllers.AddInventoryController;
import com.fibertools.controllers.InventoryControllers.InventoryController;
import com.fibertools.controllers.InventoryControllers.ModifyInventoryController;
import com.fibertools.dao.UserSQL;
import com.fibertools.models.Users;
import com.fibertools.utils.FXMLLoaderUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

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
        FXMLLoaderUtils.loadContent(contents, "/com/fibertools/main/pages/default/default.fxml");

        //TODO: Make this all its own function

        //Allows InventoryController to access contents BorderPane
        InventoryController inventoryController = new InventoryController();
        inventoryController.setContents(contents);

        //Allows AddInventoryController to access contents BorderPane
        AddInventoryController addInventoryController = new AddInventoryController();
        addInventoryController.setContents(contents);

        //Allows ModifyInventoryController to access contents BorderPane
        ModifyInventoryController modifyInventoryController = new ModifyInventoryController();
        modifyInventoryController.setContents(contents);

        //Allows EmployeeController to access contents BorderPane
        EmployeeController employeeController = new EmployeeController();
        employeeController.setContents(contents);

        //Allows AddEmployeeController to access contents BorderPane
        AddEmployeeController addEmployeeController = new AddEmployeeController();
        addEmployeeController.setContents(contents);

        //Allows SpliceRecordsController to access contents BorderPane
        SpliceRecordsController spliceRecordsController = new SpliceRecordsController();
        spliceRecordsController.setContents(contents);

        updateButtonStatus();
    }

    //Checks if user is logged in and updates button status
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
        FXMLLoaderUtils.loadContent(contents, "/com/fibertools/main/pages/traceviewer.fxml");
    }

     public void onClickReportButton(ActionEvent actionEvent) {
        FXMLLoaderUtils.loadContent(contents, "/com/fibertools/main/pages/reportcreator.fxml");
    }

    public void onClickConversionsButton(ActionEvent actionEvent) {
        FXMLLoaderUtils.loadContent(contents, "/com/fibertools/main/pages/conversions.fxml");
    }

    public void onClickCalculatorsButton(ActionEvent actionEvent) {
        FXMLLoaderUtils.loadContent(contents, "/com/fibertools/main/pages/calculators.fxml");
    }

    public void onClickSpliceRecordsButton(ActionEvent actionEvent) {
        FXMLLoaderUtils.loadContent(contents, "/com/fibertools/main/pages/spliceRecords/spliceRecords.fxml");
    }

    public void onClickInventoryButton(ActionEvent actionEvent) {
        FXMLLoaderUtils.loadContent(contents, "/com/fibertools/main/pages/inventory/inventory.fxml");
    }

    public void onClickEmployeeButton(ActionEvent actionEvent) {
        FXMLLoaderUtils.loadContent(contents, "/com/fibertools/main/pages/employee/employee.fxml");
    }

    public void onClickLoginButton(ActionEvent actionEvent) {
        try {
            if (UserSQL.isLoggedIn()) {
                Users.logoutUser();
                FXMLLoaderUtils.loadContent(contents, "/com/fibertools/main/pages/default/default.fxml");
            } else {
                FXMLLoaderUtils.loadContent(contents, "/com/fibertools/main/pages/login/login.fxml");
            }
            updateButtonStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // End of Side Menu Buttons Loaders
}