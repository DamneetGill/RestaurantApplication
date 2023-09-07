package com.example.restaurantapplication;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Address implements Initializable {

    @FXML
    private Button button_back;
    @FXML
    private Button button_continue;
    @FXML
    private TextField tf_order_cod;

    @FXML
    private TextField tf_street;

    @FXML
    private TextField tf_house_number;

    @FXML
    private TextField tf_city;

    @FXML
    private TextField tf_cap;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        button_back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "order-overview.fxml", "Order now at \"Casa Mia Restaurant\"", null);
            }
        });
        button_continue.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (!tf_street.getText().trim().isEmpty() && !tf_cap.getText().trim().isEmpty() && !tf_city.getText().trim().isEmpty() && !tf_house_number.getText().trim().isEmpty()) {
                    DBUtils.changeScene(event, "summary-overview.fxml", "Checkout", null);
                } else {
                    System.out.println("Please fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to continue");
                    alert.show();
                }
            }
        });
    }

    public void setOrderNumber(int orderNumber) {
        tf_order_cod.setText(("Order #" + orderNumber));
        tf_order_cod.setAlignment(Pos.CENTER);
    }
}
