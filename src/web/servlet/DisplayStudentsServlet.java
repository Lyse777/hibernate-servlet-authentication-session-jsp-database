package web.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import web.Model.Student;

@WebServlet(name = "DisplayStudentsServlet", urlPatterns = {"/message"})
public class DisplayStudentsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Student loggedInStudent = (Student) session.getAttribute("student");

        if (loggedInStudent != null) {
          
            request.setAttribute("student", loggedInStudent);
            RequestDispatcher dispatcher = request.getRequestDispatcher("message.jsp");
            dispatcher.forward(request, response);
        } else {
           
            response.sendRedirect("login.html");
        }
    }
}
