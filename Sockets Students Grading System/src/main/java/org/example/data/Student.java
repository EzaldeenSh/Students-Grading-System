package org.example.data;

import java.util.List;

public class Student {
    int studentId;
    String email;
    String password;
    List<CourseInfo> EnrolledCourses;

    public Student(int studentId, String email, String password) {
        this.studentId = studentId;
        this.email = email;
        this.password = password;
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

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
