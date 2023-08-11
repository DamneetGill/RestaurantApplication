package com.example.restaurantapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginLabel;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private TextField tfUsername;

    public void handleCancelButton(ActionEvent e) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void handleLoginButton(ActionEvent e) {
        if (tfUsername.getText().isBlank() == true || pfPassword.getText().isBlank() == true) {
            loginLabel.setText("please insert username and password");
        }
        LoginDatabaseConnection connection = new LoginDatabaseConnection();
        Connection connectDB = connection.getConnection();
        String verify = "SELECT count(1) FROM useraccounts WHERE username='" + tfUsername.getText() + "' AND password='" + pfPassword.getText() + "'";
        try{
            Statement statement =connectDB.createStatement();
            ResultSet queryResult= statement.executeQuery(verify);
            while(queryResult.next()){
                if(queryResult.getInt(1)==1){
                    loginLabel.setText("Welcome");
                }
                else{
                    loginLabel.setText("Invalid username and password");
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


}
