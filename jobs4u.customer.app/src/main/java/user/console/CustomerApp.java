package user.console;

import core.protocol.DataChunk;
import core.protocol.Packet;
import core.protocol.ProtocolCodes;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import core.persistence.PersistenceContext;
import core.domain.user.Jobs4UPasswordPolicy;
import eapli.framework.io.util.Console;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class for the Jobs4U Customer Application.
 * This class initializes the application by configuring the authorization registry and displaying the front menu.
 *
 * @author Miguel Cardoso
 */
@SuppressWarnings("squid:S106")
public final class CustomerApp {

    private static final int PORT = 1010;

    /**
     * Private constructor to prevent instantiation of this class.
     */
    private CustomerApp() {
    }

    /**
     * Main method to start the application.
     *
     * @param args command-line arguments
     */
    public static void main(final String[] args) throws IOException {
        System.out.println("=====================================");
        System.out.println("Customer App");
        System.out.println("(C) 2024");
        System.out.println("=====================================");

        AuthzRegistry.configure(PersistenceContext.repositories().users(), new Jobs4UPasswordPolicy(), new PlainTextEncoder());

        // Get user input for username and password
        System.out.println();
        System.out.println("=====================================");
        System.out.println();
        System.out.println("Please enter your credentials to login.");
        System.out.println();
        final String username = Console.readLine("\nUsername: ");
        System.out.println();
        final String password = Console.readLine("\nPassword: ");

        // Prepare the authentication request
        byte version = 1;
        byte code = ProtocolCodes.AUTH.code();
        List<DataChunk> dataChunkList = new ArrayList<>();
        dataChunkList.add(new DataChunk(username.getBytes()));
        dataChunkList.add(new DataChunk(password.getBytes()));

        Packet authRequest = new Packet(version, code, dataChunkList);

        byte[] authRequestBytes;

        try {
            authRequestBytes = (byte[]) authRequest.buildObject();

            try(Socket socket = new Socket("localHost", PORT)){

                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                DataInputStream in = new DataInputStream(socket.getInputStream());

                out.write(authRequestBytes);

                Packet response = Packet.fromDataStream(in);

                if(response.codes() == ProtocolCodes.ACK.code()){
                    System.out.println("Authentication successful");
                }else{
                    System.out.println("Authentication failed! Username and/or password incorrect.");
                }
            } catch (IOException e) {
                System.out.println("Error communicating with server: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error creating authentication request: " + e.getMessage());
        }
    }
}
