package com.fibertools.main;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.fibertools.dao.JDBC;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("FiberTools");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        try {
            JDBC.openConnection();
            launch();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC.closeConnection();
        }
    }
}