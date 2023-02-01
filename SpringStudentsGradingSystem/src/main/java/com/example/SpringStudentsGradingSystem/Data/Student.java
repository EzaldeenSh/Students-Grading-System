package com.example.SpringStudentsGradingSystem.Data;

import java.util.List;

public class Student {
    int studentId;
    String email;
    String password;
    String name;
    List<CourseInfo> EnrolledCourses;

    public Student(int studentId, String email, String password, String name) {
        this.studentId = studentId;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public int getStudentId() {
        return studentId;
    }


    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
