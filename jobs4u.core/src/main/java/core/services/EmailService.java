package core.services;

import core.domain.email.Email;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

@Service
public class EmailService {
    private Socket sock;
    private ObjectOutputStream oos;

    public EmailService() {
        try {
            this.sock = new Socket("127.0.0.1", 1010);
            this.oos = new ObjectOutputStream(sock.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error initializing socket or output stream: " + e.getMessage());
        }
    }

    public Email createEmail(String loggedEmail, String candidateEmail, String subject, String body) {
        return new Email(loggedEmail, candidateEmail, subject, body);
    }

    public void sendEmails(List<Email> emails) {
        try {
            oos.writeObject(emails);
            System.out.println("All emails sent!");
        } catch (IOException e) {
            System.out.println("Error sending emails: " + e.getMessage());
        }
    }

    public void closeSocket() {
        try {
            sock.close();
        } catch (IOException e) {
            System.out.println("Error closing socket: " + e.getMessage());
        }
    }
}
