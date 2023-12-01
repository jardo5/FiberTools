package com.fibertools.controllers;

import com.fibertools.models.TaceViewerModels.Sor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class ExampleParser {
    public void parseXmlFile(String filePath) {
        try {
            File file = new File(filePath);
            JAXBContext jaxbContext = JAXBContext.newInstance(Sor.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            Sor sor = (Sor) unmarshaller.unmarshal(file);
            System.out.println("Filename: " + sor.getFilename());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
