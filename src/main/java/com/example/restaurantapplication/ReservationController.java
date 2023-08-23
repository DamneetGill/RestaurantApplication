package com.example.restaurantapplication;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {

    @FXML
    private Button button_back;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        button_back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "home-overview.fxml", "Casa Mia Restaurant", null);
            }
        });
    }
}
