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
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class SummaryController implements Initializable {
    @FXML
    private Button button_cancel_order;

    @FXML
    private Button button_confirm_order;

    @FXML
    private GridPane grid_ordered;

    @FXML
    private Label label_order_number;

    @FXML
    private Label label_subtotal;

    @FXML
    private Label label_total;

    @FXML
    private Label label_username;

    @FXML
    private ScrollPane scroll_pane;

    @FXML
    private TextField tf_cap;

    @FXML
    private TextField tf_city;

    @FXML
    private TextField tf_house_number;

    @FXML
    private TextField tf_street;

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
            for (Plate plate : plates) {
                for (int j = 0; j < orderedCodes.size(); j++) {
                    if (!orderedCodes.get(j).matches("0")) {
                        if (plate.getCode().matches(orderedCodes.get(j))) {
                            FXMLLoader fxmlLoader = new FXMLLoader();
                            fxmlLoader.setLocation(getClass().getResource("base-summary-overview.fxml"));
                            AnchorPane anchorPane = fxmlLoader.load();
                            BaseSummaryController baseSummaryController = fxmlLoader.getController();
                            baseSummaryController.setOrderedData(plate, count.get(j));

                            if (column == 1) {
                                column = 0;
                                row++;
                            }
                            column++;
                            grid_ordered.add(anchorPane, column, row);
                            //set grid width
                            grid_ordered.setMinWidth(Region.USE_PREF_SIZE);
                            grid_ordered.setPrefWidth(Region.USE_PREF_SIZE);
                            grid_ordered.setMaxWidth(Region.USE_PREF_SIZE);

                            //set grid height
                            grid_ordered.setMinHeight(Region.USE_COMPUTED_SIZE);
                            grid_ordered.setPrefHeight(Region.USE_COMPUTED_SIZE);
                            grid_ordered.setMaxHeight(Region.USE_PREF_SIZE);

                            GridPane.setMargin(anchorPane, new Insets(10));
                        }
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
                DBUtils.cancelOrder(event, orderCode);
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
        label_subtotal.setText("€" + price);
    }

    public void setTotalLabel(String price) {
        double total = Double.parseDouble(price);
        total += 2.99;
        String totalPrice = String.format(Locale.US, "%.2f", total);
        label_total.setText("€" + totalPrice);
    }

    public void setUsernameLabel(String username) {
        label_username.setText("Thank you " + username);
    }

    public void setOrderNumberLabel(int orderCode) {
        label_order_number.setText("Your Order: #" + orderCode);
    }

}
