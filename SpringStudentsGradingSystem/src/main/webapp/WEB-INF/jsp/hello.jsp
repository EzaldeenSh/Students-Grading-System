<%@ page import="java.util.List" %>
<%@ page import="com.example.SpringStudentsGradingSystem.Data.CourseInfo" %>

<html lang="en" >
<head>
    <meta charset="UTF-8">
    <title>Hello</title>
</head>
<body>
<h2>${greeting}</h2>
<h3>Your courses are: </h3>
<%

List<CourseInfo> courses = (List<CourseInfo>) request.getAttribute("courses");
if(courses != null){
session.setAttribute("courses", courses);
}
courses = (List<CourseInfo>) session.getAttribute("courses");

int counter = 1;
for(CourseInfo c : courses){

out.println(counter +"- "+ c.getCourseName() +": "+c.getCourseGrade()+ "<br>");
counter++;
}
out.println("Please select a course to display info!");
%>
<p><font color="red">${error}</font></p>
    <form action="/hello" method="GET">
    <input type = "number" min = "1" max = <%=courses.size()%> name = "selection" value = "1">
    <input type = "submit">
    </form>


<p>${max}</p>
<p>${min}</p>
<p>${average}</p>
<p>${median}</p>


</body>
</html>