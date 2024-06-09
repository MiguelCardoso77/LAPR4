package core.protocol;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the Jobs4U protocol for communicating with the server.
 *
 * @author Miguel Cardoso
 */
public class Jobs4UProtocol {
    private final DataOutputStream outData;
    private final byte VERSION = 0;

    /**
     * Constructor for Jobs4UProtocol.
     *
     * @param connection The socket connection to the server
     * @throws IOException If an I/O error occurs when creating the input or output stream
     */
    public Jobs4UProtocol(Socket connection) throws IOException {
        outData = new DataOutputStream(connection.getOutputStream());
    }

    /**
     * Sends a communication test to the server.
     *
     * @return true if the test is sent successfully
     * @throws IOException If an I/O error occurs when sending the data
     */
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

    /**
     * Sends an acknowledgment to the server.
     *
     * @return true if the acknowledgment is sent successfully
     * @throws IOException If an I/O error occurs when sending the data
     */
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

    /**
     * Sends an error message to the server.
     *
     * @return true if the error message is sent successfully
     * @throws IOException If an I/O error occurs when sending the data
     */
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

    /**
     * Sends a disconnect request to the server.
     *
     * @return true if the disconnect request is sent successfully
     * @throws IOException If an I/O error occurs when sending the data
     */
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

    /**
     * Sends authentication data to the server.
     *
     * @param email    The user's email
     * @param password The user's password
     * @throws IOException If an I/O error occurs when sending the data
     */
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

    /**
     * Sends an email request to the server.
     *
     * @param toWho   The recipient's email address
     * @param subject The email subject
     * @param body    The email body
     * @throws IOException If an I/O error occurs when sending the data
     */
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
     * Sends a request to retrieve applications for a given email.
     *
     * @param email The email of the candidate
     * @throws IOException If an I/O error occurs when sending the data
     */
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

    /**
     * Receives a list of applications from the server.
     *
     * @param applicationsJSON The JSON string containing the list of applications
     * @return true if the list is received successfully
     * @throws IOException If an I/O error occurs when sending the data
     */
    public boolean receiveListApplications(String applicationsJSON) throws IOException {
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();

        byteArrayOut.write(VERSION);
        byteArrayOut.write(ProtocolCodes.APPLICATIONS.code());

        DataChunk dataChunk = buildDataChunk(applicationsJSON);

        byteArrayOut.write(dataChunk.dataLenL().rawValue());
        byteArrayOut.write(dataChunk.dataLenM().rawValue());
        byteArrayOut.write(dataChunk.data());

        byteArrayOut.write(0);
        byteArrayOut.write(0);

        byte[] requestedData = byteArrayOut.toByteArray();
        outData.write(requestedData);

        return true;
    }

    /**
     * Sends a request to retrieve job openings for a given email.
     *
     * @param email The email of the candidate
     * @throws IOException If an I/O error occurs when sending the data
     */
    public void sendJobOpenings(String email) throws IOException {
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();

        byteArrayOut.write(VERSION);
        byteArrayOut.write(ProtocolCodes.JOB_OPENINGS.code());

        DataChunk dataChunk = buildDataChunk(email);

        byteArrayOut.write(dataChunk.dataLenL().rawValue());
        byteArrayOut.write(dataChunk.dataLenM().rawValue());
        byteArrayOut.write(dataChunk.data());

        byteArrayOut.write(0);
        byteArrayOut.write(0);

        byte[] requestedData = byteArrayOut.toByteArray();
        outData.write(requestedData);
    }

    /**
     * Receives a list of job openings from the server.
     *
     * @param json The JSON string containing the list of job openings
     * @return true if the list is received successfully
     * @throws IOException If an I/O error occurs when sending the data
     */
    public boolean receiveJobOpeningLists(String json) throws IOException {
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();

        byteArrayOut.write(VERSION);
        byteArrayOut.write(ProtocolCodes.JOB_OPENINGS.code());

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

    /**
     * Sends a request to retrieve new notifications for a given email.
     *
     * @param email The email of the candidate
     * @throws IOException If an I/O error occurs when sending the data
     */
    public void sendNewNotifications(String email) throws IOException {
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();

        byteArrayOut.write(VERSION);
        byteArrayOut.write(ProtocolCodes.NEW_NOTIFICATIONS.code());

        DataChunk dataChunk = buildDataChunk(email);

        byteArrayOut.write(dataChunk.dataLenL().rawValue());
        byteArrayOut.write(dataChunk.dataLenM().rawValue());
        byteArrayOut.write(dataChunk.data());

        byteArrayOut.write(0);
        byteArrayOut.write(0);

        byte[] requestedData = byteArrayOut.toByteArray();
        outData.write(requestedData);
    }

    /**
     * Receives a list of new notifications from the server.
     *
     * @param json The JSON string containing the list of new notifications
     * @return true if the list is received successfully
     * @throws IOException If an I/O error occurs when sending the data
     */
    public boolean receiveNewNotificationsList(String json) throws IOException {
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();

        byteArrayOut.write(VERSION);
        byteArrayOut.write(ProtocolCodes.NEW_NOTIFICATIONS.code());

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

    /**
     * Sends a request to retrieve old notifications for a given email.
     *
     * @param email The email of the candidate
     * @throws IOException If an I/O error occurs when sending the data
     */
    public void sendOldNotifications(String email) throws IOException {
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();

        byteArrayOut.write(VERSION);
        byteArrayOut.write(ProtocolCodes.OLD_NOTIFICATIONS.code());

        DataChunk dataChunk = buildDataChunk(email);

        byteArrayOut.write(dataChunk.dataLenL().rawValue());
        byteArrayOut.write(dataChunk.dataLenM().rawValue());
        byteArrayOut.write(dataChunk.data());

        byteArrayOut.write(0);
        byteArrayOut.write(0);

        byte[] requestedData = byteArrayOut.toByteArray();
        outData.write(requestedData);
    }

    /**
     * Receives a list of old notifications from the server.
     *
     * @param json The JSON string containing the list of old notifications
     * @return true if the list is received successfully
     * @throws IOException If an I/O error occurs when sending the data
     */
    public boolean receiveOldNotificationsList(String json) throws IOException {
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();

        byteArrayOut.write(VERSION);
        byteArrayOut.write(ProtocolCodes.OLD_NOTIFICATIONS.code());

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

    /**
     * Builds a DataChunk from a given data string.
     *
     * @param data The data string
     * @return The DataChunk built from the given data
     */
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