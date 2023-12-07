package com.fibertools.controllers;


// Color to Fiber and Fiber to Color
// https://www.thefoa.org/tech/coloc_codes/Color_Codes_Card_Fiber_Uprint.pdf


import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ConversionsController implements Initializable {
    public BorderPane contents;
    public TextField fiberNumberTextField;
    public ComboBox tubeColorComboBox;
    public ComboBox fiberColorComboBox;
    public MFXButton calculateButton;

    public void setContents(BorderPane contents) {
        this.contents = contents;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String[] fiberColors = {"Blue", "Orange", "Green", "Brown", "Slate", "White", "Red", "Black", "Yellow", "Violet", "Rose", "Aqua", "-Blue", "-Orange", "-Green", "-Brown", "-Slate", "-White", "-Red", "-Black", "-Yellow", "-Violet", "-Rose", "-Aqua"};
        String[] tubeColors = {"Blue", "Orange", "Green", "Brown", "Slate", "White", "Red", "Black", "Yellow", "Violet", "Rose", "Aqua", "-Blue", "-Orange", "-Green", "-Brown", "-Slate", "-White", "-Red", "-Black", "-Yellow", "-Violet", "-Rose", "-Aqua"};
        fiberColorComboBox.getItems().addAll(fiberColors);
        tubeColorComboBox.getItems().addAll(tubeColors);
    }

    public void onClickCalculateButton(ActionEvent actionEvent) {
    }

}
