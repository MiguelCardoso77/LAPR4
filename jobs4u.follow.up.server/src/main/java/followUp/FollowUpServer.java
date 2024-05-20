package followUp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FollowUpServer implements CommandLineRunner {

    @Autowired
    private ProtocolHandler protocolHandler;

    public static void main(String[] args) {
        SpringApplication.run(FollowUpServer.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        protocolHandler.startServer(8080); // Start the TCP server on port 8080
    }
}