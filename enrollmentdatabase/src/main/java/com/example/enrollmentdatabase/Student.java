package com.example.enrollmentdatabase;

public class Student {

    static int ID = 232946647;
    int id;
    String name;
    String course;

    public Student(String name, String course) {
        id = ++ID;
        this.name = name;
        this.course = course;
    }

    @Override
    public String toString(){
        return name + " (" + course + ")";
    }
}
