package com.fibertools.utils;

import com.fibertools.controllers.EmployeeControllers.ModifyEmployeeController;
import com.fibertools.controllers.InventoryControllers.ModifyInventoryController;
import com.fibertools.controllers.SpliceRecordControllers.ModifySpliceRecordController;
import com.fibertools.controllers.TraceViewerControllers.TraceViewerDataController;
import com.fibertools.models.Employees;
import com.fibertools.models.Inventory;
import com.fibertools.models.SpliceRecords;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class FXMLLoaderUtils {

    public static void loadContent(BorderPane contents, String fxmlFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(FXMLLoaderUtils.class.getResource(fxmlFileName));
            Node newContent = loader.load();
            contents.getChildren().clear();
            contents.getChildren().add(newContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Open a new window but keep the current window open
    /* public static void loadViewer(String fxmlFileName, File file){
        try {
            FXMLLoader loader = new FXMLLoader(FXMLLoaderUtils.class.getResource(fxmlFileName));
            Parent root = loader.load();

            // Get the controller and pass the file
            TraceViewerDataController dataController = loader.getController();
            dataController.setFile(file); // Pass the file that was dropped or selected

            Stage stage = new Stage();
            stage.setTitle("Trace Viewer");
            stage.setScene(new Scene(root, 1200, 800));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } */


    //Loads ModifyInventoryController with selected Inventory item
    public static void loadModifyController(BorderPane contents, String fxmlFileName, Inventory selectedItem) {
        try {
            FXMLLoader loader = new FXMLLoader(FXMLLoaderUtils.class.getResource(fxmlFileName));
            Parent root = loader.load();
            ModifyInventoryController modifyInventoryController = loader.getController();
            contents.getChildren().clear();
            contents.setCenter(root);
            modifyInventoryController.setContents(contents);
            modifyInventoryController.setInventory(selectedItem);

            contents.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Loads ModifyEmployeeController with selected Employee item
    public static void loadEmployeeModifyController(BorderPane contents, String fxmlFileName, Employees selectedItem) {
        try {
            FXMLLoader loader = new FXMLLoader(FXMLLoaderUtils.class.getResource(fxmlFileName));
            Parent root = loader.load();
            ModifyEmployeeController modifyEmployeeController = loader.getController();
            contents.getChildren().clear();
            contents.setCenter(root);
            modifyEmployeeController.setContents(contents);
            modifyEmployeeController.setEmployee(selectedItem);

            contents.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Loads ModifySpliceRecordController with selected SpliceRecord item
    public static void loadSpliceModifyController(BorderPane contents, String fxmlFileName, SpliceRecords selectedItem) {
        try {
            FXMLLoader loader = new FXMLLoader(FXMLLoaderUtils.class.getResource(fxmlFileName));
            Parent root = loader.load();
            ModifySpliceRecordController modifySpliceRecordController = loader.getController();
            contents.getChildren().clear();
            contents.setCenter(root);
            modifySpliceRecordController.setContents(contents);
            modifySpliceRecordController.setSpliceRecord(selectedItem);

            contents.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
