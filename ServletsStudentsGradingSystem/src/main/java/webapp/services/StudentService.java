package webapp.services;

import webapp.data.Student;
import webapp.data.StudentDaoUser;

public class StudentService {
    private StudentDaoUser studentDaoUser = new StudentDaoUser();
    public int getStudentId(String email){
    return studentDaoUser.getStudentID(email);
    }
    public Student getStudentInfo(int studentId){
        return studentDaoUser.findByID(studentId);
    }
}
