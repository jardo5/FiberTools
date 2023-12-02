package com.fibertools.controllers.TraceViewerControllers;


import com.fibertools.models.TaceViewerModels.FxdParams;
import com.fibertools.models.TaceViewerModels.GenParams;
import com.fibertools.models.TaceViewerModels.Sor;
import com.fibertools.models.TaceViewerModels.SupParams;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.URL;

import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class TraceViewerDataController {

    @FXML
    public GridPane identificationGrid;
    public TextField fileNameTextField;

    public TextField languageTextField; //GenParams
    public TextField cableIDTextField; // GenParams
    public TextField fiberIDTextField; // GenParams
    public TextField locationATextField; //GenParams
    public TextField locationBTextField; //GenParams
    public TextField operatorTextField; //GenParams
    public TextField commentsTextField; //GenParams
    public TextField wavelengthTextField; //GenParams

    public TextField companyTextField; //SupParams
    public TextField otdrModelNumberTextField; //SupParams
    public TextField otdrSerialNumberTextField; //SupParams

    public TextField dateTimeTextField; //FxdParams
    public TextField unitTextField; //FxdParams
    public TextField pulseWidthTextField; //FxdParams
    public TextField rangeTextField; //FxdParams


    private String fileName;



    //TODO add way to remove files after they are added
    //TODO add KeyEvents

    public void populateFieldsFromSorFile(String fileName) {
        //Reanme file to [example]-dump.xml
        String xmlFileName = fileName.substring(0, fileName.length() - 4) + "-dump.xml";
        File xmlFile = Paths.get("src/main/sorData", xmlFileName).toFile();
        System.out.println("XML file path: " + xmlFile.getAbsolutePath());

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Sor.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Sor sor = (Sor) jaxbUnmarshaller.unmarshal(xmlFile);
            GenParams genParams = sor.getGenParams();
            FxdParams fxdParams = sor.getFxdParams();
            SupParams supParams = sor.getSupParams();

            //Populate fields
            updateGenParamsFields(sor);
            updateFxdParamsFields(sor);
            updateSupParamsFields(sor);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void updateGenParamsFields(Sor sor){
        try {
            if(sor.getGenParams() != null){
                setTextField(fileNameTextField, sor.getFilename());
                setTextField(languageTextField, sor.getGenParams().getLanguage());
                setTextField(cableIDTextField, sor.getGenParams().getCableID());
                setTextField(fiberIDTextField, sor.getGenParams().getFiberID());
                setTextField(locationATextField, sor.getGenParams().getLocationA());
                setTextField(locationBTextField, sor.getGenParams().getLocationB());
                setTextField(operatorTextField, sor.getGenParams().getOperator());
                setTextField(commentsTextField, sor.getGenParams().getComments());
                setTextField(wavelengthTextField, sor.getGenParams().getWavelength());
            }
        } catch (NullPointerException e){
            System.out.println("Null pointer exception");
        }
    }

    private void updateSupParamsFields(Sor sor){
        try {
            if(sor.getSupParams() != null){
                setTextField(companyTextField, sor.getSupParams().getSupplier());
                setTextField(otdrModelNumberTextField, sor.getSupParams().getModelNumber());
                setTextField(otdrSerialNumberTextField, sor.getSupParams().getSerialNumber());
            }
        } catch (NullPointerException e){
            System.out.println("Null pointer exception");
        }
    }

    private void updateFxdParamsFields(Sor sor){
        try {
            if(sor.getFxdParams() != null){
                setTextField(dateTimeTextField, sor.getFxdParams().getDateTime());
                setTextField(unitTextField, sor.getFxdParams().getUnit());
                setTextField(pulseWidthTextField, sor.getFxdParams().getPulseWidth());

                //TODO make this simplify the number for example 131260.36203530076ft to 131260ft
                DecimalFormat df = new DecimalFormat("#");
                setTextField(rangeTextField, df.format(sor.getFxdParams().getRange() * 3280.84));

            }
        }catch (NullPointerException e){
            System.out.println("Null pointer exception");
        }
    }

    private void setTextField(TextField textField, String text){
        text = text == null ? null : text.trim();

        if(text != null && !text.isEmpty()){
            textField.setText(text);
            textField.setStyle("-fx-background-color: #FBFAF5;");
        } else {
            textField.setText("null");
            textField.setStyle("-fx-background-color: #d2cece;");
        }
    }

}