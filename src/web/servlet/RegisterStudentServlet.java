package web.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import web.Dao.StudentDao;
import web.Model.Student;

@WebServlet(name = "RegisterStudentServlet", urlPatterns = {"/registerStudent"})
public class RegisterStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("error", "Email and password are required!");
            request.getRequestDispatcher("signup.html").forward(request, response); 
            return;
        }

        StudentDao studentDao = new StudentDao();
 
        if (studentDao.emailExists(email)) {
            request.setAttribute("error", "Email already exists. Please use a different email.");
            request.getRequestDispatcher("signup.html").forward(request, response);
            return;
        }

        Student student = new Student();
        student.setEmail(email);
        student.setPassword(password);
        
        studentDao.registerStudent(student);

        response.sendRedirect("login.html");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        response.sendRedirect("signup.html");
    }
}
