package com.fibertools.controllers;

import com.fibertools.utils.FXMLLoaderUtils;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class CreditsController {

    public void openFlukeLossCalc(ActionEvent actionEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://www.flukenetworks.com/knowledge-base/simplifiber-pro/loss-budget-calculation-simplifiber-pro"));
    }

    public void openCorningLossCalc(ActionEvent actionEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://www.corning.com/optical-communications/worldwide/en/home/Resources/system-design-calculators/link-loss-budget-calculator.html"));
    }

    public void openFOAStanard(ActionEvent actionEvent) throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://www.thefoa.org/tech/tia568b3.htm"));
    }

    public void onClickExit(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
