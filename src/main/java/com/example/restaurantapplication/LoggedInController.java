package com.example.restaurantapplication;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class LoggedInController implements Initializable {

    @FXML
    private Button button_log_out;
    @FXML
    private Button button_continue;

    @FXML
    private Label label_welcome;
    String username;

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        button_log_out.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event, "sample.fxml", "Log in now at \"Casa Mia Restaurant\"", null);
            }
        });

        button_continue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                FXMLLoader loader=new FXMLLoader(getClass().getResource("order-overview.fxml"));
                try {
                    Parent root=loader.load();
                    OrderController orderController=loader.<OrderController>getController();
                    orderController.setUsername(username);
                    Scene scene=new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Order now at \"Casa Mia Restaurant\"");
                    stage.show();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }



    public void setUserInformation(String username) {
        label_welcome.setText(("Welcome " + username + "!"));
        label_welcome.setAlignment(Pos.CENTER);
        label_welcome.setWrapText(true);

    }

}
