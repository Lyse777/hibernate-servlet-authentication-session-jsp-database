<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="web.Model.Student" %>
<%
    Student student = (Student) session.getAttribute("student");
    if (student == null) {
        response.sendRedirect("login.html");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="form-container">
    <h2>Welcome, <%= student.getEmail() %>!</h2>
    <a href="StudentAdmissionForm.jsp" class="btn" style="text-decoration:none;">Fill in Admission Form</a>
    <a href="logout" class="btn" style="text-decoration:none; background-color:brown;">Logout</a>
</div>
</body>
</html>
