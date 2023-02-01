package webapp.data;

import java.util.List;

public interface StudentDao {
    List<Student> findAll();
    Student findByID(int StudentID);

    boolean validateCredentials(String username, String password);
    List<CourseInfo> getStudentInfo(int studentId);
    int getStudentID(String email);
    List<CourseInfo> getCourseInfo(CourseInfo courseInfo);
    int getMaxGrade(CourseInfo course);
    int getMinGrade(CourseInfo course);
    double getGradesAverage(CourseInfo course);


}
