package core.services;

import core.domain.email.Email;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

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

    public void sendEmail(Email email) {
        try {
            oos.writeObject(email);
            System.out.println("Email sent to " + email.toWho() + "!");
        } catch (IOException e) {
            System.out.println("Error sending email: " + e.getMessage());
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
