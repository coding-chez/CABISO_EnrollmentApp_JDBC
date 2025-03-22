package com.example.enrollmentdatabase.databases;

import com.example.enrollmentdatabase.model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDBHandler {

    public static void insertStudent(Student student) {
        String sql = "INSERT INTO students (name, course) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getCourse().getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT students.id, students.name, courses.id AS course_id, courses.course_name " +
                "FROM students JOIN courses ON students.course_id = courses.id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int courseId = rs.getInt("course_id");
                String courseName = rs.getString("course_name");

                students.add(new Student(id, name, new com.example.enrollmentdatabase.model.Courses(courseId, courseName)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static void updateStudent(Student student) {
        String sql = "UPDATE students SET name = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateStudentCourse(int studentId, int newCourseId) {
        String sql = "UPDATE students SET course_id = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newCourseId);
            pstmt.setInt(2, studentId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteStudent(int studentId) {
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addStudent(String name, String courseName) {
        String sql = "INSERT INTO students (name, course) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            System.out.println("SQL Query: " + sql);
            System.out.println("Parameters: Name = " + name + ", Course = " + courseName);

            pstmt.setString(1, name);
            pstmt.setString(2, courseName); // Store course name instead of ID

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student added successfully!");
            } else {
                System.out.println("Error: Student was not added to the database.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void updateStudent(int id, String newName, int courseId) {
        String sql = "UPDATE students SET name = ?, course_id = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setInt(2, courseId);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
