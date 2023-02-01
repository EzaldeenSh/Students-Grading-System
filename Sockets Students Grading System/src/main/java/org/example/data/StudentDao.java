package org.example.data;

import java.util.List;

public interface StudentDao {
    List<Student> findAll();
    Student findByID(int StudentID);

    boolean insertStudent(Student student);
    boolean updateStudent(Student student);
    boolean deleteStudent(Student student);
    boolean validateCredentials(String username, String password);
    List<CourseInfo> getStudentInfo(int studentId);
    int getStudentID(String email);
    List<CourseInfo> getCourseInfo(CourseInfo courseInfo);
    int getMaxGrade(CourseInfo course);
    int getMinGrade(CourseInfo course);
    double getGradesAverage(CourseInfo course);

}