package com.fibertools.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
}
