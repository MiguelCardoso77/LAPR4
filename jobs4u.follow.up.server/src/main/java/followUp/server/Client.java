package followUp.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("127.0.0.1",7878);
        ObjectOutputStream ois = new ObjectOutputStream(s.getOutputStream());
        ois.writeObject("Hello from the other side, client!");
        s.close();
    }
}
