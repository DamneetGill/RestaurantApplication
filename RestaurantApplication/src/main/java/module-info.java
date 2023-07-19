module com.example.restaurantapplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.restaurantapplication to javafx.fxml;
    exports com.example.restaurantapplication;
}