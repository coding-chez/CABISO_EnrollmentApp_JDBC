package com.example.enrollmentdatabase.databases;

import com.example.enrollmentdatabase.model.Courses;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDBHandler {

    public static List<Courses> getAllCourses() {
        List<Courses> courses = new ArrayList<>();
        String query = "SELECT id, course_name FROM courses";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("Fetching courses from database...");

            // Default placeholder option
            courses.add(new Courses(0, "Select Course"));

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("course_name");
                System.out.println("Course fetched: " + name);
                courses.add(new Courses(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public static void addCourse(String courseName) {
        String query = "INSERT INTO courses (course_name) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, courseName);
            stmt.executeUpdate();
            System.out.println("Course added: " + courseName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCourse(int courseId) {
        String query = "DELETE FROM courses WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, courseId);
            stmt.executeUpdate();
            System.out.println("Course deleted: ID " + courseId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
