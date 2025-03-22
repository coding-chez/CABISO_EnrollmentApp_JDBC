package com.example.enrollmentdatabase.march18;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateData {
    public static final String URL = "jdbc:mysql://localhost:3306/csit228f3";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE users SET name=?, email =? WHERE id=?"
             )
        ){
            String name = "Cherry Lyn Sta Romana";
            String email = "cherrylyn.staromana@cit.edu";
            int id = 2;
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
            preparedStatement.setInt(3,id);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("UPDATED SUCCESSFULLY");
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
