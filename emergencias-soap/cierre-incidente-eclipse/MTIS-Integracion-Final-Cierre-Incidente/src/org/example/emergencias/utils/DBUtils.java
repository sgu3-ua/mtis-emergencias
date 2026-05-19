package org.example.emergencias.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {
    private static final String URL =
    		  "jdbc:mysql://localhost:3306/sistema_emergencias?autoReconnect=true&useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static Connection getConnection() throws Exception {
    	Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }
}