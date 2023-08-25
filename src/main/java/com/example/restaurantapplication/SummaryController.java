package com.example.restaurantapplication;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class SummaryController implements Initializable {

    @FXML
    private Button button_cancel_order;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label subtotalLabel;
    @FXML
    private Label totalLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_cancel_order.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "order-overview.fxml", "Order Now!", null);
            }
        });
    }

    public void setSubtotalLabel(String price) {
        subtotalLabel.setText("€" + price);
    }

    public void setTotalLabel(String price) {
        double total = Double.parseDouble(price);
        total += 2.99;
        String totalPrice = String.format(Locale.US, "%.2f", total);
        totalLabel.setText("€" + totalPrice);
    }
}
