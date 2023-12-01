package com.fibertools.main;

import com.fibertools.controllers.ExampleParser;
import com.fibertools.dao.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;



import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        System.out.println(System.getenv("PATH"));

        ExampleParser exampleParser = new ExampleParser();
        exampleParser.parseXmlFile("src/main/sorData/example1-dump.xml");


        //Spacer
        System.out.println();

        JDBC.openConnection();
        launch();
        JDBC.closeConnection();

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