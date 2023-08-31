package com.example.restaurantapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class DBUtils {
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {
        Parent root = null;

        if (username != null) {

            try {
                FXMLLoader loader = new FXMLLoader((DBUtils.class.getResource(fxmlFile)));
                root = loader.load();
                LoggedInController loggedInController = loader.getController();
                loggedInController.setUserInformation(username);
                loggedInController.setUsername(username);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(DBUtils.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void signUpUser(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExists = null;
        ResultSet resultSet = null;


        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_login", "root", "narinderjit_1969");
            psCheckUserExists = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckUserExists.setString(1, username);
            resultSet = psCheckUserExists.executeQuery();

            if (resultSet.isBeforeFirst()) {
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username.");
                alert.show();
            } else {
                psInsert = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
                psInsert.setString(1, username);
                psInsert.setString(2, password);
                psInsert.executeUpdate();

                changeScene(event, "sample.fxml", "Welcome!", username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psCheckUserExists != null) {
                try {
                    psCheckUserExists.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void logInUser(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_login", "root", "narinderjit_1969");
            preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found in the database!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");
                    if (retrievedPassword.equals(password)) {
                        changeScene(event, "logged-in.fxml", "Welcome!", username);
                    } else {
                        System.out.println("Password did not match!");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("The provided credentials are incorrect!");
                        alert.show();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void addressInfo(ActionEvent event, String username, String street, String houseNumber, String cap, String city) {
        boolean verify = true;
        Connection connection = null;
        PreparedStatement psInsert = null;


        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_login", "root", "narinderjit_1969");
            if (cap.isEmpty() || cap.length() != 5 || cap.matches("^[0-9]*$") == false) {
                System.out.println("Incorrect CAP");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("CAP inserted is not correct");
                alert.show();
                verify = false;
            }
            if (street.isEmpty() || street.matches("[0-9]") == true) {
                System.out.println("Incorrect street");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Street inserted is not correct");
                alert.show();
                verify = false;
            }
            if (houseNumber.isEmpty()) {
                System.out.println("Incorrect house number");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("House number inserted is not correct");
                alert.show();
                verify = false;
            }
            if (city.isEmpty() || city.matches("[0-9]") == true) {
                System.out.println("Incorrect city");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("City inserted is not correct");
                alert.show();
                verify = false;
            }

            if (verify) {
                psInsert = connection.prepareStatement("INSERT into addresses(user_id) select user_id from users where username=?");
                psInsert.setString(1, username);
                psInsert = connection.prepareStatement("INSERT into addresses(order_cod) select max(order_cod) from orders join users on (users.user_id=orders.user_id) where username = ?");
                psInsert.setString(1, username);
                psInsert = connection.prepareStatement("INSERT INTO addresses (street, home_number, cap, city) VALUES (?, ?, ?, ?)");
                psInsert.setString(1, street);
                psInsert.setString(2, houseNumber);
                psInsert.setString(3, cap);
                psInsert.setString(4, city);



                psInsert.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int newOrder(ActionEvent event, String username) {
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        int orderCode = 0;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_login", "root", "narinderjit_1969");
            psInsert = connection.prepareStatement("insert orders (user_id) select user_id from users where username=?");
            psInsert.setString(1, username);
            psInsert.executeUpdate();

            preparedStatement = connection.prepareStatement("select max(order_cod) from orders join users on (users.user_id=orders.user_id) where username=?");
            preparedStatement.setString(1, username);
            result = preparedStatement.executeQuery();
            if (result.next()) {
                orderCode = result.getInt(1);

            }
            return orderCode;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addToOrder(ActionEvent event, String code, int orderCode, int quantity) {
        Connection connection = null;
        PreparedStatement psInsert = null;

        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_login", "root", "narinderjit_1969");
            psInsert = connection.prepareStatement("update orders set " + code + "=" + code + " + ? where order_cod = ? ");
            psInsert.setInt(1, quantity);
            psInsert.setInt(2, orderCode);
            psInsert.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cancelOrder (ActionEvent event, int orderCode) {
        Connection connection = null;
        PreparedStatement psInsert = null;

        try {

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_login", "root", "narinderjit_1969");
            psInsert = connection.prepareStatement("delete from orders where order_cod = ? ");
            psInsert.setInt(1, orderCode);
            psInsert.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
