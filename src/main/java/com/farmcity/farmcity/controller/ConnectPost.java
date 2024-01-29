package com.farmcity.farmcity.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectPost {
    public Connection ConnectionBase() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://monorail.proxy.rlwy.net:47003/railway", "postgres", "2Geb5E6eGe3A*Gb622dDFFA*D3B2g*D6");
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException(e);
        }
    }
}
