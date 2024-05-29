package core.protocol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ComProtocolV0 {
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private final byte VERSION = 0;

    private final int MAX_TRIES = 3;

    public ComProtocolV0(Socket connection) throws IOException {
        output = new ObjectOutputStream(connection.getOutputStream());
        input = new ObjectInputStream(connection.getInputStream());
    }

    public boolean sendAck() throws IOException, ClassNotFoundException {
        Packet response = null;
        int tries = MAX_TRIES;
        do {
            if (tries == 0) return false;
            Packet p = new Packet(VERSION, ProtocolCodes.ACK, null);
            output.writeObject(p);
            response = ((Packet<?>) input.readObject());
            tries--;
        } while (response == null || response.code() != ProtocolCodes.ACK);
        return true;
    }

    public boolean sendErr() throws IOException, ClassNotFoundException {
        Packet response = null;
        int tries = MAX_TRIES;
        do {
            if (tries == 0) return false;
            Packet p = new Packet(VERSION, ProtocolCodes.ERR, null);
            output.writeObject(p);
            response = ((Packet<?>) input.readObject());
            tries--;
        } while (response == null || response.code() != ProtocolCodes.ACK);
        return true;
    }

    public boolean sendDisconnect() throws IOException, ClassNotFoundException {
        Packet response = null;
        int tries = MAX_TRIES;
        do {
            if (tries == 0) return false;
            Packet p = new Packet(VERSION, ProtocolCodes.DISCONNECT, null);
            output.writeObject(p);
            response = ((Packet<?>) input.readObject());
            tries--;
        } while (response == null || response.code() != ProtocolCodes.ACK);
        return true;
    }

    public boolean commTest() throws IOException, ClassNotFoundException {
        Packet response = null;
        int tries = MAX_TRIES;
        do {
            if (tries == 0) return false;
            Packet p = new Packet(VERSION, ProtocolCodes.COMMTEST, null);
            output.writeObject(p);
            response = ((Packet<?>) input.readObject());
            tries--;
        } while (response == null || response.code() != ProtocolCodes.ACK);
        return true;
    }

    public <T> boolean send(T object, ProtocolCodes code) throws IOException, ClassNotFoundException {
        Packet response = null;
        int tries = MAX_TRIES;
        do {
            if (tries == 0) return false;
            Packet p = new Packet(VERSION, code, object);
            output.writeObject(p);
            response = ((Packet<?>) input.readObject());
            tries--;
        } while (response == null || response.code() != ProtocolCodes.ACK);
        return true;
    }

    public <T> T receive() throws IOException, ClassNotFoundException {
        Packet<T> response = null;
        int tries = MAX_TRIES;
        do {
            if (tries == 0) return null;
            Packet p = new Packet(VERSION, ProtocolCodes.ACK, null);
            response = ((Packet<T>) input.readObject());

            if (response!=null && response.code() != ProtocolCodes.ERR){
                return (T) response.data();
            }

            output.writeObject(new Packet<>(VERSION, ProtocolCodes.ERR, null));

            tries--;
        } while (response == null || response.code() != ProtocolCodes.ACK);
        return (T) response.data();
    }


}