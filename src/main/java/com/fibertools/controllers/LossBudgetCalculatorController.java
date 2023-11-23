package com.fibertools.controllers;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import java.io.IOException;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static com.fibertools.controllers.EmployeeControllers.AddEmployeeController.errorAlert;

public class LossBudgetCalculatorController {
    public BorderPane content;

    public void setContents(BorderPane contents) {
        this.content = contents;
    }


    public ComboBox lossBudgetTypeComboBox;
    public ComboBox lossBudgetMeasurementComboBox;

    public TextField lossBudgetCableLength;
    public TextField lossBudgetConnectors;
    public TextField lossBudgetSplices;
    public TextField lossBudgetTotalLoss;
    public MFXButton lossBudgetCalculateButton;

    public MFXButton lossBudgetCredits;

    public void initialize() {
        lossBudgetTypeComboBox.getItems().addAll(
                "Single Mode 9um 1310nm",
                "Single Mode 9um 1550nm",
                "Multi Mode 50-125um 850nm",
                "Multi Mode 50-125um 1300nm",
                "Multi Mode 62.5-125um 850nm",
                "Multi Mode 62.5-125um 1300nm");



        lossBudgetMeasurementComboBox.getItems().addAll(
                "Miles",
                "Kilometers",
                "Feet",
                "Meters"
        );
    }

    //STANDARD LOSSES ACCORDING TO ANSI/TIA/EIA-568-C.3 and ISO/IEC 11801:2002
    //1 Splice is 0.3 dB
    //1 Connector is 0.75 dB

    //Multi Mode 50-125um 850nm is 3.5 dB/km
    //Multi Mode 50-125um 1300nm is 1.5 dB/km
    //Multi Mode 62.5-125um 850nm is 3.5 dB/km
    //Multi Mode 62.5-125um 1300nm is 1.5 dB/km

    //Single Mode 9um 1310nm is 1 dB/km
    //Single Mode 9um 1550nm is 1 dB/km

    public void onClickLossBudgetCalculateButton(ActionEvent actionEvent) {
        String lossBudgetType = lossBudgetTypeComboBox.getValue().toString();
        String lossBudgetMeasurement = lossBudgetMeasurementComboBox.getValue().toString();
        String lossBudgetCableLengthText = lossBudgetCableLength.getText();
        String lossBudgetConnectorsText = lossBudgetConnectors.getText();
        String lossBudgetSplicesText = lossBudgetSplices.getText();

        try {
            double cableLength = Double.parseDouble(lossBudgetCableLengthText);
            int connectors = Integer.parseInt(lossBudgetConnectorsText);
            int splices = Integer.parseInt(lossBudgetSplicesText);

            double connectorLoss = 0.75; // dB
            double spliceLoss = 0.3; // dB

            double cableLossPerKm;
            switch (lossBudgetType) {
                case "Multi Mode 50-125um 850nm":
                case "Multi Mode 62.5-125um 850nm":
                    cableLossPerKm = 3.5;
                    break;
                case "Multi Mode 50-125um 1300nm":
                case "Multi Mode 62.5-125um 1300nm":
                    cableLossPerKm = 1.5;
                    break;
                case "Single Mode 9um 1310nm":
                case "Single Mode 9um 1550nm":
                    cableLossPerKm = 1.0;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown cable type");
            }

            switch (lossBudgetMeasurement) {
                case "Miles":
                    cableLength *= 1609.34; // 1 mile = 1609.34 meters
                    break;
                case "Kilometers":
                    cableLength *= 1000.0; // 1 km = 1000 meters
                    break;
                case "Feet":
                    cableLength *= 0.3048; // 1 foot = 0.3048 meters
                    break;
                case "Meters":
                    // No conversion needed
                    break;
                default:
                    throw new IllegalArgumentException("Unknown measurement");
            }


            double totalCableLoss = cableLength * cableLossPerKm / 1000.0; // convert from km to meters
            double totalConnectorLoss = connectors * connectorLoss;
            double totalSpliceLoss = splices * spliceLoss;

            double totalLoss = totalCableLoss + totalConnectorLoss + totalSpliceLoss;


            lossBudgetTotalLoss.setText(String.format("%.2f", totalLoss) + " dB");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            errorAlert("Invalid number format");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            errorAlert("Invalid cable type or measurement");
        }
    }


    public void onClickLossBudgetCredits(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fibertools/main/pages/lossBudgetCalculator/credits.fxml"));
            StackPane dialogContent = loader.load();

            //TODO: Fix White Bar at the bottom of Dialog
            Dialog dialog = new Dialog();
            dialog.setTitle("Credits");
            dialog.getDialogPane().setContent(dialogContent);
            dialog.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
