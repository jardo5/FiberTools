package com.fibertools.controllers;

import com.fibertools.models.TaceViewerModels.FxdParams;
import com.fibertools.models.TaceViewerModels.GenParams;
import com.fibertools.models.TaceViewerModels.Sor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

// TODO DELETE THIS CLASS AFTER TESTING IS COMPLETE

public class ExampleParser {
    /*
    public void parseXmlFile(String filePath) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Sor.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            File xmlFile = new File(filePath);
            Sor sor = (Sor) jaxbUnmarshaller.unmarshal(xmlFile);

            // Print the Sor object
            System.out.println("Filename: " + sor.getFilename());
            System.out.println("Format: " + sor.getFormat());
            System.out.println("Version: " + sor.getVersion());


            GenParams genParams = sor.getGenParams();
            if (genParams != null) {
                // Print parameters of GenParams
                System.out.println("Language: " + genParams.getLanguage());
                System.out.println("Cable ID: " + genParams.getCableID());
                System.out.println("Fiber ID: " + genParams.getFiberID());
            }

            FxdParams fxdParams = sor.getFxdParams();
            if (fxdParams != null) {
                System.out.println("Unit: " + fxdParams.getUnit());
                System.out.println("Wavelength: " + fxdParams.getWavelength());
            }

            //Print new line for readability
            System.out.println();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    } */
}


