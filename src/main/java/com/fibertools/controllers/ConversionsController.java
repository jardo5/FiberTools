package com.fibertools.controllers;


// Color to Fiber and Fiber to Color
// https://www.thefoa.org/tech/coloc_codes/Color_Codes_Card_Fiber_Uprint.pdf


import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Arrays; // Import statement for Arrays

public class ConversionsController implements Initializable {
    public MFXButton calculateButton;

    public Rectangle tubeColorBox;
    public Line tubeColorLine;

    public Rectangle fiberColorBox;
    public Line fiberColorLine;

    public BorderPane contents;

    public StackPane errorTextStackPane;
    public Text statusMessage;

    public void setContents(BorderPane contents) {
        this.contents = contents;
    }

    @FXML
    private TextField fiberNumberTextField;
    @FXML
    private ComboBox<String> tubeColorComboBox;
    @FXML
    private ComboBox<String> fiberColorComboBox;
    @FXML
    private ComboBox<Integer> selectFiberCountComboBox;
    @FXML
    private ComboBox<Integer> selectFiberInTubeCountComboBox;

    private final Map<String, String> colorHexMap = new HashMap<>();


    private final String[] solidColors = {
            "Blue", "Orange", "Green", "Brown", "Slate", "White",
            "Red", "Black", "Yellow", "Violet", "Rose", "Aqua"
    };

    private final String[] dashedColors = {
            "-Blue", "-Orange", "-Green", "-Brown", "-Slate", "-White",
            "-Red", "-Black", "-Yellow", "-Violet", "-Rose", "-Aqua"
    };

    private final Integer[] fiberInTubeOptions = { 12, 24 }; // Options for fibers in a tube

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> tubeColors = FXCollections.observableArrayList(Arrays.asList(solidColors));
        tubeColorComboBox.setItems(tubeColors);

        ObservableList<String> fiberColors = FXCollections.observableArrayList(Arrays.asList(solidColors));
        fiberColorComboBox.setItems(fiberColors);

        ObservableList<Integer> fiberCounts = FXCollections.observableArrayList(144, 288, 432);
        selectFiberCountComboBox.setItems(fiberCounts);

        ObservableList<Integer> fiberInTubeCounts = FXCollections.observableArrayList(fiberInTubeOptions);
        selectFiberInTubeCountComboBox.setItems(fiberInTubeCounts);

