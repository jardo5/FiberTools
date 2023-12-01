package com.fibertools.controllers.TraceViewerControllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;


import java.net.URL;

import java.util.ResourceBundle;

public class TraceViewerDataController implements Initializable {

    @FXML
    public GridPane identificationGrid;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /* public void setFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(Sor.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Using InputStreamReader to specify UTF-8 encoding explicitly
            try (InputStream inputStream = new FileInputStream(file);
                 Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {

                Sor sor = (Sor) unmarshaller.unmarshal(reader);
                // Now sor object contains all the parsed XML data

                // Example: Printing the Fiber ID
                if (sor.getGenParams() != null) {
                    System.out.println("Fiber ID: " + sor.getGenParams().getFiberId());
                } else {
                    System.out.println("Fiber ID not found in the XML.");
                }

                // Add more logic here to process other parts of the XML as needed
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } */
}