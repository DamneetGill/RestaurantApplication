package com.example.restaurantapplication;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class SummaryController implements Initializable {

    @FXML
    private Button button_cancel_order;

    @FXML
    private Button button_confirm_order;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label subtotalLabel;
    @FXML
    private Label totalLabel;
    @FXML
    private TextField tf_cap;

    @FXML
    private TextField tf_city;

    @FXML
    private TextField tf_house_number;

    @FXML
    private TextField tf_street;

    @FXML
    private Label orderNumberLabel;
    @FXML
    private GridPane orderedGrid;
    @FXML
    private ScrollPane scrollPane;

    String username;
    int orderCode;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setOrderNumber(int orderCode) {
        this.orderCode = orderCode;
    }

    public void setOrderedGrid(List<String> orderedCodes, List<Plate> plates, List<Integer> count) {
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < plates.size(); i++) {
                for (int j = 0; j < orderedCodes.size(); j++) {
                    if (plates.get(i).getCode().matches(orderedCodes.get(j))) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("base-summary-overview.fxml"));
                        AnchorPane anchorPane = fxmlLoader.load();
                        BaseSummaryController baseSummaryController = fxmlLoader.getController();
                        baseSummaryController.setOrderedData(plates.get(i), count.get(j));

                        if (column == 1) {
                            column = 0;
                            row++;
                        }
                        column++;
                        orderedGrid.add(anchorPane, column, row);
                        //set grid width
                        orderedGrid.setMinWidth(Region.USE_PREF_SIZE);
                        orderedGrid.setPrefWidth(Region.USE_PREF_SIZE);
                        orderedGrid.setMaxWidth(Region.USE_PREF_SIZE);

                        //set grid height
                        orderedGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                        orderedGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                        orderedGrid.setMaxHeight(Region.USE_PREF_SIZE);

                        GridPane.setMargin(anchorPane, new Insets(10));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_cancel_order.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.cancelOrder(event,orderCode);
                DBUtils.changeScene(event, "home-overview.fxml", "Order now at \"Casa Mia Restaurant\"", null);

            }
        });
        button_confirm_order.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (!tf_street.getText().trim().isEmpty() && !tf_cap.getText().trim().isEmpty() && !tf_city.getText().trim().isEmpty() && !tf_house_number.getText().trim().isEmpty()) {
                    DBUtils.addressInfo(event, username, tf_street.getText().toString(), tf_house_number.getText().toString(), tf_cap.getText().toString(), tf_city.getText().toString());
                } else {
                    System.out.println("Please fill in all information");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all information to continue");
                    alert.show();
                }
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

    public void setUsernameLabel(String username) {
        usernameLabel.setText("Thank you " + username);
    }

    public void setOrderNumberLabel(int orderCode) {
        orderNumberLabel.setText("Your Order: #" + orderCode);
    }

}
