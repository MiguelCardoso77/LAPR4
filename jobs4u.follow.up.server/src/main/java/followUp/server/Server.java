package followUp.server;

import core.protocol.Jobs4UProtocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The Server class represents a simple server that accepts client connections and handles them using CallResponder instances.
 *
 * @author Miguel Cardoso
 */
public class Server implements Runnable {
    private static final ThreadGroup serverThreadGroup = new ThreadGroup("server-thread-group");
    private final ServerSocket socket;
    private boolean isRunning;

    /**
     * Constructs a new Server instance that listens on the specified port.
     *
     * @param port the port number to listen on
     * @throws IOException if an I/O error occurs when opening the server socket
     */
    public Server(int port) throws IOException {
        socket = new ServerSocket(port);
        isRunning = false;
    }

    /**
     * Starts the server, accepting incoming client connections and handling them in separate threads.
     */
    public void start() {
        isRunning = true;

        while (isRunning) {
            try {
                Socket connection = socket.accept();

                if (!isRunning) {
                    Jobs4UProtocol protocol = new Jobs4UProtocol(connection);
                    protocol.sendDisconnect();
                    return;
                }

                ConnectionLimiter.instance().takeConnection();
                Thread thread = new Thread(serverThreadGroup, new CallResponder(connection));
                thread.start();

            } catch (IOException | InterruptedException | ClassNotFoundException e) {
                System.out.println("Could not accept new connection!");
            }
        }
    }

    /**
     * Stops the server, closing the server socket.
     */
    public void stop() {
        this.isRunning = false;

        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Starts the server by calling the start method.
     */
    @Override
    public void run() {
        start();
    }
}
