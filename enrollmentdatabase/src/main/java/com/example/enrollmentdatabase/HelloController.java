package com.example.enrollmentdatabase;

import com.example.enrollmentdatabase.model.Student;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class HelloController {
    public TextField tfNumber;
    public TextField tfThread;
    public ListView<Student> lvList;
    public HBox hbProgCont;
    public TextField tfName;
    public Button btnRemove;
    public Button btnEnroll;
    public ComboBox<String> cbCourse;
    public TextField tfId;

    public void initialize(){
        cbCourse.getItems().add("BSCS");
        cbCourse.getItems().add("BSIT");
        cbCourse.getItems().add("BSN");
        cbCourse.getItems().add("BSIE");
        cbCourse.getItems().add("BSCpE");
        cbCourse.getItems().add("BSCE");
        lvList.getItems().addListener(new ListChangeListener<Student>() {
            @Override
            public void onChanged(Change<? extends Student> change) {
                System.out.println("hello, welcome!");
            }
        });
//      lvList.getItems().addListener(new
//        lvList.addEventHandler(new EventType<>(), new EventHandler<Event>() {
//            @Override
//            public void handle(Event event) {
//                System.out.println("hello");
//            }
//        }
    }
    public void onRemoveClicked(ActionEvent actionEvent) {
        Student s = lvList.getSelectionModel().getSelectedItem();
        lvList.getItems().remove(s);
    }

    public void onEnrollClicked(ActionEvent actionEvent) {
        String name = tfName.getText();
        String course = cbCourse.getSelectionModel().getSelectedItem();
        Student s = new Student(name,course);
        lvList.getItems().add(s);
        tfName.setText("");
        cbCourse.getSelectionModel().select(0);
//        cbCourse.setPromptText("Select Course");
    }
}



