package web.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import web.Dao.StudentDao;
import web.Model.Student;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        StudentDao studentDao = new StudentDao();
        Student student = studentDao.authenticateStudent(email, password);

        if (student != null) {
          
            HttpSession session = request.getSession(true); 
            session.setAttribute("student", student); 
            
            
            session.setMaxInactiveInterval(30 * 60); // 30 minutes

           
            response.sendRedirect("dashboard.jsp"); 
        } else {
            
            request.setAttribute("errorMessage", "Invalid Email or Password");

          
            request.getRequestDispatcher("login.html").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
        response.sendRedirect("login.html");
    }
}
