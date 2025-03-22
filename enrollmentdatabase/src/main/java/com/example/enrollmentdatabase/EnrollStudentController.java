//package com.example.enrollmentdatabase;
//
//import com.example.enrollmentdatabase.databases.CourseDBHandler;
//import com.example.enrollmentdatabase.databases.StudentDBHandler;
//import com.example.enrollmentdatabase.model.Courses;
//import com.example.enrollmentdatabase.model.Student;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.util.List;
//
//public class EnrollStudentController {
//
//    @FXML private TextField tfName;
//    @FXML private ComboBox<Courses> cbCourse;
//    @FXML private Button btnEnroll, btnUpdate, btnRemove;
//    @FXML private ListView<Student> lvStudents;
//
//    private ObservableList<Student> studentList;
//    private Student selectedStudent;
//
//    @FXML
//    public void initialize() {
//        loadCourses();
//        loadStudents();
//
//        lvStudents.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//            if (newSelection != null) {
//                selectedStudent = newSelection;
//                tfName.setText(selectedStudent.getName());
//                cbCourse.setValue(new Courses(selectedStudent.getCourse()));
//                btnUpdate.setDisable(false);
//            }
//        });
//    }
//
//    private void loadCourses() {
//        List<Courses> courses = CourseDBHandler.getAllCourses();
//        cbCourse.setItems(FXCollections.observableArrayList(courses));
//    }
//
//    private void loadStudents() {
//        studentList = FXCollections.observableArrayList(StudentDBHandler.getAllStudents());
//        lvStudents.setItems(studentList);
//    }
//
//    @FXML
//    private void onEnrollClicked() {
//        String name = tfName.getText();
//        Courses selectedCourse = cbCourse.getValue();
//
//        if (name.isEmpty() || selectedCourse == null) {
//            showAlert("Error", "Please enter a name and select a course.");
//            return;
//        }
//
//        Student newStudent = new Student(name, selectedCourse.getName());
//        StudentDBHandler.insertStudent(newStudent);
//        loadStudents();
//        clearFields();
//    }
//
//    @FXML
//    private void onUpdateClicked() {
//        if (selectedStudent == null) return;
//
//        selectedStudent.setName(tfName.getText());
//        selectedStudent.setCourse(cbCourse.getValue().getName());
//
//        StudentDBHandler.updateStudent(selectedStudent);
//        loadStudents();
//        clearFields();
//    }
//
//
//    private void clearFields() {
//        tfName.clear();
//        cbCourse.setValue(null);
//        selectedStudent = null;
//        btnUpdate.setDisable(true);
//    }
//
//    private void showAlert(String title, String message) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle(title);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//
//    @FXML
//    private void onRemoveClicked() {
//        if (selectedStudent == null) {
//            showAlert("Error", "Select a student to remove.");
//            return;
//        }
//
//        StudentDBHandler.deleteStudent(selectedStudent.getId());
//        loadStudents();
//        clearFields();
//    }
//
//    public void goBack(ActionEvent actionEvent) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
//            Parent root = loader.load();
//            Stage stage = (Stage) btnEnroll.getScene().getWindow();
//            stage.setScene(new Scene(root));
//            stage.setTitle("Home");
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
package com.example.enrollmentdatabase;

import com.example.enrollmentdatabase.databases.CourseDBHandler;
import com.example.enrollmentdatabase.databases.StudentDBHandler;
import com.example.enrollmentdatabase.model.Courses;
import com.example.enrollmentdatabase.model.Student;
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

public class EnrollStudentController {

    @FXML
    private TextField tfName;

    @FXML
    private ComboBox<Courses> cbCourse;

    @FXML
    private ListView<Student> lvStudents;

    @FXML
    private Button btnBack, btnUpdate, btnRemove, btnLoadStudents;

    private ObservableList<Student> studentList;

    @FXML
    public void initialize() {
        loadCourses();
        loadStudents();

        studentList = FXCollections.observableArrayList();
        lvStudents.setItems(studentList);

        btnUpdate.setDisable(true);
        btnRemove.setDisable(true);

        lvStudents.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            boolean isStudentSelected = newSelection != null;
            btnUpdate.setDisable(!isStudentSelected);
            btnRemove.setDisable(!isStudentSelected);
        });

        System.out.println("Initialization complete. ListView connected.");
    }

    private void loadCourses() {
        List<Courses> courses = CourseDBHandler.getAllCourses();
        ObservableList<Courses> courseOptions = FXCollections.observableArrayList(courses);

        cbCourse.setItems(courseOptions);
        cbCourse.getSelectionModel().selectFirst();
    }

    private void loadStudents() {
        List<Student> students = StudentDBHandler.getAllStudents();
        studentList = FXCollections.observableArrayList(students);
        updateStudentListView();
    }

    private void updateStudentListView() {
        lvStudents.setItems(studentList);
        lvStudents.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Student student, boolean empty) {
                super.updateItem(student, empty);
                if (empty || student == null) {
                    setText(null);
                } else {
                    setText(student.getName() + " - " + student.getCourse().getName());
                }
            }
        });
    }

    @FXML
    public void onLoadStudentsClicked(ActionEvent actionEvent) {
        loadStudents();
        System.out.println("Student list reloaded.");
    }

    @FXML
    public void onEnrollClicked(ActionEvent actionEvent) {
        try {
            String name = tfName.getText().trim();
            Courses selectedCourse = cbCourse.getSelectionModel().getSelectedItem();

            if (name.isEmpty() || selectedCourse == null) {
                showAlert("Invalid Input", "Please enter a valid name and select a course.");
                return;
            }

            System.out.println("Attempting to enroll: " + name + " in " + selectedCourse.getName());

            StudentDBHandler.addStudent(name, selectedCourse.getName());

            System.out.println("Enrollment successful!");
            loadStudents();

            tfName.clear();
            cbCourse.getSelectionModel().selectFirst();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Enrollment Error", "An error occurred: " + e.getMessage());
        }
    }

    @FXML
    public void onUpdateClicked(ActionEvent actionEvent) {
        Student selectedStudent = lvStudents.getSelectionModel().getSelectedItem();
        Courses selectedCourse = cbCourse.getSelectionModel().getSelectedItem();

        if (selectedStudent == null || selectedCourse == null || selectedCourse.getId() == 0) {
            showAlert("Invalid Selection", "Please select a valid student and course.");
            return;
        }

        StudentDBHandler.updateStudentCourse(selectedStudent.getId(), selectedCourse.getId());
        loadStudents();
    }

    @FXML
    public void onRemoveClicked(ActionEvent actionEvent) {
        Student selectedStudent = lvStudents.getSelectionModel().getSelectedItem();

        if (selectedStudent == null) {
            showAlert("Selection Required", "Please select a student to remove.");
            return;
        }

        StudentDBHandler.deleteStudent(selectedStudent.getId());
        loadStudents();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Navigation Error", "Unable to go back to the home screen.");
        }
    }
}
