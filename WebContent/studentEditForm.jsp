<%@ page import="web.Model.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Student</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="form-container">
        <form action="updateStudent" method="post" class="my-form" enctype="multipart/form-data">
            <h2>Edit Student</h2>
            <input type="hidden" name="id" value="${student.id}" />
            <div class="form-control">
                <label for="firstName">First Name:</label>
                <input type="text" id="firstName" name="firstName" value="${student.firstName}" />
            </div>
            <div class="form-control">
                <label for="lastName">Last Name:</label>
                <input type="text" id="lastName" name="lastName" value="${student.lastName}" />
            </div>
            <div class="form-control">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${student.email}" />
            </div>
            <div class="form-control">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" value="${student.password}" />
            </div>
           <div class="form-control">
            <label for="passportPhoto">Passport Photo (.jpg, .png):</label>
            <input type="file" id="passportPhoto" name="passportPhoto" accept="image/png, image/jpeg" required>
        </div>
        <div class="form-control">
            <label for="diplomaCertificate">Diploma Certificate (PDF only):</label>
            <input type="file" id="diplomaCertificate" name="diplomaCertificate" accept="application/pdf" required>
        </div>
            <input type="submit" value="Update Student" class="btn" />
        </form>
    </div>
</body>
</html>
