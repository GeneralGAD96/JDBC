package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {

    private static String dbURL = "jdbc:mysql://localhost:3306/mydbtest";
    private static String dbUserName = "root";
    private static String dbPassword = "root";

    public static Connection getConnection() {

        Connection connection;
        try {
            connection = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
            return connection;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
