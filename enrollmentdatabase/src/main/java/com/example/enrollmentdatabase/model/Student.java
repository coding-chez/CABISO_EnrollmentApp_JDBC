package com.example.enrollmentdatabase.model;

public class Student {
    private int id;
    private String name;
    private Courses course;

    public Student(int id, String name, Courses course) {
        this.id = id;
        this.name = name;
        this.course = course;
    }

    public Student(String name, Courses course) {
        this.name = name;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return name + " (" + course.getName() + ")";
    }
}
