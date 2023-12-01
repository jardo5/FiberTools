package com.fibertools.controllers;

import com.fibertools.models.TaceViewerModels.GenParams;
import com.fibertools.models.TaceViewerModels.Sor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ExampleParser {
    public void parseXmlFile(String filePath) {
        try {
            // Create a JAXB context for the Sor class
            JAXBContext jaxbContext = JAXBContext.newInstance(Sor.class);

            // Create an Unmarshaller
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            // Unmarshal the XML file
            File xmlFile = new File(filePath);
            Sor sor = (Sor) jaxbUnmarshaller.unmarshal(xmlFile);

            // Access and print general parameters of the Sor object
            System.out.println("Filename: " + sor.getFilename());
            System.out.println("Format: " + sor.getFormat());
            System.out.println("Version: " + sor.getVersion());

            // Accessing the GenParams object
            GenParams genParams = sor.getGenParams();
            if (genParams != null) {
                // Print parameters of GenParams
                System.out.println("Language: " + genParams.getLanguage());
                System.out.println("Cable ID: " + genParams.getCableID());
                System.out.println("Fiber ID: " + genParams.getFiberID());
                // ... and so on for other fields in GenParams
            }

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}

