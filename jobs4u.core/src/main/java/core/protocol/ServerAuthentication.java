package core.protocol;

import java.io.IOException;
import java.net.Socket;

public class ServerAuthentication {
    public void authenticateCandidate(String email, String password) {

        try {
            Socket socket = new Socket("127.0.0.1", 1010);

            ComProtocolV0 protocol = new ComProtocolV0(socket);
            try {
                protocol.sendAuth(email, password);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
