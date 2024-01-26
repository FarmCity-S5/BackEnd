package com.farmcity.farmcity.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectPost {
    public Connection ConnectionBase() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/city", "rakharrs", "pixel");
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException(e);
        }
    }
}
