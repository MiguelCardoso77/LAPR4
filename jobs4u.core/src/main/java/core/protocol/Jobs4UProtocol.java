package core.protocol;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Jobs4UProtocol {
    private final DataInputStream inData;
    private final DataOutputStream outData;
    private final byte VERSION = 0;

    public Jobs4UProtocol(Socket connection) throws IOException {
        inData = new DataInputStream(connection.getInputStream());
        outData = new DataOutputStream(connection.getOutputStream());
    }

    public boolean sendCommTest() throws IOException {
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        byteArrayOut.write(VERSION);
        byteArrayOut.write(ProtocolCodes.COMMTEST.code());

        byteArrayOut.write(0);
        byteArrayOut.write(0);

        byte[] requestedData = byteArrayOut.toByteArray();

        System.out.println("Sending COMMTEST ...");
        outData.write(requestedData);

        return true;
    }

    public boolean sendAck() throws IOException {
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        byteArrayOut.write(VERSION);
        byteArrayOut.write(ProtocolCodes.ACK.code());

        byteArrayOut.write(0);
        byteArrayOut.write(0);

        byte[] requestedData = byteArrayOut.toByteArray();

        System.out.println("Sending ACK ...");
        outData.write(requestedData);

        return true;
    }

    public boolean sendErr() throws IOException {
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        byteArrayOut.write(VERSION);
        byteArrayOut.write(ProtocolCodes.ERR.code());

        byteArrayOut.write(0);
        byteArrayOut.write(0);

        byte[] requestedData = byteArrayOut.toByteArray();

        System.out.println("Sending ERR ...");
        outData.write(requestedData);

        return true;
    }

    public boolean sendDisconnect() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        byteArrayOut.write(VERSION);
        byteArrayOut.write(ProtocolCodes.DISCONNECT.code());

        byteArrayOut.write(0);
        byteArrayOut.write(0);

        byte[] requestedData = byteArrayOut.toByteArray();

        System.out.println("Sending DISCONNECT ...");
        outData.write(requestedData);

        return true;
    }

    public void sendAuth(String email, String password) throws IOException, ClassNotFoundException {
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

        outData.write(requestedData);
    }

    public void sendEmail(String toWho, String subject, String body) throws IOException {
        List<String> data = new ArrayList<>();
        data.add(toWho);
        data.add(subject);
        data.add(body);

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
        byteArrayOut.write(ProtocolCodes.EMAIL.code());

        for (DataChunk chunk : dataChunkList) {
            byteArrayOut.write(chunk.dataLenL().rawValue());
            byteArrayOut.write(chunk.dataLenM().rawValue());
            byteArrayOut.write(chunk.data());
        }

        byteArrayOut.write(0);
        byteArrayOut.write(0);

        byte[] requestedData = byteArrayOut.toByteArray();

        outData.write(requestedData);
    }

/**
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
 **/


}