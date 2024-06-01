package core.domain.email;

import infrastructure.AppSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.*;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailHandler.class);
    private final Session session;
    private final String username;

    /**
     * Instantiates a new Email handler.
     *
     * @throws IOException the io exception
     */
    public EmailHandler() throws IOException {
        Properties appProperties = new Properties();
        String propFileName = "application.properties";

        InputStream inptStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        if (inptStream != null) {
            appProperties.load(inptStream);
        } else {
            throw new FileNotFoundException("Application property does not exist");
        }

        inptStream.close();

        username = appProperties.getProperty("email.from", "sem4pi2dd5@outlook.pt");
        final String password = appProperties.getProperty("email.password", "grupo2dd5#");
        String host = appProperties.getProperty("email.host", "smtp.office365.com");
        String port = appProperties.getProperty("email.port", "587");

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.office365.com");

        session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

    }

    /**
     * Send email boolean.
     *
     * @param dest    the dest
     * @param subject the subject
     * @param content the content
     * @return the boolean
     */
    public boolean sendEmail(String dest, String subject, String content) {
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(username));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(dest));
            msg.setSubject(subject);
            msg.setText(content);
            Transport.send(msg);
            return true;
        } catch (MessagingException e) {
            LOGGER.error("Error sending email: {}", e.getMessage());
            return false;
        }

    }
}
