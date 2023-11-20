package com.fibertools.utils;

import com.fibertools.controllers.InventoryControllers.ModifyInventoryController;
import com.fibertools.models.Inventory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;

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

    //Loads ModifyInventoryController with selected Inventory item
    public static void loadModifyController(BorderPane contents, String fxmlFileName, Inventory selectedItem){
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

}
