package followUp.server;

import java.util.Properties;
import java.util.concurrent.Semaphore;

public class ServerSemaphore {
    private static ServerSemaphore instance;
    private final Semaphore semaphore;

    private ServerSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public static ServerSemaphore getInstance() {
        if (instance == null) {
            synchronized (ServerSemaphore.class) {
                if (instance != null) return instance;
                Properties props = System.getProperties();
                return new ServerSemaphore(new Semaphore(Integer.parseInt(props.getOrDefault("server.connections.max", 10).toString())));
            }
        }
        return instance;
    }

    public void enterCriticalSection() throws InterruptedException {
        this.semaphore.acquire();
    }

    public void exitCriticalSection() {
        this.semaphore.release();
    }

}
