package com.example.enrollmentdatabase.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersTable {
    public static final String URL = "jdbc:mysql://localhost:3306/dbenrollment";
    public static final String STUDENTNAME = "root";
    public static final String PASSWORD = "";
    public static void main(String[] args) {
        Connection connection = null;
        //TODO: fix DriverManager SQLException
        try {
            connection = DriverManager.getConnection(URL, STUDENTNAME, PASSWORD);
            System.out.println("DATABASE CONNECTION SUCCESSFUL");
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE users (studentID INT NOT NULL AUTO_INCREMENT, " +
                    "studentName VARCHAR(50) NOT NULL, " +
                    "password VARCHAR(50) NULL, "+
                    "PRIMARY KEY (studentID))";
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
