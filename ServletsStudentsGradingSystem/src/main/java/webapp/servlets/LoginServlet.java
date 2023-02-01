package webapp.servlets;
import webapp.data.CourseInfo;
import webapp.data.Student;
import webapp.services.StudentService;
import webapp.services.CoursesService;
import webapp.services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/login.do")
public class LoginServlet extends HttpServlet {
	private final LoginService loginService = new LoginService();
	private final StudentService studentService = new StudentService();
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(
				request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		boolean isValidUser = loginService.validateUser(username, password);

		if (isValidUser) {
			CoursesService coursesService = new CoursesService();
			HttpSession session = request.getSession(true);


			int id = studentService.getStudentId(username);
			Student student = studentService.getStudentInfo(id);
			
			request.setAttribute("username", student.getStudentName());
			List<CourseInfo> courses = coursesService.getStudentInfo(student.getStudentId());
			session.setAttribute("studentCourses" , courses);



			session.setAttribute("student", student);

			request.getRequestDispatcher("/WEB-INF/views/welcome.jsp").forward(
					request, response);
		} else {
			request.setAttribute("errorMessage", "Invalid Credentials!");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(
					request, response);
		}
	}

}