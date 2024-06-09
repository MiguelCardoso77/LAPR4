package core.domain.email;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.*;

/**
 * Handles the sending of emails.
 * This class provides methods for sending emails with a specified recipient, subject, and body.
 *
 * @author Miguel Cardoso
 */
public class EmailHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailHandler.class);
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

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    /**
     * Send email boolean.
     *
     * @param toWho   the dest
     * @param subject the subject
     * @param body    the body
     * @return the boolean
     */
    public boolean sendEmail(String toWho, String subject, String body) {
        try {
            Email email = new SimpleEmail();
            email.setHostName("frodo.dei.isep.ipp.pt");
            email.setSmtpPort(25);
            email.setSSLOnConnect(false);
            email.setFrom("sem4pi2dd5@outlook.pt", "Jobs4U Customer Manager");
            email.setSubject(subject);
            email.setMsg(body);
            email.addTo(toWho);
            email.send();

            return true;
        } catch (EmailException e) {
            LOGGER.error("Error sending email", e);
            throw new RuntimeException(e);
        }

    }
}
