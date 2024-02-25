package web.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import web.Dao.StudentDao;
import web.Model.Student;

@WebServlet("/editStudent")
public class EditStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.trim().isEmpty()) {
            Long id = Long.parseLong(idStr);
            StudentDao studentDao = new StudentDao();
            Student student = studentDao.findStudentById(id);
            if (student != null) {
                request.setAttribute("student", student);
                RequestDispatcher dispatcher = request.getRequestDispatcher("studentEditForm.jsp");
                dispatcher.forward(request, response);
                return;
            }
        }
        response.sendRedirect("StudentAdmissionForm.jsp");
    }
}
