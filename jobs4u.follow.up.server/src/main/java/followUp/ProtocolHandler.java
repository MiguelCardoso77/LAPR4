package followUp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class ProtocolHandler {
    //@Autowired
    //private AuthService authService;

    //@Autowired
    //private VerificationService verificationService;

    public void startServer(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);
            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientHandler extends Thread {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try (InputStream input = socket.getInputStream();
                 OutputStream output = socket.getOutputStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                 PrintWriter writer = new PrintWriter(output, true)) {

                String text;
                while ((text = reader.readLine()) != null) {
                    // Handle protocol-based communication
                    String[] request = text.split(" ");
                    String command = request[0];

                    switch (command) {
                        case "AUTH":
                            String username = request[1];
                            String password = request[2];
                            //boolean authenticated = authService.authenticateUser(username, password);
                            //writer.println(authenticated ? "SUCCESS" : "FAILURE");
                            break;
                        case "VERIFY":
                            Long candidateId = Long.parseLong(request[1]);
                            //String result = verificationService.verifyCandidate(candidateId);
                            //writer.println("VERIFICATION " + result);
                            break;
                        default:
                            writer.println("UNKNOWN COMMAND");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
