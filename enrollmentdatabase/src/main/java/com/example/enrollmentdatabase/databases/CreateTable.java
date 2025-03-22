package com.example.enrollmentdatabase.databases;

import java.sql.Connection;
import java.sql.Statement;

public class CreateTable {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            String createStudentsTable = "CREATE TABLE IF NOT EXISTS students (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "course VARCHAR(50) NOT NULL" +
                    ");";

            String createCoursesTable = "CREATE TABLE IF NOT EXISTS courses (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "course_name VARCHAR(100) NOT NULL UNIQUE" +
                    ");";

            stmt.executeUpdate(createStudentsTable);
            stmt.executeUpdate(createCoursesTable);

            System.out.println("✅ Tables created successfully!");

        } catch (Exception e) {
            System.out.println("❌ Error creating tables: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
