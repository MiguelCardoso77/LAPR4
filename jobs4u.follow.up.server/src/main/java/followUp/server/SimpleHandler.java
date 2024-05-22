package followUp.server;

import core.domain.email.Email;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class SimpleHandler extends Handler {

    public SimpleHandler(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    public void handle() {
        System.out.println("Connection established!");
        try {

            handleEmails();

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleEmails() throws IOException, ClassNotFoundException {
        List<Email> messages = (List<Email>) this.input.readObject();

        System.out.println("\nReceived emails: ");
        for (Email message : messages) {
            System.out.println(message);
            System.out.println("-------------------");
        }

        this.output.writeObject(messages);
    }
}
