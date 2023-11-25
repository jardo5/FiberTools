package com.fibertools.controllers.TraceViewerControllers;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.animation.FadeTransition;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class TraceViewerController {

    @FXML
    public StackPane confirmationTextStackPane;

    @FXML
    private StackPane innerMiddleStackPane;

    @FXML
    private Text statusMessage;

    private File droppedFile;


    @FXML
    private void onDragEntered(DragEvent event) {
        innerMiddleStackPane.setStyle("-fx-background-color: #3282b8;"); // Color when file is dragged over
    }

    @FXML
    private void onDragExited(DragEvent event) {
        innerMiddleStackPane.setStyle("-fx-background-color: #323d43;"); // Original color
    }


    public void initialize() {
        setupDragAndDrop();
        confirmationTextStackPane.setOpacity(0);
    }

    private void setupDragAndDrop() {
        innerMiddleStackPane.setOnDragOver(this::onDragOver);
        innerMiddleStackPane.setOnDragDropped(this::onDragDropped);
    }

    private void onDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        event.consume();
    }

    private void onDragDropped(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            File file = db.getFiles().get(0);
            String fileName = file.getName();
            if (fileName.toLowerCase().endsWith(".sor")) {
                success = true;
                droppedFile = file;
                updateStatusMessage("File loaded: " + droppedFile.getName(), false); // false for success
            } else {
                updateStatusMessage("Invalid file type. Please drop a .sor file.", true); // true for error
            }
        }
        event.setDropCompleted(success);
        event.consume();
    }

    @FXML
    private void onBrowseClicked() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SOR files (*.sor)", "*.sor");
        fileChooser.getExtensionFilters().add(extFilter);


        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            droppedFile = file;
            updateStatusMessage("File loaded: " + droppedFile.getName(), false);
        }
    }

    private void updateStatusMessage(String message, boolean isError) {
        Platform.runLater(() -> {
            statusMessage.setText(message);
            confirmationTextStackPane.setOpacity(1.0);

            if (isError) {
                confirmationTextStackPane.setStyle("-fx-background-color: #D97B6E;");
            } else {
                confirmationTextStackPane.setStyle("-fx-background-color: #6E8FD9;");
            }

            FadeTransition fade = new FadeTransition(Duration.seconds(3), confirmationTextStackPane);
            fade.setFromValue(1.0);
            fade.setToValue(0.0);
            fade.setDelay(Duration.seconds(5));
            fade.play();
        });
    }



    private void showError(String errorMessage) {
        Platform.runLater(() -> {
            updateStatusMessage(errorMessage, true);
        });
    }

    @FXML
    private void onGenerateClicked() {
        if (droppedFile != null) {
            try {
                runPyOTDR(droppedFile.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
                showError("Error processing file.");
            }
        } else {
            showError("No file loaded. Please drag and drop a .sor file or use the 'Browse File' button.");
        }
    }

    private void runPyOTDR(String filePath) throws IOException {
        String pythonPath = "/Library/Frameworks/Python.framework/Versions/3.12/bin/python3";
        String pyotdrScriptPath = "/Library/Frameworks/Python.framework/Versions/3.12/bin/pyotdr";
        ProcessBuilder processBuilder = new ProcessBuilder(pythonPath, pyotdrScriptPath, filePath);
        processBuilder.redirectErrorStream(true); // Redirect stderr to stdout

        Process process = processBuilder.start();

        // Reading the combined output stream of the process
        new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line); // All output, including errors, is now in stdout
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            int exitCode = process.waitFor();
            System.out.println("pyOTDR process exited with code " + exitCode);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    // Opens Trace Viewer
    public void onViewClicked(ActionEvent actionEvent) {
    }
}
