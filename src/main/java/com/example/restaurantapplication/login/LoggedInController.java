package com.example.restaurantapplication.login;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {

    @FXML
    private Button button_log_out;

    @FXML
    private Label label_welcome;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_log_out.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "sample.fxml", "Log in!", null);
            }
        });
    }

    public void setUserInformation(String Username) {
        label_welcome.setText(("Welcome " + Username + "!"));
    }

}
