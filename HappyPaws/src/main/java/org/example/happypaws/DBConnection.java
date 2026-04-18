package org.example.happypaws;

import java.sql.*;
import java.util.Properties;

public class DBConnection {

    private String host = "localhost";
    private String port = "1521";
    private String db   = "orcl";
    private String user = "SYS";
    private String pass = "Oracle2026";

    public Connection connect() {
        try {
            Properties props = new Properties();
            props.setProperty("user", user);
            props.setProperty("password", pass);
            props.setProperty("internal_logon", "SYSDBA");

            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@" + host + ":" + port + ":" + db, props
            );
            return conn;

        } catch (Exception e) {
            System.out.println("Error en la DB connection: " + e.getMessage());
            return null;
        }
    }
}