package com.example.SpringStudentsGradingSystem.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoUser implements StudentDao{
    @Override
    public boolean validateCredentials(String username, String password) {

        String sql = "select * from users where EMAIL = \'" + username +"\'" ;
        try{
            ResultSet resultSet = statement.executeQuery(sql);
            if (!resultSet.next()){
                return  false;
            } else {
                return resultSet.getString(3).equals(password);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<CourseInfo> getStudentInfo(int studentId) {
        List<CourseInfo> courses = new ArrayList<>();
        String sql = "select * from sub_marks where STUDENT_ID = " + studentId;
        try{
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                CourseInfo courseInfo = new CourseInfo(resultSet.getString(2), resultSet.getInt(3));
                courses.add(courseInfo);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }



        return courses;
    }

    @Override
    public int getStudentID(String email) {
        String sql = "select * from users where EMAIL = \'" +email+"\'";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                return resultSet.getInt(1);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<CourseInfo> getCourseInfo(CourseInfo courseInfo) {
        List<CourseInfo> courseInfoList = new ArrayList<>();
        String sql ="select * from sub_marks where subject = \"" + courseInfo.getCourseName()+"\" order by marks";
        try{
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                courseInfoList.add(new CourseInfo(resultSet.getString(2),resultSet.getInt(3)));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }




        return courseInfoList;
    }

    @Override
    public int getMaxGrade(CourseInfo course) {
        int maxGrade = 0;
        String sql = "select max(marks) from sub_marks where subject = \'" + course.getCourseName() + "\'";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                maxGrade = resultSet.getInt(1);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return maxGrade;
    }

    @Override
    public int getMinGrade(CourseInfo course) {
        int minGrade = 0;
        String sql = "select min(marks) from sub_marks where subject = \'" + course.getCourseName() + "\'";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                minGrade = resultSet.getInt(1);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return minGrade;
    }

    @Override
    public double getGradesAverage(CourseInfo course) {
        double gradesAverage = 0;
        String sql = "select avg(marks) from sub_marks where subject = \'" + course.getCourseName() + "\'";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                gradesAverage = resultSet.getDouble(1);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return gradesAverage;
    }

    private Connection connection;
    private Statement statement;
    private List<Student> students;

    public StudentDaoUser() {
        students = new ArrayList<>();
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentsgrades","root","3Ezzo29438167;" );
            statement= connection.createStatement();
            System.out.println("Connected");

        }catch(SQLException e){
            System.out.println("Connection problem");
        }
    }

    @Override
    public List<Student> findAll() {
        students.clear();
        String sql = "select * from users";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                students.add(new Student(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public Student findByID(int id) {
        Student s = null;
        String sql = "select * from users where STUDENT_ID = " + id;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                s = new Student(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
            }
        } catch (SQLException e){e.printStackTrace();}
        return s;
    }





    @Override
    public boolean insertStudent(Student student) {

        return false;
    }

    @Override
    public boolean updateStudent(Student student) {
        return false;
    }

    @Override
    public boolean deleteStudent(Student student) {
        return false;
    }
}
