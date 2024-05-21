package followUp.server;

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

            Object message = this.input.readObject();
            System.out.println("Received: " + message);

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
