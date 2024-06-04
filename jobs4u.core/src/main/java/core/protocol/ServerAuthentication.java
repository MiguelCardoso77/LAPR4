package core.protocol;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerAuthentication {
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
