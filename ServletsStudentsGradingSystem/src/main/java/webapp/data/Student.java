package webapp.data;

import java.util.List;

public class Student {
   private int studentId;
   private String studentName;
   private String email;
   private String password;
   private List<CourseInfo> EnrolledCourses;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Student(int studentId, String email, String password, String studentName) {
        this.studentId = studentId;
        this.email = email;
        this.password = password;
        this.studentName = studentName;

    }
    public Student(){}

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
