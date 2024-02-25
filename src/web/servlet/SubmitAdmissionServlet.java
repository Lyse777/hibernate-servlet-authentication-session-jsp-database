package web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import web.Dao.StudentDao;
import web.Model.Student;
import web.email.EmailUtility;

@WebServlet("/submitAdmission")
public class SubmitAdmissionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIRECTORY = "uploads";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!ServletFileUpload.isMultipartContent(request)) {
            request.setAttribute("message", "Form must have enctype=multipart/form-data.");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession(false);
        Student student = (Student) session.getAttribute("student");
        
        if (student == null) {
            
            response.sendRedirect("login.html");
            return;
        }

        if (student.getPassportPhotoPath() != null || student.getDiplomaCertificatePath() != null) {
            request.setAttribute("message", "You have already submitted the form.");
            request.setAttribute("student", student);
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        try {
            List<FileItem> formItems = upload.parseRequest(request);
            for (FileItem item : formItems) {
                if (!item.isFormField()) {
                    String fieldName = item.getFieldName();
                    String fileName = new File(item.getName()).getName();
                    String filePath = uploadPath + File.separator + fileName;
                    File storeFile = new File(filePath);
                    item.write(storeFile);

                    if ("passportPhoto".equals(fieldName)) {
                        student.setPassportPhotoPath(UPLOAD_DIRECTORY + "/" + fileName);
                    } else if ("diplomaCertificate".equals(fieldName)) {
                        student.setDiplomaCertificatePath(UPLOAD_DIRECTORY + "/" + fileName);
                    }
                } else {
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString();
                    if ("firstName".equals(fieldName)) {
                        student.setFirstName(fieldValue);
                    } else if ("lastName".equals(fieldName)) {
                        student.setLastName(fieldValue);
                    }
                    
                }
            }

            StudentDao studentDao = new StudentDao();
            studentDao.updateStudent(student);

            // Sending confirmation email.
            String subject = "Admission Form Submission Confirmation";
            String content = "Dear " + student.getFirstName() + ",\n\nYour admission form has been successfully submitted.\n\nBest Regards,\nAdmissions Team";
            EmailUtility.sendEmail(student.getEmail(), subject, content);

            request.setAttribute("student", student);
            request.setAttribute("message", "Admission form submitted successfully! A confirmation email has been sent to " + student.getEmail() + ".");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error: " + ex.getMessage());
            request.getRequestDispatcher("/message.jsp").forward(request, response);
        }
    }
}
