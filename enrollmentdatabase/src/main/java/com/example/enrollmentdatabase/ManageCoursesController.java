//package com.example.enrollmentdatabase;
//
//import com.example.enrollmentdatabase.databases.InsertCourse;
//import com.example.enrollmentdatabase.databases.RetrieveCourses;
//import com.example.enrollmentdatabase.databases.UpdateCourse;
//import com.example.enrollmentdatabase.databases.DeleteCourse;
//import com.example.enrollmentdatabase.model.Courses;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//
//import java.util.List;
//
//public class ManageCoursesController {
//
//    @FXML
//    private TextField tfCourseName;
//
//    @FXML
//    private TextField tfCourseId;
//
//    @FXML
//    private ListView<Courses> lvCourses;
//
//    @FXML
//    private Button btnAddCourse, btnUpdateCourse, btnDeleteCourse;
//
//    private ObservableList<Courses> courseList;
//
//    @FXML
//    public void initialize() {
//        loadCourses(); //
//        setupListViewListener();
//    }
//
//    private void loadCourses() {
//        List<Courses> courses = RetrieveCourses.getCourses();
//        courseList = FXCollections.observableArrayList(courses);
//        lvCourses.setItems(courseList);
//    }
//
//    private void setupListViewListener() {
//        lvCourses.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
//            if (newVal != null) {
//                tfCourseId.setText(String.valueOf(newVal.getCourseId()));
//                tfCourseName.setText(newVal.getCourseName());
//            }
//        });
//    }
//
//    @FXML
//    private void onAddCourseClicked() {
//        String courseName = tfCourseName.getText().trim();
//
//        if (courseName.isEmpty()) {
//            showAlert("Error", "Course name cannot be empty.");
//            return;
//        }
//
//        InsertCourse.insertCourse(courseName);
//        System.out.println("Course Added: " + courseName);
//
//        loadCourses(); // Refresh list
//        tfCourseName.clear();
//    }
//
//    @FXML
//    private void onUpdateCourseClicked() {
//        String newName = tfCourseName.getText().trim();
//        String courseIdStr = tfCourseId.getText().trim();
//
//        if (newName.isEmpty() || courseIdStr.isEmpty()) {
//            showAlert("Error", "Select a course and enter a new name.");
//            return;
//        }
//
//        int courseId = Integer.parseInt(courseIdStr);
//        UpdateCourse.updateCourse(courseId, newName);
//        System.out.println("Course Updated: " + newName);
//
//        loadCourses(); // Refresh list
//        tfCourseId.clear();
//        tfCourseName.clear();
//    }
//
//    @FXML
//    private void onDeleteCourseClicked() {
//        String courseIdStr = tfCourseId.getText().trim();
//
//        if (courseIdStr.isEmpty()) {
//            showAlert("Error", "Select a course to delete.");
//            return;
//        }
//
//        int courseId = Integer.parseInt(courseIdStr);
//        DeleteCourse.deleteCourse(courseId);
//        System.out.println("Course Deleted: ID " + courseId);
//
//        loadCourses(); // Refresh list
//        tfCourseId.clear();
//        tfCourseName.clear();
//    }
//
//    private void showAlert(String title, String message) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle(title);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//
//    public void onRemoveCourseClicked(ActionEvent actionEvent) {
//
//    }
//}
package com.example.enrollmentdatabase;

import com.example.enrollmentdatabase.databases.CourseDBHandler;
import com.example.enrollmentdatabase.model.Courses;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

public class ManageCoursesController {

    @FXML
    private TextField tfCourseName;

    @FXML
    private ListView<Courses> lvCourses;

    @FXML
    private Button btnDelete;

    @FXML
    public void initialize() {
        loadCourses();
        btnDelete.setDisable(true);

        lvCourses.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            btnDelete.setDisable(newSelection == null);
        });
    }

    private void loadCourses() {
        List<Courses> courses = CourseDBHandler.getAllCourses();
        ObservableList<Courses> courseOptions = FXCollections.observableArrayList(courses);
        lvCourses.setItems(courseOptions);
    }

    @FXML
    private void onAddCourseClicked() {
        String courseName = tfCourseName.getText().trim();
        if (!courseName.isEmpty()) {
            CourseDBHandler.addCourse(courseName);
            loadCourses(); // Refresh list
            tfCourseName.clear();
        }
    }

    @FXML
    private void onDeleteCourseClicked() {
        Courses selectedCourse = lvCourses.getSelectionModel().getSelectedItem();
        if (selectedCourse != null && selectedCourse.getId() != 0) {
            CourseDBHandler.deleteCourse(selectedCourse.getId());
            loadCourses(); // Refresh list
        }
    }

    public void goBack(ActionEvent actionEvent) {

    }
}
