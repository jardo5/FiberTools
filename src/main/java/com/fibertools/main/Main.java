package com.fibertools.main;

import com.fibertools.dao.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

//TODO Start Working on Report Creator
//TODO Start Working on Conversions (Fiber to Color, Color to Fiber, etc.)
//TODO Prettier
//TODO Remove unused imports
//TODO Go over UI/UX
//TODO Comment out unnecessary System.out.println() statements

public class Main extends Application {
    public static void main(String[] args) {
        System.out.println(System.getenv("PATH"));

        JDBC.openConnection();
        launch();
        JDBC.closeConnection();

        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("javafx.version"));

    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("FiberTools");
        stage.setScene(scene);
        stage.show();
    }


}