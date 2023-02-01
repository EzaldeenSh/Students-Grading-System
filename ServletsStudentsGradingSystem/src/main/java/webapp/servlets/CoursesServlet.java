package webapp.servlets;

import webapp.data.CourseInfo;
import webapp.data.Student;
import webapp.services.StudentService;
import webapp.services.CoursesService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/welcome.do")
public class CoursesServlet extends HttpServlet {
    CoursesService coursesService = new CoursesService();
    StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Student student = (Student)session.getAttribute("student");
        String studentName = student.getStudentName();
        int selection = Integer.parseInt(request.getParameter("selection"));
        List<CourseInfo> studentCourses = (List<CourseInfo>) session.getAttribute("studentCourses");
        selection--;
        if(selection < 0 || selection >= studentCourses.size()){
            request.setAttribute("errorMessage", "Invalid Selection!");
        } else {
            CourseInfo selectedCourse = studentCourses.get(selection);

            request.setAttribute("max","Max Grade: " + coursesService.getMaxGrade(selectedCourse));
            request.setAttribute("min", "Mix Grade: " +coursesService.getMinGrade(selectedCourse));
            request.setAttribute("average","Grades Average: " + coursesService.getGradesAverage(selectedCourse));
            request.setAttribute("median", "Grades Median: " + coursesService.getGradesMedian(selectedCourse));

        }

        request.setAttribute("username", studentName);
        request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(
                request, response);


    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Student student = (Student)session.getAttribute("student");
        String studentName = student.getStudentName();



        request.setAttribute("username", studentName);
        request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(
                request, response);
    }
}
