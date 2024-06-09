package followUp.server;

import core.protocol.Jobs4UProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

/**
 * The BaseResponder class provides a base implementation for handling incoming client requests.
 * Subclasses must implement the startHandler() method to define the specific handling logic.
 *
 * @author Miguel Cardoso
 */
public abstract class BaseResponder implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseResponder.class);

    private final Socket socket;
    protected DataInputStream inData;
    protected DataOutputStream outData;
    protected Jobs4UProtocol protocol;

    /**
     * Constructs a new BaseResponder object with the given socket.
     *
     * @param socket the socket for communication with the client
     * @throws IOException if an I/O error occurs when creating the input and output streams
     */
    public BaseResponder(Socket socket) throws IOException {
        this.socket = socket;
        this.inData = new DataInputStream(socket.getInputStream());
        this.outData = new DataOutputStream(socket.getOutputStream());
        this.protocol = new Jobs4UProtocol(socket);
    }

    /**
     * Subclasses must implement this method to define the specific handling logic for incoming requests.
     */
    public abstract void startHandler();

    /**
     * Runs the responder to handle client requests.
     * Calls the startHandler() method to initiate request handling.
     * Releases the connection once handling is complete.
     */
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
