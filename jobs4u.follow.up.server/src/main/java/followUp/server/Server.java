package followUp.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable {
    private static final ThreadGroup serverThreadGroup = new ThreadGroup("server-thread-group");
    private final ServerSocket socket;
    private boolean running;
    private final List<SimpleHandler> handlers = new ArrayList<>();

    public Server(int port) throws IOException {
        socket = new ServerSocket(port);
        running = false;
    }

    public void start() {
        running = true;
        while (running) {
            try {
                Socket connection = socket.accept();

                if (!running) {
                    for (SimpleHandler handler : handlers) {
                        handler.sendShutdownSignal();
                    }
                    return;
                }

                ServerSemaphore.getInstance().enterCriticalSection();

                SimpleHandler handler = new SimpleHandler(connection);
                handlers.add(handler);

                Thread thread = new Thread(serverThreadGroup, new SimpleHandler(connection));
                thread.start();
            } catch (IOException e) {
                System.out.println("Could not accept new connection!");
            } catch (InterruptedException e) {
                System.out.println("Could not wait for connection. Listening to new ones...");
            }
        }
    }

    public void stop() {
        this.running = false;
    }


    @Override
    public void run() {
        start();
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
