<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Admission Form</title>
        <link rel="stylesheet" href="style.css">
</head>
<body>
    
    <form action="${pageContext.request.contextPath}/submitAdmission" method="post" enctype="multipart/form-data" class="form-container">
       <h2>Student Admission Form</h2>
        <div class="form-control">
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" required>
        </div>
        <div class="form-control">
            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" required>
        </div>
        <div class="form-control">
            <label for="passportPhoto">Passport Photo (.jpg, .png):</label>
            <input type="file" id="passportPhoto" name="passportPhoto" accept="image/png, image/jpeg" required>
        </div>
        <div class="form-control">
            <label for="diplomaCertificate">Diploma Certificate (PDF only):</label>
            <input type="file" id="diplomaCertificate" name="diplomaCertificate" accept="application/pdf" required>
        </div>
        <button type="submit" class="btn">Submit Admission Form</button>
    </form>
</body>
</html>
