package user.connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSide {
    public static void main(String[] args) throws IOException {
        Socket sock = new Socket("127.0.0.1",1010);
        ObjectOutputStream ois = new ObjectOutputStream(sock.getOutputStream());
        ois.writeObject("Client side connected successfully!");
        sock.close();
    }
}
