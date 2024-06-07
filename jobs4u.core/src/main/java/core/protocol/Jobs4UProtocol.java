package core.protocol;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
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

    public void sendApplications(String email) throws IOException {
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();

        byteArrayOut.write(VERSION);
        byteArrayOut.write(ProtocolCodes.APPLICATIONS.code());

        DataChunk dataChunk = buildDataChunk(email);

        byteArrayOut.write(dataChunk.dataLenL().rawValue());
        byteArrayOut.write(dataChunk.dataLenM().rawValue());
        byteArrayOut.write(dataChunk.data());

        byteArrayOut.write(0);
        byteArrayOut.write(0);

        byte[] requestedData = byteArrayOut.toByteArray();
        outData.write(requestedData);
    }

    public boolean receiveListApplications(String json) throws IOException {
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();

        byteArrayOut.write(VERSION);
        byteArrayOut.write(ProtocolCodes.APPLICATIONS.code());

        DataChunk dataChunk = buildDataChunk(json);

        byteArrayOut.write(dataChunk.dataLenL().rawValue());
        byteArrayOut.write(dataChunk.dataLenM().rawValue());
        byteArrayOut.write(dataChunk.data());

        byteArrayOut.write(0);
        byteArrayOut.write(0);

        byte[] requestedData = byteArrayOut.toByteArray();
        outData.write(requestedData);

        return true;
    }

    public void sendJobOpenings(String email) throws IOException {
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        final int MAX_CHUNK_LEN = 255 + 256 * 255;

        byte[] rawData = email.getBytes();
        int index = 0;
        int chunkSize = Math.min(MAX_CHUNK_LEN, rawData.length - index);
        byte[] arr = new byte[chunkSize];

        for (int j = 0; j < chunkSize; j++) {
            arr[j] = rawData[index];
            index++;
        }

        byte data1LenL = (byte) (chunkSize % 256);
        byte data1LenM = (byte) (chunkSize / 256);

        DataChunk dataChunk = new DataChunk(new UnsignedInteger(data1LenL), new UnsignedInteger(data1LenM), arr);

        byteArrayOut.write(VERSION);
        byteArrayOut.write(ProtocolCodes.JOB_OPENINGS.code());

        byteArrayOut.write(dataChunk.dataLenL().rawValue());
        byteArrayOut.write(dataChunk.dataLenM().rawValue());
        byteArrayOut.write(dataChunk.data());

        byteArrayOut.write(0);
        byteArrayOut.write(0);

        byte[] requestedData = byteArrayOut.toByteArray();
        outData.write(requestedData);
    }

    public boolean receiveJobOpeningLists(String json) throws IOException {
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        final int MAX_CHUNK_LEN = 255 + 256 * 255;

        byte[] rawData = json.getBytes();
        int index = 0;
        int chunkSize = Math.min(MAX_CHUNK_LEN, rawData.length - index);
        byte[] arr = new byte[chunkSize];

        for (int j = 0; j < chunkSize; j++) {
            arr[j] = rawData[index];
            index++;
        }

        byte data1LenL = (byte) (chunkSize % 256);
        byte data1LenM = (byte) (chunkSize / 256);

        DataChunk dataChunk = new DataChunk(new UnsignedInteger(data1LenL), new UnsignedInteger(data1LenM), arr);

        byteArrayOut.write(VERSION);
        byteArrayOut.write(ProtocolCodes.JOB_OPENINGS.code());

        byteArrayOut.write(dataChunk.dataLenL().rawValue());
        byteArrayOut.write(dataChunk.dataLenM().rawValue());
        byteArrayOut.write(dataChunk.data());

        byteArrayOut.write(0);
        byteArrayOut.write(0);

        byte[] requestedData = byteArrayOut.toByteArray();
        outData.write(requestedData);

        return true;
    }

    public void sendNotifications(String email) {
    }

    private DataChunk buildDataChunk(String data) {
        final int MAX_CHUNK_LEN = 255 + 256 * 255;

        byte[] rawData = data.getBytes();
        int index = 0;
        int chunkSize = Math.min(MAX_CHUNK_LEN, rawData.length - index);
        byte[] arr = new byte[chunkSize];

        for (int j = 0; j < chunkSize; j++) {
            arr[j] = rawData[index];
            index++;
        }

        byte data1LenL = (byte) (chunkSize % 256);
        byte data1LenM = (byte) (chunkSize / 256);

        return new DataChunk(new UnsignedInteger(data1LenL), new UnsignedInteger(data1LenM), arr);
    }
}