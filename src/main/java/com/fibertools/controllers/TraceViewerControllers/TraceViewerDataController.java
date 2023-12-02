package com.fibertools.controllers.TraceViewerControllers;


import com.fibertools.models.TaceViewerModels.*;
import com.fibertools.utils.KeyEventsParser;
import com.fibertools.utils.MeasurementConversions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.net.URL;

import java.nio.file.Paths;
import java.security.Key;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class TraceViewerDataController implements Initializable {

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

    public TableView eventsTable; //KeyEvents
    public TableColumn eventColumn; //KeyEvents
    public TableColumn typeColumn; //KeyEvents
    public TableColumn distanceColumn; //KeyEvents
    public TableColumn slopeColumn; //KeyEvents
    public TableColumn spliceLossColumn; //KeyEvents
    public TableColumn reflectionLossColumn; //KeyEvents
    public TableColumn commentsColumn; //KeyEvents
    public TableColumn endOfPrevColumn; //KeyEvents
    public TableColumn startOfCurrColumn; //KeyEvents
    public TableColumn endOfCurrColumn; //KeyEvents
    public TableColumn startOfNextColumn; //KeyEvents
    public TableColumn peakColumn; //KeyEvents
    
    public TableView summaryTable; //KeyEvents, Summary
    public TableColumn totalLossColumn; //KeyEvents, Summary
    public TableColumn orlColumn; //KeyEvents, Summary
    public TableColumn lossStartColumn; //KeyEvents, Summary
    public TableColumn lossEndColumn; //KeyEvents, Summary
    public TableColumn orlStartColumn; //KeyEvents, Summary
    public TableColumn orlEndColumn; //KeyEvents, Summary


    private String fileName;
    private File xmlFile;


    //TODO add way to remove files after they are added
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Events Table
        eventColumn.setCellValueFactory(new PropertyValueFactory<>("index"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        distanceColumn.setCellValueFactory(new PropertyValueFactory<>("distance"));
        slopeColumn.setCellValueFactory(new PropertyValueFactory<>("slope"));
        spliceLossColumn.setCellValueFactory(new PropertyValueFactory<>("spliceLoss"));
        reflectionLossColumn.setCellValueFactory(new PropertyValueFactory<>("reflLoss"));
        commentsColumn.setCellValueFactory(new PropertyValueFactory<>("comments"));
        endOfPrevColumn.setCellValueFactory(new PropertyValueFactory<>("endOfPrev"));
        startOfCurrColumn.setCellValueFactory(new PropertyValueFactory<>("startOfCurr"));
        endOfCurrColumn.setCellValueFactory(new PropertyValueFactory<>("endOfCurr"));
        startOfNextColumn.setCellValueFactory(new PropertyValueFactory<>("startOfNext"));
        peakColumn.setCellValueFactory(new PropertyValueFactory<>("peak"));
        //End of Events Table
        //Summary Table
        totalLossColumn.setCellValueFactory(new PropertyValueFactory<>("totalLoss"));
        orlColumn.setCellValueFactory(new PropertyValueFactory<>("orl"));  // Match the property name
        lossStartColumn.setCellValueFactory(new PropertyValueFactory<>("lossStart"));
        lossEndColumn.setCellValueFactory(new PropertyValueFactory<>("lossEnd"));
        orlStartColumn.setCellValueFactory(new PropertyValueFactory<>("orlStart"));  // Match the property name
        orlEndColumn.setCellValueFactory(new PropertyValueFactory<>("orlEnd"));  // Match the property name
        //End of Summary Table
    }

    //Summary Tab
    public void populateSummaryTable(String fileName){
        ObservableList<KeyEvents.Summary> summaries = FXCollections.observableArrayList();
        summaries.addAll(KeyEventsParser.parseSummary(fileName));
        summaryTable.setItems(summaries);
    }

    //Events Tab
    public void populateEventsTable(String fileName){
        ObservableList<KeyEvents.Event> events = FXCollections.observableArrayList();
        events.addAll(KeyEventsParser.parseAllKeyEvents(fileName));
        eventsTable.setItems(events);
    }
    //End of Events Tab

    // Start of General Params Tab
    public void populateFieldsFromSorFile(String fileName) {
        //Rename file to [example]-dump.xml
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
            populateEventsTable(String.valueOf(xmlFile));
            populateSummaryTable(String.valueOf(xmlFile));


            //Delete xml file after it is read
            xmlFile.delete();
            //Delete .dat file with [example]-trace.dat
            String datFileName = fileName.substring(0, fileName.length() - 4) + "-trace.dat";
            File datFile = Paths.get("src/main/sorData", datFileName).toFile();
            datFile.delete();
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

                //Removes decimals from range and converts km to ft
                DecimalFormat df = new DecimalFormat("#");
                setTextField(rangeTextField, df.format(sor.getFxdParams().getRange() * 3280.84));

            }
        }catch (NullPointerException e){
            System.out.println("Null pointer exception");
        }
    }
    //End of General Params Tab

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