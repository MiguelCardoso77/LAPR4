package followUp.server;

import core.protocol.Jobs4UProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

public abstract class BaseResponder implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseResponder.class);

    private final Socket socket;
    protected DataInputStream inData;
    protected DataOutputStream outData;
    protected Jobs4UProtocol protocol;

    public BaseResponder(Socket socket) throws IOException {
        this.socket = socket;
        this.inData = new DataInputStream(socket.getInputStream());
        this.outData = new DataOutputStream(socket.getOutputStream());
        this.protocol = new Jobs4UProtocol(socket);
    }

    public abstract void startHandler();

    @Override
    public void run() {
        try {

            startHandler();
            ConnectionLimiter.instance().releaseConnection();

        } catch (Exception e) {
            LOGGER.error("An error occurred during handling. Closing connection...", e);

            try {
                socket.close();
            } catch (IOException ee) {
                LOGGER.error("An error occurred closing socket.", ee);
                return;
            }

            LOGGER.info("Socket closed successfully!");
        }
    }
}