        // Listener for the fiber count selection
        selectFiberCountComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                selectFiberInTubeCountComboBox.setValue(newVal == 144 ? 12 : 24);
                updateColorOptions(newVal);
            }
        });

        // Initialize with 144 fiber count setup
        selectFiberCountComboBox.setValue(144);

        initializeColorHexMap();
    }

    private void initializeColorHexMap() {
        colorHexMap.put("Blue", "#2196F3");
        colorHexMap.put("Orange", "#FF9800");
        colorHexMap.put("Green", "#4CAF50");
        colorHexMap.put("Brown", "#795548");
        colorHexMap.put("Slate", "#607D8B");
        colorHexMap.put("White", "#FFFFFF");
        colorHexMap.put("Red", "#F44336");
        colorHexMap.put("Black", "#000000");
        colorHexMap.put("Yellow", "#FFEB3B");
        colorHexMap.put("Violet", "#9C27B0");
        colorHexMap.put("Rose", "#E91E63");
        colorHexMap.put("Aqua", "#00BCD4");
    }

    @FXML
    private void onClickCalculateButton(ActionEvent event) {
        Integer fiberCount = selectFiberCountComboBox.getValue();
        Integer fibersPerTube = selectFiberInTubeCountComboBox.getValue();
        String tubeColor = tubeColorComboBox.getValue();
        String fiberColor = fiberColorComboBox.getValue();

        if (fiberCount == null || fibersPerTube == null || tubeColor == null || fiberColor == null) {
            //Error Message
            showError("Please select all options.");
            return;
        }

        int tubeIndex = getTubeIndex(tubeColor, fiberCount);
        int fiberIndex = getFiberIndex(fiberColor);

        if (tubeIndex == -1 || fiberIndex == -1) {
            showError("Invalid color selection.");
            return;
        }

        int fiberNumber = tubeIndex * fibersPerTube + fiberIndex + 1; // +1 because fiber numbers start at 1
        fiberNumberTextField.setText(String.valueOf(fiberNumber));

        if (tubeColor != null && fiberColor != null) {
            updateColorBoxes(tubeColor, fiberColor);
        }
    }

    @FXML
    private void onFiberNumberEntered(ActionEvent event) {
        Integer fiberCount = selectFiberCountComboBox.getValue();
        Integer fibersPerTube = selectFiberInTubeCountComboBox.getValue();
        Integer fiberNumber = tryParse(fiberNumberTextField.getText());

        if (fiberCount == null || fibersPerTube == null || fiberNumber == null) {
            showError("Please select all options.");
            return;
        }

        int tubeIndex = (fiberNumber - 1) / fibersPerTube;
        int fiberIndex = (fiberNumber - 1) % fibersPerTube;

        String tubeColor = getTubeColor(tubeIndex, fiberCount);
        String fiberColor = getFiberColor(fiberIndex);

        if (tubeColor != null && fiberColor != null) {
            tubeColorComboBox.setValue(tubeColor);
            fiberColorComboBox.setValue(fiberColor);
            updateColorBoxes(tubeColor, fiberColor);
        }
    }


    private void updateColorOptions(int fiberCount) {
        ObservableList<String> tubeColors = FXCollections.observableArrayList();
        ObservableList<String> fiberColors = FXCollections.observableArrayList();

        // Always add all solid colors
        tubeColors.addAll(Arrays.asList(solidColors));
        fiberColors.addAll(Arrays.asList(solidColors));

        // Add dashed colors based on the selected fiber count
        if (fiberCount == 432) {
            // For 432, tube color includes solid and dashed up to "-White"
            tubeColors.addAll(Arrays.asList(dashedColors).subList(0, 6));
        }

        if (fiberCount >= 288) {
            // For 288 and 432, fiber color includes solid and all dashed colors
            fiberColors.addAll(Arrays.asList(dashedColors));
        }

        tubeColorComboBox.setItems(tubeColors);
        fiberColorComboBox.setItems(fiberColors);

        // Reset the selected items when the options change
        tubeColorComboBox.setValue(null);
        fiberColorComboBox.setValue(null);
    }

    private int getTubeIndex(String color, Integer fiberCount) {
        int index = Arrays.asList(solidColors).indexOf(color);
        if (index != -1) {
            return index;
        }

        index = Arrays.asList(dashedColors).indexOf(color);
        if (index != -1) {
            return (fiberCount == 432) ? index + 12 : index; // For 432 fiber count, dashed starts after 12 solid tubes
        }

        return -1; // Color not found
    }

    private int getFiberIndex(String color) {
        int index = Arrays.asList(solidColors).indexOf(color);
        if (index != -1) {
            return index;
        }

        index = Arrays.asList(dashedColors).indexOf(color);
        return index != -1 ? index + 12 : -1; // Dashed colors follow after 12 solid colors
    }

    private String getTubeColor(int tubeIndex, Integer fiberCount) {
        if (tubeIndex < 12) {
            return solidColors[tubeIndex];
        } else if (fiberCount == 432 && tubeIndex < 18) {
            return dashedColors[tubeIndex - 12]; // For 432 fiber count, dashed starts after 12 solid tubes
        } else {
            return null; // Invalid tube index
        }
    }

    private String getFiberColor(int fiberIndex) {
        if (fiberIndex < 12) {
            return solidColors[fiberIndex];
        } else {
            return dashedColors[fiberIndex - 12]; // Dashed colors follow after 12 solid colors
        }
    }

    private void updateColorBoxes(String tubeColor, String fiberColor) {
        tubeColorBox.setVisible(true);
        fiberColorBox.setVisible(true);

        String tubeHex = getColorHex(tubeColor.replace("-", ""));
        String fiberHex = getColorHex(fiberColor.replace("-", ""));

        tubeColorBox.setFill(Color.web(tubeHex));
        fiberColorBox.setFill(Color.web(fiberHex));

        // Set the line color to white if the background is black, else black
        if (tubeColor.equals("-Black")) {
            tubeColorLine.setStroke(Color.WHITE);
        } else {
            tubeColorLine.setStroke(Color.BLACK);
        }
        tubeColorLine.setVisible(tubeColor.startsWith("-"));

        if (fiberColor.equals("-Black")) {
            fiberColorLine.setStroke(Color.WHITE);
        } else {
            fiberColorLine.setStroke(Color.BLACK);
        }
        fiberColorLine.setVisible(fiberColor.startsWith("-"));
    }


    private String getColorHex(String colorName) {
        return colorHexMap.getOrDefault(colorName, "#FFFFFF"); // Default to white if color not found
    }

    private Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @FXML
    private void onResetButtonClicked(ActionEvent event) {
        selectFiberCountComboBox.setValue(144); // Reset to default fiber count
        selectFiberInTubeCountComboBox.setValue(12); // Reset to default fibers per tube
        tubeColorComboBox.setValue(null); // Clear tube color selection
        fiberColorComboBox.setValue(null); // Clear fiber color selection
        fiberNumberTextField.clear(); // Clear fiber number text field
        tubeColorBox.setVisible(false); // Hide tube color box
        fiberColorBox.setVisible(false); // Hide fiber color box
        tubeColorLine.setVisible(false); // Hide tube color line
        fiberColorLine.setVisible(false); // Hide fiber color line
    }

    private void updateStatusMessage(String message) {
        Platform.runLater(() -> {
            statusMessage.setText(message);
            errorTextStackPane.setVisible(true);
            errorTextStackPane.setStyle("-fx-background-color: #D97B6E;");

            FadeTransition fade = new FadeTransition(Duration.seconds(3), errorTextStackPane);
            fade.setFromValue(1.0);
            fade.setToValue(0.0);
            fade.setDelay(Duration.seconds(5));
            fade.play();
        });
    }


    private void showError(String errorMessage) {
        Platform.runLater(() -> {
            updateStatusMessage(errorMessage);
        });
    }
}
