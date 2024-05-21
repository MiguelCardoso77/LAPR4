package followUp.server;

import core.domain.email.Email;

import java.io.IOException;
import java.net.Socket;

public class SimpleHandler extends Handler{
    public SimpleHandler(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    public void handle() {
        System.out.println("Connection established!");
        try {

            Email message = (Email) this.input.readObject();
            System.out.println("Received: " + message);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
