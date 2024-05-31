package core.protocol;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComProtocolV0 {
    private ObjectOutputStream output;
    private DataOutputStream outData;
    private ObjectInputStream input;
    private final byte VERSION = 0;

    private final int MAX_TRIES = 3;

    public ComProtocolV0(Socket connection) throws IOException {
        output = new ObjectOutputStream(connection.getOutputStream());
        outData = new DataOutputStream(connection.getOutputStream());
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

    public boolean sendAuth(String email, String password) throws IOException, ClassNotFoundException {
        List<String> data = new ArrayList<>();
        data.add(email);
        data.add(password);

        List<DataChunk> dataChunkList = new ArrayList<>();
        final int MAX_CHUNK_LEN = 255 + 256 * 255;

        for (String s : data) {
            byte[] rawData = s.getBytes();
            int index = 0;

            int chunkSize = Math.min(MAX_CHUNK_LEN, rawData.length - index);
            byte[] arr = new byte[chunkSize];

            for (int j = 0; j < chunkSize; j++) {
                arr[j] = rawData[index];
                index++;
            }

            byte data1LenL = (byte) (chunkSize % 256);
            byte data1LenM = (byte) (chunkSize / 256);

            dataChunkList.add(new DataChunk(new UnsignedInteger(data1LenL), new UnsignedInteger(data1LenM), arr));
        }

        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        byteArrayOut.write(VERSION);
        byteArrayOut.write(ProtocolCodes.AUTH.code());

        for (DataChunk chunk : dataChunkList) {
            byteArrayOut.write(chunk.dataLenL().rawValue());
            byteArrayOut.write(chunk.dataLenM().rawValue());
            byteArrayOut.write(chunk.data());
        }

        byteArrayOut.write(0);
        byteArrayOut.write(0);

        byte[] requestedData = byteArrayOut.toByteArray();
        System.out.println(Arrays.toString(requestedData));

        outData.write(requestedData);

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

            if (response != null && response.code() != ProtocolCodes.ERR) {
                return (T) response.data();
            }

            output.writeObject(new Packet<>(VERSION, ProtocolCodes.ERR, null));

            tries--;
        } while (response == null || response.code() != ProtocolCodes.ACK);
        return (T) response.data();
    }


}