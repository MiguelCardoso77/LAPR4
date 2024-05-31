package followUp.server;

import core.protocol.Jobs4UProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

public abstract class Handler implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Handler.class);

    private final Socket socket;

    protected DataInputStream inData;
    protected DataOutputStream outData;
    protected Jobs4UProtocol protocol;

    public Handler(Socket socket) throws IOException {
        this.socket = socket;
        this.inData = new DataInputStream(socket.getInputStream());
        this.outData = new DataOutputStream(socket.getOutputStream());
        this.protocol = new Jobs4UProtocol(socket);
    }

    public abstract void handle();

    @Override
    public void run() {
        try {
            handle();
            ServerSemaphore.getInstance().exitCriticalSection();
        } catch (Exception e) {
            LOGGER.error("There was an error during handler. Closing connection...", e);
            try {
                socket.close();
            } catch (IOException ex) {
                LOGGER.error("There was an error closing socket. Ignoring closing...", ex);
                return;
            }
            LOGGER.info("Socket closed successfully!");
        }
    }
}
