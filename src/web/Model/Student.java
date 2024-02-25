package web.Model;

import javax.persistence.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

@Entity
@Table(name = "students") 
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true) 
    private String email;

    @Column(name = "password")
    private String password;

    
    @Column(name = "passport_photo_path")
    private String passportPhotoPath;

    @Column(name = "diploma_certificate_path")
    private String diplomaCertificatePath;

    public Student() {
        
    }

   
    public Student(String firstName, String lastName, String email, String password, String passportPhotoPath, String diplomaCertificatePath) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.passportPhotoPath = passportPhotoPath;
        this.diplomaCertificatePath = diplomaCertificatePath;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPassportPhotoPath() { return passportPhotoPath; }
    public void setPassportPhotoPath(String passportPhotoPath) { this.passportPhotoPath = passportPhotoPath; }

    public String getDiplomaCertificatePath() { return diplomaCertificatePath; }
    public void setDiplomaCertificatePath(String diplomaCertificatePath) { this.diplomaCertificatePath = diplomaCertificatePath; }

    public String getPassportPhotoBase64() {
        if (passportPhotoPath != null) {
            try {
                File file = new File(passportPhotoPath);
                FileInputStream fis = new FileInputStream(file);
                byte[] photoBytes = new byte[(int) file.length()];
                fis.read(photoBytes);
                fis.close();
                return Base64.getEncoder().encodeToString(photoBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public String getPassportPhotoType() {
        if (passportPhotoPath != null) {
            String[] parts = passportPhotoPath.split("\\.");
            if (parts.length > 1) {
                return parts[parts.length - 1].toLowerCase(); 
            }
        }
        return null;
    }

    public String getDiplomaCertificateBase64() {
        if (diplomaCertificatePath != null) {
            try {
                File file = new File(diplomaCertificatePath);
                FileInputStream fis = new FileInputStream(file);
                byte[] documentBytes = new byte[(int) file.length()];
                fis.read(documentBytes);
                fis.close();
                return Base64.getEncoder().encodeToString(documentBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
