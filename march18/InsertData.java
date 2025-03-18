package com.example.csit228examanswers.march18;

import java.sql.*;

public class InsertData {
    public static final String URL = "jdbc:mysql://localhost:3306/csit228f3";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users (name, email) VALUES (?, ?)"
            )
        ){
            System.out.println("DATABASE CONNECTION SUCCESSFUL");
            String name = "Cherry";
            String email = "cstaromana@cit.edu";
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("INSERTED SUCCESSFULLY");
            }
//            Statement statement = connection.createStatement();
//            String query = "CREATE TABLE users (id INT NOT NULL AUTO_INCREMENT, " +
//                    "name VARCHAR(50) NOT NULL, " +
//                    "email VARCHAR(100) NOT NULL, " +
//                    "password VARCHAR(50) NULL, "+
//                    "PRIMARY KEY (id))";
//            statement.execute(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

