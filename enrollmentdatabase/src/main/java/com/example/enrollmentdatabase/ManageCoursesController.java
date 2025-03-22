package com.example.enrollmentdatabase;

import com.example.enrollmentdatabase.databases.CourseDBHandler;
import com.example.enrollmentdatabase.model.Courses;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ManageCoursesController {

    @FXML
    private TextField tfCourseName;

    @FXML
    private ListView<Courses> lvCourses;

    @FXML
    private Button btnDelete, btnBack;

    @FXML
    public void initialize() {
        loadCourses();
        btnDelete.setDisable(true);

        lvCourses.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            btnDelete.setDisable(newSelection == null || newSelection.getId() == 0);
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
            loadCourses();
            tfCourseName.clear();
        } else {
            showAlert("Input Required", "Please enter a course name.");
        }
    }

    @FXML
    private void onDeleteCourseClicked() {
        Courses selectedCourse = lvCourses.getSelectionModel().getSelectedItem();
        if (selectedCourse != null && selectedCourse.getId() != 0) {
            CourseDBHandler.deleteCourse(selectedCourse.getId());
            loadCourses();
        } else {
            showAlert("Invalid Selection", "Cannot delete the default 'Select Course' option.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void goBack(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/enrollmentdatabase/hello-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Enrollment System");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Navigation Error", "Unable to go back to the home screen.");
        }
    }


}
