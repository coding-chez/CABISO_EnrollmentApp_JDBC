package com.example.enrollmentdatabase.march18;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteData {
    public static final String URL = "jdbc:mysql://localhost:3306/csit228f3";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM users WHERE id=?"
             )
        ){
            int id = 2;
            preparedStatement.setInt(1,id);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("DELETED SUCCESSFULLY");
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
