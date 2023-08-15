package com.example.restaurantapplication.login;

import java.sql.Connection;
import java.sql.DriverManager;

public class LoginDatabaseConnectionPrev {
    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName = "";
        String databaseUser = "";
        String databasePassword = "";
        String url = "jdbc:mysql://localhost:3306/" + databaseName;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);

        } catch (Exception e) {
            throw new RuntimeException(e);

        }
        return databaseLink;
    }
}
