package com.example.enrollmentdatabase.model;

public class Courses {
    private int id;
    private String name;

    // Constructor for fetching from database
    public Courses(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Constructor for inserting a new course (without ID)
    public Courses(String name) {
        this.name = name;
    }

    // Getters and Setters
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

    @Override
    public String toString() {
        return name;
    }
}
