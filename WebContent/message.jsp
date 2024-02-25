<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="web.Model.Student" %>
<!DOCTYPE html>
<html>
<head>
    <title>Submission Confirmation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-image: url('24.jpg');
            background-size: cover;
            background-position: center;
            margin: 0;
            padding: 20px;
               height: 100%;
            color: #333;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #fff; 
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: rgba(255, 255, 255, 0.8); 
            border-radius: 10px;
            overflow: hidden; 
        }

        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }

        img {
            max-width: 200px;
            max-height: 200px;
            
            margin: 0 auto;
        }

        .btn1, .btn2 {
            padding: 10px 20px;
            color: #fff;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            text-decoration: none;
            display: inline-block;
            margin-right: 10px;
            transition: background-color 0.3s;
        }

        .btn1 {
            background-color: #D31919;
        }

        .btn1:hover {
            background-color: #D73C3C;
        }

        .btn2 {
            background-color: #AA21C0;
        }

        .btn2:hover {
            background-color: #AC78B4;
        }

        .message {
            margin-top: 20px;
            background-color: rgba(255, 255, 255, 0.8);
            padding: 20px;
            border-radius: 10px;
        }

        .error {
            color: #D31919;
            font-weight: bold;
        }

        .success {
            color: #008000;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h2>Submission Confirmation</h2>
    <div class="message <%= request.getAttribute("error") != null ? "error" : "success" %>">
        <%= request.getAttribute("message") %>
    </div>

    <div class="student-info">
        <% 
            Student student = (Student) request.getAttribute("student");
            if (student != null) {
        %>
            <table>
            <tr>
                <th>ID</th>
                <td><%= student.getId() %></td>
            </tr>
            <tr>
                <th>First Name</th>
                <td><%= student.getFirstName() %></td>
            </tr>
            <tr>
                <th>Last Name</th>
                <td><%= student.getLastName() %></td>
            </tr>
            <tr>
                <th>Email</th>
                <td><%= student.getEmail() %></td>
            </tr>
            <tr>
                <th>Password</th>
                      <td><%= student.getPassword() %></td>
            </tr>
                          <tr>
               <th>Passport Photo</th>
<td>
   <% if(student.getPassportPhotoPath() != null && !student.getPassportPhotoPath().isEmpty()) { %>
            <img src="<%=request.getContextPath()%>/<%= student.getPassportPhotoPath() %>" alt="Passport Photo">
        <% } %>
        </td>
    </tr>
               <tr>
                <th>Diploma Certificate</th>
                <td>
       <% if(student.getDiplomaCertificatePath() != null && !student.getDiplomaCertificatePath().isEmpty()) { %>
            <a href="<%=request.getContextPath()%>/<%= student.getDiplomaCertificatePath() %>" target="_blank">View Diploma Certificate</a>
        <% } %>
                </td>
            </tr>
           </table>
            <div class="actions">
                <a href="editStudent?id=<%= student.getId() %>" class="btn2">Update</a>
                <a href="deleteStudent?id=${student.id}" class="btn1" onclick="return confirm('Are You Sure You Want To Delete This Account?');">Delete</a>
            </div>
        <% } else { %>
            <p>Student information is not available. Please log in again.</p>
        <% } %>
    </div>
</body>
</html>