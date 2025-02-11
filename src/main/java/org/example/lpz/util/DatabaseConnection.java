package org.example.lpz.util;

import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/javaClass";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection myConn;

    public static Connection getInstance() {
        if(myConn == null){
            try {
                myConn = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return myConn;
    }

}
