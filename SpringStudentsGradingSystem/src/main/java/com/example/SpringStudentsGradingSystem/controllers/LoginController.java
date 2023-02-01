package com.example.SpringStudentsGradingSystem.controllers;
import com.example.SpringStudentsGradingSystem.Data.CourseInfo;
import com.example.SpringStudentsGradingSystem.Data.Student;
import com.example.SpringStudentsGradingSystem.Data.StudentDaoUser;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;


@Controller
public class LoginController {
    CoursesService coursesService = new CoursesService();
    Student student;
    StudentDaoUser studentDaoUser = new StudentDaoUser();


    @GetMapping("/")
    public String initiate(){
        return "login";
    }

    @GetMapping("/hello")
    public String display(@RequestParam(value = "selection") int selection, Model model ){

        model.addAttribute("student", student);
        model.addAttribute("greeting", "Hello "+student.getName());
        int studentId = student.getStudentId();
        List<CourseInfo> registeredCourses = coursesService.getStudentInfo(studentId);
        selection--;
        if(selection < 0 || selection >= registeredCourses.size()){
            model.addAttribute("error", "Invalid Selection");
        } else {
            int max = coursesService.getMaxGrade(registeredCourses.get(selection));
            int min = coursesService.getMinGrade(registeredCourses.get(selection));
            double avg = coursesService.getGradesAverage(registeredCourses.get(selection));
            double median = coursesService.getGradesMedian(registeredCourses.get(selection));
            model.addAttribute("max","Max Grade: " + max);
            model.addAttribute("min","Min Grade: " + min);
            model.addAttribute("average","Grades Average: " + avg);
            model.addAttribute("median","Grades Median: " + median);
        }



        return "hello";
    }




    @PostMapping("/hello")
    public String Validate(
            @RequestParam(value="username",  defaultValue = "User") String email,
            @RequestParam(value="password", defaultValue = "") String password, Model model
    ){


        boolean isValidated = studentDaoUser.validateCredentials(email, password);
        int id;
        if(isValidated) {
            id = studentDaoUser.getStudentID(email);
            student = studentDaoUser.findByID(id);
            model.addAttribute("student", student);
            List<CourseInfo> courses = studentDaoUser.getStudentInfo(id);
            model.addAttribute("name", "Hello "+student.getName());
            model.addAttribute("courses", courses);


            return "hello";
        } else {
            model.addAttribute("errorMessage", "Invalid Credentials! ");
            return "login";
        }

    }



}
