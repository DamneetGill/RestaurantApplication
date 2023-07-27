package com.example.restaurantapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class HomeController {

    @FXML
    private Button OrderButton;

    @FXML
    private Button ReservationButton;


    @FXML
    void handleOrder(ActionEvent event) throws Exception {

        /*open the sign-in/login window*/
        Stage login = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("login-overview.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.show();
    }

    @FXML
    void handleReservation(ActionEvent event) {

    }

}




