package com.example.javalab2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory {
    private String driver;
    private String url;
    private String user;
    private String password;
    private static ConnectionFactory connector;

    private ConnectionFactory() {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        this.driver = resource.getString("db.driver");
        this.url = resource.getString("db.url");
        this.user = resource.getString("db.user");
        this.password = resource.getString("db.password");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ConnectionFactory getInstance() {
        return (connector == null) ? new ConnectionFactory() : connector;
    }
}
