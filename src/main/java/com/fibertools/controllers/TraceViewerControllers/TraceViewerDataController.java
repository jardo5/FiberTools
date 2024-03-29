package com.fibertools.controllers.TraceViewerControllers;


import com.fibertools.models.TaceViewerModels.*;
import com.fibertools.utils.KeyEventsParser;
import com.fibertools.utils.MeasurementConversions;
import com.fibertools.utils.PDFReportStructure;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;
import java.util.*;

public class TraceViewerDataController implements Initializable {

    private final String fileName = "default";
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
    public NumberAxis traceChartX;
    public NumberAxis traceChartY;
    private File xmlFile;
    private File datFile;
    private Line selectedLine;
    @FXML
    private LineChart<Number, Number> traceChart;
    private XYChart.Series<Number, Number> series;

    private XYChart.Series<Number, Number> selectedLineSeries = new XYChart.Series<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Create Line Chart
        series = new XYChart.Series<>();
        traceChart.getData().add(series);

        //Improves performance of chart
        series.getNode().setStyle("-fx-stroke: black; -fx-stroke-width: 1px;");
        traceChart.setCreateSymbols(false);

        loadChartData(fileName);


        eventsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                double selectedDistance = ((KeyEvents.Event) newSelection).getDistance();
                drawLineOnTraceChart(selectedDistance);
            }
        });

        // Create a series for drawing lines
        selectedLineSeries = new XYChart.Series<>();
        traceChart.getData().add(selectedLineSeries);


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


    //Trace
    //TODO Attempt to find a more efficient way to parse the .dat file and display it as a graph
    private void loadChartData(String fileName) {
        String line;
        int nthLine = 10; // Only plot every 10th line
        int lineCount = 0;
        String datFileName = fileName.substring(0, fileName.length() - 4) + "-trace.dat";
        File datFile = Paths.get("src/main/sorData", datFileName).toFile();


        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/sorData/EXAMPLE-trace.dat"))) {
            while ((line = reader.readLine()) != null) {
                lineCount++;
                if (lineCount % nthLine != 0) continue; // Skip lines that are not the 10th line

                try {
                    String[] parts = line.split("\t| {4}"); // All .dat files x/y are split by tab or four spaces
                    if (parts.length == 2) {
                        double distanceKM = Double.parseDouble(parts[0]);
                        double distanceFT = MeasurementConversions.KMtoFT(distanceKM); // Convert KM to FT
                        double power = Double.parseDouble(parts[1]);
                        series.getData().add(new XYChart.Data<>(distanceFT, power));

                    } else {
                        System.out.println("Unexpected data format: " + line);
                    }
                } catch (Exception e) {
                    System.out.println("Error processing line: " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (datFile.exists()) {
                datFile.delete();
            }
        }
    }

    private void drawLineOnTraceChart(double distance) {
        // Clear the previously drawn lines
        selectedLineSeries.getData().clear();

        // Get the maximum Y-axis value from the existing data
        double maxY = getMaxYValueFromChartData();

        // Add the line to the series
        selectedLineSeries.getData().add(new XYChart.Data<>(distance, maxY));
        selectedLineSeries.getData().add(new XYChart.Data<>(distance, traceChartY.getLowerBound())); // Lower bound or another desired value

        // Set a custom style for the line (you can customize this further)
        selectedLineSeries.getNode().setStyle("-fx-stroke: red; -fx-stroke-width: 1.5px;");
    }

    private double getMaxYValueFromChartData() {
        double maxY = Double.MIN_VALUE;

        for (XYChart.Data<Number, Number> data : series.getData()) {
            double yValue = data.getYValue().doubleValue();
            if (yValue > maxY) {
                maxY = yValue;
            }
        }

        return maxY;
    }
    //End of Trace

    private void setTextField(TextField textField, String text) {
        text = text == null ? null : text.trim();

        if (text != null && !text.isEmpty()) {
            textField.setText(text);
            textField.setStyle("-fx-background-color: #FBFAF5;");
        } else {
            textField.setText("null");
            textField.setStyle("-fx-background-color: #d2cece;");
        }
    }

    //Summary Tab
    public void populateSummaryTable(String fileName) {
        ObservableList<KeyEvents.Summary> summaries = FXCollections.observableArrayList();
        summaries.addAll(KeyEventsParser.parseSummary(fileName));
        summaryTable.setItems(summaries);
    }
    //End of Summary Tab

    //Events Tab
    public void populateEventsTable(String fileName) {
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

    private void updateGenParamsFields(Sor sor) {
        try {
            if (sor.getGenParams() != null) {
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
        } catch (NullPointerException e) {
            System.out.println("Null pointer exception");
        }
    }

    private void updateSupParamsFields(Sor sor) {
        try {
            if (sor.getSupParams() != null) {
                setTextField(companyTextField, sor.getSupParams().getSupplier());
                setTextField(otdrModelNumberTextField, sor.getSupParams().getModelNumber());
                setTextField(otdrSerialNumberTextField, sor.getSupParams().getSerialNumber());
            }
        } catch (NullPointerException e) {
            System.out.println("Null pointer exception");
        }
    }

    private void updateFxdParamsFields(Sor sor) {
        try {
            if (sor.getFxdParams() != null) {
                setTextField(dateTimeTextField, sor.getFxdParams().getDateTime());
                setTextField(unitTextField, sor.getFxdParams().getUnit());
                setTextField(pulseWidthTextField, sor.getFxdParams().getPulseWidth());

                //Removes decimals from range and converts km to ft
                DecimalFormat df = new DecimalFormat("#");
                setTextField(rangeTextField, df.format(sor.getFxdParams().getRange() * 3280.84));

            }
        } catch (NullPointerException e) {
            System.out.println("Null pointer exception");
        }
    }
    //End of General Params Tab

    //Credits Tab
    public void openBlog(ActionEvent actionEvent) {
        openPage("https://morethanfootnotes.blogspot.com/2015/07/the-otdr-optical-time-domain.html?view=sidebar");
    }


    public void openGitHub1(ActionEvent actionEvent) {
        openPage("https://github.com/sid5432/pyOTDR");
    }

    public void openGitHub2(ActionEvent actionEvent) {
        openPage("https://github.com/JamesHarrison/otdrs");
    }

    public void openOnlineOTDR(ActionEvent actionEvent) {
        openPage("https://onlineotdr.com/");
    }

    public void openEXFO(ActionEvent actionEvent) {
        openPage("https://documents.exfo.com/Products/UserGuides/User_Guide_OTDR_English_(1068770).pdf");
    }
    //End of Credits Tab

    private void openPage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getGenParamsData() {
        Map<String, String> genParams = new HashMap<>();
        genParams.put("File Name", fileNameTextField.getText());
        genParams.put("Language", languageTextField.getText());
        genParams.put("Cable ID", cableIDTextField.getText());
        genParams.put("Fiber ID", fiberIDTextField.getText());
        genParams.put("Location A", locationATextField.getText());
        genParams.put("Location B", locationBTextField.getText());
        genParams.put("Operator", operatorTextField.getText());
        genParams.put("Comments", commentsTextField.getText());
        genParams.put("Wavelength", wavelengthTextField.getText());
        return genParams;
    }

    public Map<String, String> getSupParamsData() {
        Map<String, String> supParams = new HashMap<>();
        supParams.put("Company", companyTextField.getText());
        supParams.put("OTDR Model Number", otdrModelNumberTextField.getText());
        supParams.put("OTDR Serial Number", otdrSerialNumberTextField.getText());
        return supParams;
    }

    public Map<String, String> getFxdParamsData() {
        Map<String, String> fxdParams = new HashMap<>();
        fxdParams.put("Date/Time", dateTimeTextField.getText());
        fxdParams.put("Unit", unitTextField.getText());
        fxdParams.put("Pulse Width", pulseWidthTextField.getText());
        fxdParams.put("Range", rangeTextField.getText());
        return fxdParams;
    }

    public Map<String, String> getSummaryData() {
        Map<String, String> summaryData = new HashMap<>();
        summaryData.put("Total Loss", totalLossColumn.getCellData(0) + " dB");
        summaryData.put("ORL", orlColumn.getCellData(0) + " dB");
        summaryData.put("Loss Start", lossStartColumn.getCellData(0) + " ft");
        summaryData.put("Loss End", lossEndColumn.getCellData(0) + " ft");
        return summaryData;
    }

    public List<Map<String, String>> getEventData() {
        List<Map<String, String>> allEventData = new ArrayList<>();

        // Assuming eventsTable has items and columns are properly set up
        int numRows = eventsTable.getItems().size();
        for (int rowIndex = 0; rowIndex < numRows; rowIndex++) {
            Map<String, String> rowData = new HashMap<>();
            rowData.put("Event", eventColumn.getCellData(rowIndex).toString());
            rowData.put("Distance", distanceColumn.getCellData(rowIndex).toString());
            rowData.put("Splice Loss", spliceLossColumn.getCellData(rowIndex).toString());
            rowData.put("Reflection Loss", reflectionLossColumn.getCellData(rowIndex).toString());

            allEventData.add(rowData);
        }

        return allEventData;
    }

    public void onClickGenerateReportButton(ActionEvent actionEvent) {
        try {
            Map<String, String> genParamsData = getGenParamsData();
            Map<String, String> getSupParamsData = getSupParamsData();
            Map<String, String> getFxdParamsData = getFxdParamsData();
            Map<String, String> getSummaryData = getSummaryData();
            List<Map<String, String>> getEventData = getEventData();
            PDFReportStructure reportStructure = new PDFReportStructure();
            reportStructure.createReport("TraceViewerReportExample.pdf", genParamsData, getSupParamsData, getFxdParamsData, getSummaryData, traceChart, getEventData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}