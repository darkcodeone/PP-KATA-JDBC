package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/userdb?autoReconnect=true&useSSL=false";
    private static final String PASSWORD = "amogus";
    private static final String USER = "valerkamops";
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
            System.out.println("Connection set!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something isn't ok.");
        }
        return connection;
    }
}
