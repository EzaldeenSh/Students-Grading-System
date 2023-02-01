package webapp.services;

import webapp.data.CourseInfo;
import webapp.data.StudentDaoUser;

import java.util.ArrayList;
import java.util.List;

public class CoursesService {
    private final StudentDaoUser studentDaoUser = new StudentDaoUser();
    private List<CourseInfo> coursesInfo = new ArrayList<>();
    public List<CourseInfo> getStudentInfo(int studentId){
        coursesInfo = studentDaoUser.getStudentInfo(studentId);
        return coursesInfo;
    }
    public int getMaxGrade(CourseInfo courseInfo){
        return studentDaoUser.getMaxGrade(courseInfo);

    }
    private List<CourseInfo> getAllGrades(CourseInfo courseInfo){
        return studentDaoUser.getCourseInfo(courseInfo);

    }
    public int getMinGrade(CourseInfo courseInfo){
        return studentDaoUser.getMinGrade(courseInfo);
    }
    public double getGradesAverage(CourseInfo courseInfo){
        return studentDaoUser.getGradesAverage(courseInfo);
    }
    public double getGradesMedian(CourseInfo courseInfo){
       return calculateMedian( getAllGrades(courseInfo));
    }
    private double calculateMedian(List<CourseInfo> coursesInfo){
        if(coursesInfo.size()%2==1)
            return coursesInfo.get(coursesInfo.size()/2).getCourseGrade();
         else {
            double first = coursesInfo.get(coursesInfo.size()/2).getCourseGrade();
            double second = coursesInfo.get(coursesInfo.size()/2 - 1).getCourseGrade();

            double median = (first + second) / 2;
            return median;
         }
    }
}
