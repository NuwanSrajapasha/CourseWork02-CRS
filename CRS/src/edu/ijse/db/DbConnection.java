package edu.ijse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author ASUS
 */
public class DbConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/crs";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection getConnection()throws SQLException {
        return DriverManager.getConnection(URL,USER,PASS);
    }
}
