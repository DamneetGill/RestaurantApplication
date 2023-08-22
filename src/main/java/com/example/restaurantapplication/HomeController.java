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
    public void handleOrder(ActionEvent event) throws Exception {
        Stage login = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.setTitle("Log in now at \"Casa Mia Restaurant\"");
        login.show();
    }

    @FXML
    public void handleReservation(ActionEvent event) throws Exception {
        Stage login = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("reservation-overview.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.setTitle("Make your reservation");
        login.show();
    }

}




