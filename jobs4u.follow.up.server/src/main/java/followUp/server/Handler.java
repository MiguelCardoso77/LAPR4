package followUp.server;

import core.protocol.ComProtocolV0;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

public abstract class Handler implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Handler.class);

    private final Socket socket;

    protected DataInputStream inData;
    protected ObjectInputStream input;
    protected DataOutputStream outData;
    protected ObjectOutputStream output;
    protected ComProtocolV0 protocol;

    public Handler(Socket socket) throws IOException {
        this.socket = socket;
        this.input = new ObjectInputStream(socket.getInputStream());
        this.inData = new DataInputStream(socket.getInputStream());
        this.output = new ObjectOutputStream(socket.getOutputStream());
        this.outData = new DataOutputStream(socket.getOutputStream());
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
