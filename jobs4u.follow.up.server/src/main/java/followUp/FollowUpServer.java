package followUp;

import core.domain.user.Jobs4UPasswordPolicy;
import core.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import followUp.server.Server;
import infrastructure.AppSettings;

import java.io.IOException;
import java.util.Scanner;

public class FollowUpServer {
    private static final int PORT = 7878;

    public static void main(String[] args) throws InterruptedException {
        try {
            Server server = new Server(PORT);
            System.out.println("At any time, press ENTER to stop server");
            init();
            Thread thread = new Thread(server);
            thread.setDaemon(true);
            thread.start();
            Scanner sc = new Scanner(System.in);
            System.out.println("At any time, press ENTER to stop server");
            sc.nextLine();
            server.stop();
            stop(thread);
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