package core.protocol;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Packet<T> implements Serializable {
    private static final int MAX_CHUNK_LEN = 255 + 256 * 255;

    private byte version;
    private ProtocolCodes code;
    private byte codes;
    private List<DataChunk> data;

    public Packet(byte version, ProtocolCodes code, T data) throws IOException {
        this.version = version;
        this.code = code;
        this.data = createChunks(data);
    }

    public Packet(byte version, byte code, List<DataChunk> data) throws IOException {
        this.version = version;
        this.codes = code;
        this.data = data;
    }

    public byte version() {
        return version;
    }

    public ProtocolCodes code() {return code; }

    public byte codes() {return codes; }


    public List<DataChunk> data() {
        return data;
    }

    public <T> T buildObject() throws IOException, ClassNotFoundException {
        List<Byte> rawData = new ArrayList<>();

        for (DataChunk dataChunk : data) {
            for (byte b : dataChunk.data()) {
                rawData.add(b);
            }
        }

        byte[] arr = new byte[rawData.size()];
        for (int i = 0; i < rawData.size(); i++) {
            arr[i] = rawData.get(i);
        }

        ByteArrayInputStream bais = new ByteArrayInputStream(arr);
        ObjectInputStream ois = new ObjectInputStream(bais);

        return (T) ois.readObject();
    }

    private List<DataChunk> createChunks(T data) throws IOException {
        if (data == null) return new ArrayList<>();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(data);
        byte[] rawData = baos.toByteArray();

        int nChunks = rawData.length / MAX_CHUNK_LEN + 1;

        int index = 0;
        List<DataChunk> dataChunks = new ArrayList<>(nChunks);

        for (int i = 0; i < nChunks; i++) {
            int chunkSize = Math.min(MAX_CHUNK_LEN, rawData.length - index);
            byte[] arr = new byte[chunkSize];

            for (int j = 0; j < chunkSize; j++) {
                arr[j] = rawData[index];
                index++;
            }

            byte data1LenL = (byte) (chunkSize % 256);
            byte data1LenM = (byte) (chunkSize / 256);

            DataChunk dataChunk = new DataChunk(new UnsignedInteger(data1LenL), new UnsignedInteger(data1LenM), arr);
            dataChunks.add(dataChunk);
        }

        return dataChunks;
    }

    public static Packet fromDataStream(DataInputStream inputStream) throws IOException {
        byte[] metadata = new byte[2];
        int bytesRead = inputStream.read(metadata);

        if (bytesRead != 2) {
            throw new IOException("Insufficient metadata bytes read");
        }

        byte protocolVersion = metadata[0];
        byte code = metadata[1];

        List<DataChunk> dataChunkList = new ArrayList<>();

        while (true) {
            byte[] lengthBytes = new byte[2];
            bytesRead = inputStream.read(lengthBytes);
            if (bytesRead != 2) {
                throw new IOException("Insufficient data length bytes read");
            }

            int dataLength = (lengthBytes[0] & 0xFF) + ((lengthBytes[1] & 0xFF) << 8);
            if (dataLength == 0) {
                break;
            }

            byte[] dataContent = new byte[dataLength];
            bytesRead = inputStream.read(dataContent);
            if (bytesRead != dataLength) {
                throw new IOException("Insufficient data bytes read");
            }

            dataChunkList.add(new DataChunk(dataContent));
        }

        return new Packet(protocolVersion, code, dataChunkList);
    }


}