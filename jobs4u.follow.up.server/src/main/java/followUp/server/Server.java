package followUp.server;

import core.protocol.Jobs4UProtocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private static final ThreadGroup serverThreadGroup = new ThreadGroup("server-thread-group");
    private final ServerSocket socket;
    private boolean isRunning;

    public Server(int port) throws IOException {
        socket = new ServerSocket(port);
        isRunning = false;
    }

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

    public void stop() {
        this.isRunning = false;

        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        start();
    }
}
