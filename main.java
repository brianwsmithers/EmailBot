/**
 *  This email bot uses javax.mail.jar and the Java utility library to send e-mails with GMAIL.
 *  To use the program for your e-mail please install the java.mail.jar and library follow
 *  the instructions.
 * 
 *  @author Brian Smithers
 *  @version 1.0
 *  @since October 11, 2020 
 *
 *  Change the following values...
 *
 *  1. String 'from' to your email address.
 *  2. String 'password' to your email account password.
 *  3. String 'to' to the receiver of your email.
 *  4. String 'setSubject' to the subject of your message.
 *  5. String 'setMessage' to the body of your message.
 *
 *  Please note, you will need to use a developer password to access your GMAIL account if
 *  you have multi-factor authentication activated. You can find out how to do this through
 *  the google website. It will provide you with a unique password that does not require
 *  multi-factor authentication. DO NOT share the password with anyone as it is a less
 *  secure method of accessing your email account.
 *
 */


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {

        String from = "email@gmail.com";  // Your email...
        String password = "password";     // Your password...
        String to = "receivers@mail.com"; // The receiver's email...

        // Subject
        String setSubject = "Subject goes here...";
        // Body Message
        String setMessage = "Message goes here...";

        emailMethod(from, password, to, setMessage, setSubject);

    }

    static void emailMethod(String from, String password, String to, String setMessage,
                            String setSubject) {

        // Assuming you are sending email from GMAIL's SMTP
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session;
        session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return  new PasswordAuthentication(from, password);
            }
        });

        // used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: Header field
            message.setSubject(setSubject);

            // Now set the actual message
            message.setText(setMessage);

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully...");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
