package followUp.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket sock = new Socket("127.0.0.1",1010);

        ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());

        oos.writeObject("Client side connected successfully!");

        sock.close();
    }
}
