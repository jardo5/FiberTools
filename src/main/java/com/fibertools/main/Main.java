package com.fibertools.main;

import com.fibertools.dao.JDBC;
import com.fibertools.models.TaceViewerModels.GenParams;
import com.fibertools.models.TaceViewerModels.KeyEvents;
import com.fibertools.models.TaceViewerModels.Sor;
import com.fibertools.utils.KeyEventsParser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;



import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        System.out.println(System.getenv("PATH"));

        //TODO REMOVE THIS
        String fileName = "src/main/sorData/XMLTRANSLATION.xml";
        KeyEventsParser.parseAllKeyEvents(fileName);
        KeyEventsParser.parseSummary(fileName);


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