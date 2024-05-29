package user.console;

import core.domain.user.Jobs4UPasswordPolicy;
import core.protocol.DataChunk;
import core.protocol.Packet;
import core.protocol.ProtocolCodes;
import eapli.framework.io.util.Console;
import user.console.presentation.FrontMenu;
import core.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for the Jobs4U Candidate Application.
 * This class initializes the application by configuring the authorization registry and displaying the front menu.
 *
 * @author Miguel Cardoso
 */
@SuppressWarnings("squid:S106")
public final class CandidateApp {
    private static final int PORT = 1010;

    /**
     * Private constructor to prevent instantiation of this class.
     */
    private CandidateApp() {
    }

    /**
     * Main method to start the application.
     *
     * @param args command-line arguments
     */
    public static void main(final String[] args) throws IOException {

        System.out.println("=====================================");
        System.out.println("Candidate App");
        System.out.println("(C) 2024");
        System.out.println("=====================================");

        AuthzRegistry.configure(PersistenceContext.repositories().users(), new Jobs4UPasswordPolicy(), new PlainTextEncoder());

        // Get user input for username and password
        final String username = Console.readLine("\nUsername: ");
        final String password = Console.readLine("\nPassword: ");

        // Prepare the authentication request
        byte version = 1;
        byte code = ProtocolCodes.AUTH.code();
        List<DataChunk> dataChunkList = new ArrayList<>();
        dataChunkList.add(new DataChunk(username.getBytes()));
        dataChunkList.add(new DataChunk(password.getBytes()));

        Packet authRequest = new Packet(version, code, dataChunkList);

        byte[] requestedData;

        try{
            requestedData = (byte[]) authRequest.buildObject();

            // Connect to the server and send the request
            try (Socket socket = new Socket("localHost", PORT);

                 DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                 DataInputStream inputStream = new DataInputStream(socket.getInputStream())){

                // Send request data to server
                outputStream.write(requestedData);

                // Wait for response from server
                Packet response = Packet.fromDataStream(inputStream);

                if (response.codes() == ProtocolCodes.ACK.code()){
                    System.out.println("Login Successful!");
                } else {
                    System.out.println("Login failed. Please check your credentials.");
                }

            } catch (IOException e){
                System.err.println("Error communicating with server: " + e.getMessage());
            }

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error creating authentication request: " + e.getMessage());
        }
    }

}
