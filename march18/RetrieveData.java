package com.example.csit228examanswers.march18;

import java.sql.*;

public class RetrieveData {
    public static final String URL = "jdbc:mysql://localhost:3306/csit228f3";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString("name");
                String email = resultSet.getString(3);
                System.out.println("[" + id + "] " + name + " - " + email);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
