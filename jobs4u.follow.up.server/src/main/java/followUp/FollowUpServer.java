package followUp;

import core.domain.user.Jobs4UPasswordPolicy;
import core.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.io.util.Console;
import followUp.server.Server;
import infrastructure.AppSettings;

import java.io.IOException;

/**
 * Main class to start and stop the Follow-Up Server.
 *
 * @author Miguel Cardoso
 */
public class FollowUpServer {
    private static final int PORT = 2005;

    /**
     * Main method to start the Follow-Up Server.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        try {
            Server server = new Server(PORT);
            startServer();

            Thread serverThread = new Thread(server);
            serverThread.setDaemon(true);
            serverThread.start();

            Console.readLine("To stop the server press 'ENTER'...");

            server.stop();
            stopServer(serverThread);
        } catch (IOException e) {
            System.out.println("Could not bind to port " + PORT);
            System.out.println("Exiting the application...");
        }
    }

    /**
     * Initializes and starts the Follow-Up Server.
     */
    public static void startServer() {
        System.out.println("Starting Follow-Up Server...");
        AuthzRegistry.configure(PersistenceContext.repositories().users(), new Jobs4UPasswordPolicy(), new PlainTextEncoder());
        new AppSettings();
    }

    /**
     * Stops the Follow-Up Server by interrupting the specified thread.
     *
     * @param thread The thread running the server
     */
    public static void stopServer(Thread thread) {
        thread.interrupt();
    }
}