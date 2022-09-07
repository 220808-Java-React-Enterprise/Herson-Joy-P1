package com.revature.P1.utils.database;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/* Singleton design pattern */
public class ConnectionFactory {
    private static ConnectionFactory connectionFactory;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private final Properties props = new Properties();

    private ConnectionFactory() {
//        try {
//            props.load(new FileReader("db.properties"));
//            System.out.println(props.getProperty("url"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) connectionFactory = new ConnectionFactory();
        return connectionFactory;
    }

    public Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://p1-database-1.c6nbfc7sxlvy.us-east-2.rds.amazonaws.com:5432/postgres?currentSchema=p1";
        String username = "postgres";
        String password = "revature";


        //Connection conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));
        Connection conn = DriverManager.getConnection(url,username,password);
        if (conn == null) throw new RuntimeException("Could not establish connection with the database!");
        System.out.println("connect success");
        return conn;
    }
}
