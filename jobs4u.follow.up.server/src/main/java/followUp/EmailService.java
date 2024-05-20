package followUp;

import eapli.framework.infrastructure.authz.application.Authenticator;
import org.apache.logging.log4j.message.Message;
import org.hibernate.Session;

import java.net.PasswordAuthentication;
import java.util.Properties;

public class EmailService {
    private static final String SMTP_HOST = "smtp.example.com";
    private static final String SMTP_PORT = "587";
    private static final String SMTP_USERNAME = "your-email@example.com";
    private static final String SMTP_PASSWORD = "your-email-password";

    public void sendVerificationEmail(String to, String name, String result) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
/**
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SMTP_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Verification Result");
            message.setText(String.format("Dear %s,\n\nYour verification result: %s\n\nBest regards,\nCompany", name, result));

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
 */
    }
}