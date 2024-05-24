package followUp;

import core.domain.user.Jobs4UPasswordPolicy;
import core.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.io.util.Console;
import followUp.server.Server;
import infrastructure.AppSettings;

import java.io.IOException;

public class FollowUpServer {
    private static final int PORT = 1010;

    public static void main(String[] args) throws InterruptedException {
        try {
            Server server = new Server(PORT);
            init();

            Thread serverThread = new Thread(server);
            serverThread.setDaemon(true); //ver para q serve
            serverThread.start();

            Console.readLine("To stop the server press 'ENTER'...");

            server.stop();
            stop(serverThread);
        } catch (IOException e) {
            System.out.println("Could not bind to port " + PORT);
            System.out.println("Exiting the application...");
        }
    }

    public static void init() {
        System.out.println("Initializing server...");
        AuthzRegistry.configure(PersistenceContext.repositories().users(), new Jobs4UPasswordPolicy(), new PlainTextEncoder());
        new AppSettings();
    }

    public static void stop(Thread thread) {
        thread.interrupt();
    }
}