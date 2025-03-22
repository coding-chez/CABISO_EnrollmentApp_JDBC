package com.example.enrollmentdatabase.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    public static final String URL = "jdbc:mysql://localhost:3306/csit228f3";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            System.out.println("DATABASE CONNECTION SUCCESSFUL");

            String createStudentsTable = "CREATE TABLE IF NOT EXISTS students (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "course VARCHAR(50) NOT NULL)";

            String createCoursesTable = "CREATE TABLE IF NOT EXISTS courses (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "course_name VARCHAR(50) NOT NULL UNIQUE)";

            statement.execute(createStudentsTable);
            statement.execute(createCoursesTable);

            System.out.println("TABLES CREATED SUCCESSFULLY");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
