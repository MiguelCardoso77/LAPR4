package followUp.server;

import java.util.Properties;
import java.util.concurrent.Semaphore;

public class ConnectionLimiter {
    private static volatile ConnectionLimiter instance;
    private final Semaphore connectionSemaphore;

    private ConnectionLimiter(Semaphore semaphore) {
        this.connectionSemaphore = semaphore;
    }

    public static ConnectionLimiter instance() {
        if (instance == null) {
            synchronized (ConnectionLimiter.class) {
                if (instance == null) {
                    Properties properties = System.getProperties();
                    int maxConnections = Integer.parseInt(properties.getOrDefault("server.connections.max", 10).toString());
                    instance = new ConnectionLimiter(new Semaphore(maxConnections));
                }
            }
        }

        return instance;
    }

    public void takeConnection() throws InterruptedException {
        this.connectionSemaphore.acquire();
    }

    public void releaseConnection() {
        this.connectionSemaphore.release();
    }

}
