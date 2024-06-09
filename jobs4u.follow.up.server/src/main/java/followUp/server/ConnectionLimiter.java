package followUp.server;

import java.util.Properties;
import java.util.concurrent.Semaphore;

/**
 * The ConnectionLimiter class provides a mechanism to limit the number of concurrent connections.
 *
 * @author Miguel Cardoso
 */
public class ConnectionLimiter {
    private static volatile ConnectionLimiter instance;
    private final Semaphore connectionSemaphore;

    /**
     * Constructs a new ConnectionLimiter with the given semaphore.
     *
     * @param semaphore the semaphore used for limiting connections
     */
    private ConnectionLimiter(Semaphore semaphore) {
        this.connectionSemaphore = semaphore;
    }

    /**
     * Returns the singleton instance of the ConnectionLimiter class.
     *
     * @return the singleton instance
     */
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

    /**
     * Acquires a permit for a connection, blocking until one is available if necessary.
     *
     * @throws InterruptedException if the current thread is interrupted while waiting
     */
    public void takeConnection() throws InterruptedException {
        this.connectionSemaphore.acquire();
    }

    /**
     * Releases a permit for a connection.
     */
    public void releaseConnection() {
        this.connectionSemaphore.release();
    }

}
