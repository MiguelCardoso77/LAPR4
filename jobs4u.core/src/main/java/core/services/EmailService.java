package core.services;

import core.domain.email.Email;
import core.domain.email.EmailHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {

    public Email createEmail(String candidateEmail, String subject, String body) {
        return new Email(candidateEmail, subject, body);
    }

    public void sendEmailOnline(String dest, String subject, String body) {
        try {

            EmailHandler emailHandler = new EmailHandler();
            boolean flag = emailHandler.sendEmail(dest, subject, body);
            if (flag) {
                System.out.println("Email sent!");
            } else {
                System.out.println("Error sending email!");
            }

        } catch (IOException e) {
            System.out.println("Error sending email: " + e.getMessage());
        }
    }
}
