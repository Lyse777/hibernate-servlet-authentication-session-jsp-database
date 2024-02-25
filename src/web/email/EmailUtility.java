package web.email;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailUtility {

    public static void sendEmail(String to, String subject, String content) throws MessagingException {
        final String fromEmail = "umuhirelise22@gmail.com"; 
        final String password = "swkrwphcllmigsmp"; 
        String host = "smtp.gmail.com";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587"); 
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); 
        properties.put("mail.smtp.debug", "true");
        System.setProperty("https.protocols", "TLSv1.2");

     
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
            throw new MessagingException("Error while sending email: " + mex.getMessage());
        }
    }
}
