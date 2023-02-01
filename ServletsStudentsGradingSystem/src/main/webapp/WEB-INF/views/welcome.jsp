<%@ page import="java.util.List" %>
<%@ page import="webapp.data.CourseInfo" %>
<html>
<head>
<title>Courses</title>
</head>
<body>

welcome ${username}<br />
<h3>Your courses are: </h3>
<%
List<CourseInfo> courses = (List<CourseInfo>) session.getAttribute("studentCourses");

%>
<%
int counter = 1;
for(CourseInfo c : courses){

out.println(counter +"- "+ c.getCourseName() +": "+c.getCourseGrade()+ "<br>");
counter++;
}

%>
<p><font color="red">${errorMessage}</font></p>
    <form action="/welcome.do" method="GET">
    <input type = "number" min = "1" max = "5" name = "selection" value = "1">
    <input type = "submit">
    </form>


<p>${max}</p>
<p>${min}</p>
<p>${average}</p>
<p>${median}</p>

</body>
</html>