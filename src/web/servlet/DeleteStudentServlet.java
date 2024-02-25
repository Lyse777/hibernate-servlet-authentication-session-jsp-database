package web.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import web.Dao.StudentDao;

@WebServlet(name = "DeleteStudentServlet", urlPatterns = {"/deleteStudent"})
public class DeleteStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentIdStr = request.getParameter("id");
        if (studentIdStr != null && !studentIdStr.isEmpty()) {
            Long studentId = Long.parseLong(studentIdStr);
            StudentDao studentDao = new StudentDao();
            studentDao.deleteStudent(studentId);
        }
        
       
        response.sendRedirect("login.html");
    }
}
