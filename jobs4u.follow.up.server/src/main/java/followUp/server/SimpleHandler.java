package followUp.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;

public class SimpleHandler extends Handler{
    private final Logger LOGGER = LoggerFactory.getLogger(SimpleHandler.class);

    public SimpleHandler(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    public void handle() {
        System.out.println("Hello World! I am Alive!");
        try {
            System.out.println(this.input.readObject().toString());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendShutdownSignal() {
        try {
            output.writeObject("Server is shutting down");
        } catch (IOException e) {
            LOGGER.error("Error sending shutdown signal", e);
        }
    }
}
