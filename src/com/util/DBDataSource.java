package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBDataSource {
    public static final String URL = "jdbc:postgresql://localhost:5432/Stock";
    public static final String USER = "postgres";
    public static final String PASSWORD = "000000";

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
