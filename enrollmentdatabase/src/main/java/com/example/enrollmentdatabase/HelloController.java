//package com.example.enrollmentdatabase;
//
//import com.example.enrollmentdatabase.databases.DeleteStudent;
//import com.example.enrollmentdatabase.databases.InsertStudent;
//import com.example.enrollmentdatabase.databases.RetrieveCourses;
//import com.example.enrollmentdatabase.model.Courses;
//import com.example.enrollmentdatabase.model.Student;
//import javafx.collections.FXCollections;
//import javafx.collections.ListChangeListener;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.scene.control.*;
//import javafx.scene.layout.HBox;
//
//import java.util.List;
//
//public class HelloController {
//    public TextField tfName;
//    public ComboBox<String> cbCourse;
//    public ListView<Student> lvList;
//    public Button btnRemove;
//    public Button btnEnroll;
//    public HBox hbProgCont;
//
//    private ObservableList<Student> studentList = FXCollections.observableArrayList();
//
//    public void initialize() {
//        loadCoursesFromDB();
//
//        lvList.setItems(studentList);
//
//        studentList.addListener((ListChangeListener<Student>) change ->
//                System.out.println("Student list updated!")
//        );
//    }
//
//    private void loadCoursesFromDB() {
//        List<Courses> courses = RetrieveCourses.getCourses();
//        cbCourse.getItems().clear();
//        for (Courses c : courses) {
//            cbCourse.getItems().add(c.getCourseName());
//        }
//    }
//
//    public void onEnrollClicked(ActionEvent actionEvent) {
//        String name = tfName.getText();
//        String course = cbCourse.getSelectionModel().getSelectedItem();
//
//        if (name.isEmpty() || course == null) {
//            showAlert("Error", "Name and Course must be filled!");
//            return;
//        }
//
//        int id = InsertStudent.insertStudent(name, course);
//        Student s = new Student(id, name, course);
//
//        studentList.add(s);
//
//        tfName.clear();
//        cbCourse.getSelectionModel().clearSelection();
//    }
//
//    public void onRemoveClicked(ActionEvent actionEvent) {
//        Student s = lvList.getSelectionModel().getSelectedItem();
//        if (s == null) {
//            showAlert("Error", "Select a student to remove!");
//            return;
//        }
//
//        DeleteStudent.deleteStudent(s.getId());
//        studentList.remove(s);
//    }
//
//    private void showAlert(String title, String message) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//
//    public void onEnrollStudentsClicked(ActionEvent actionEvent) {
//    }
//
//    public void onManageCoursesClicked(ActionEvent actionEvent) {
//    }
//}
package com.example.enrollmentdatabase;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import java.io.IOException;

public class HelloController {

    @FXML
    private void onEnrollStudentClicked(ActionEvent event) throws IOException {
        navigateTo(event, "/com/example/enrollmentdatabase/enroll-student.fxml", "Enroll Student");
    }

    @FXML
    private void onManageCoursesClicked(ActionEvent event) throws IOException {
        navigateTo(event, "/com/example/enrollmentdatabase/manage-courses.fxml", "Modify Courses");
    }

    private void navigateTo(ActionEvent event, String fxmlPath, String title) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }
}
