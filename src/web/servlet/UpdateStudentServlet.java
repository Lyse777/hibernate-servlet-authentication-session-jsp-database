
package web.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import web.Dao.StudentDao;
import web.Model.Student;

@WebServlet("/updateStudent")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10) // 10 MB max file size
public class UpdateStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            // Retrieving existing photo and certificate paths
            StudentDao studentDao = new StudentDao();
            Student existingStudent = studentDao.findStudentById(id);
            String passportPhotoPath = existingStudent.getPassportPhotoPath();
            String diplomaCertificatePath = existingStudent.getDiplomaCertificatePath();

            Part passportPhotoPart = request.getPart("passportPhoto");
            if (passportPhotoPart != null && passportPhotoPart.getSize() > 0) {
                // Processing passport photo upload
                passportPhotoPath = saveFile(passportPhotoPart);
            }

            Part diplomaCertificatePart = request.getPart("diplomaCertificate");
            if (diplomaCertificatePart != null && diplomaCertificatePart.getSize() > 0) {
                // Processing diploma certificate upload
                diplomaCertificatePath = saveFile(diplomaCertificatePart);
            }

            Student student = new Student(firstName, lastName, email, password, passportPhotoPath, diplomaCertificatePath);
            student.setId(id);

            studentDao.updateStudent(student);

            response.sendRedirect("StudentAdmissionForm.jsp");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("editStudent?id=" + request.getParameter("id"));
        }
    }

    private String saveFile(Part part) throws IOException {
        String fileName = part.getSubmittedFileName();
        String uploadsFolder = "/uploads"; 
        String realUploadsFolder = getServletContext().getRealPath(uploadsFolder) + File.separator + fileName;

        File fileSaveDir = new File(realUploadsFolder);
        if (!fileSaveDir.getParentFile().exists()) {
            fileSaveDir.getParentFile().mkdirs();
        }

        try (InputStream input = part.getInputStream();
             FileOutputStream output = new FileOutputStream(fileSaveDir)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) > 0) {
                output.write(buffer, 0, bytesRead);
            }
        }

        return uploadsFolder + "/" + fileName; 
    }


}
