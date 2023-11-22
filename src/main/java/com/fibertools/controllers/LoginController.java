package com.fibertools.controllers;

import com.fibertools.dao.UserSQL;
import com.fibertools.models.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class LoginController {

    public TextField usernameField;
    public TextField passwordField;
    public Button loginPageLoginButton;

    public void onClickLogin(ActionEvent actionEvent) {
        try {
            Users loggedInUser = UserSQL.loggedInUser(
                    usernameField.getText(),
                    passwordField.getText()
            );
            if (loggedInUser != null) {
                showInformationAlert("Login Successful", "Welcome " + loggedInUser.getUsername());
                Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fibertools/main/main.fxml"));
                Scene scene = new Scene(loader.load(), 1000, 600);
                stage.setTitle("FiberTools");
                stage.setScene(scene);
                stage.show();

            } else {
                showErrorAlert("Login Failed", "Invalid Username or Password");
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    private void showInformationAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
