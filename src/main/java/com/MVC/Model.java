package com.MVC;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class  Model {

    protected Connection conn;

    protected Model() throws SQLException, ClassNotFoundException {
        String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        Class.forName(JDBC_DRIVER);
        String DB_URL = "jdbc:mysql://localhost/test";
        String USER = "root";
        String PASS = "";

        conn = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    protected String getTimeNow() {
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return LocalDateTime.now().format(myFormatDate);
    }
}
