package core.protocol;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
/**
 * Handles the authentication of a candidate in the Jobs4U system.
 * This class provides a method to authenticate a candidate using their email and password.
 * It communicates with the server using a Jobs4UProtocol instance.
 *
 * @author Miguel Cardoso
 */
public class ServerAuthentication {
    /**
     * Authenticates a candidate using their email and password.
     * This method sends the email and password to the server using a Jobs4UProtocol instance.
     * It then reads the response code from the server and returns true if the authentication is successful, false otherwise.
     *
     * @param email The email of the candidate.
     * @param password The password of the candidate.
     * @return true if the authentication is successful, false otherwise.
     * @throws RuntimeException If an I/O error occurs when communicating with the server.
     */
    public boolean authenticateCandidate(String email, String password) {
        try {
            Socket socket = new Socket("127.0.0.1", 2005);
            DataInputStream inData = new DataInputStream(socket.getInputStream());

            Jobs4UProtocol protocol = new Jobs4UProtocol(socket);
            protocol.sendAuth(email, password);

            inData.readByte();
            byte code = inData.readByte();

            if (code == ProtocolCodes.ACK.code()) {
                System.out.println("ACK Code Received: Authentication successful!");
                return true;
            } else if (code == ProtocolCodes.ERR.code()) {
                System.out.println("ERR Code Received: Authentication failed!");
                return false;
            }

            socket.close();
        } catch (IOException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        return false;
    }
}
