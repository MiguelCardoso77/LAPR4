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

    public ProtocolCodes code() {
        return code;
    }

    public byte codes() {
        return codes;
    }


    public List<DataChunk> data() {
        return data;
    }

    public <T> T buildObjectT() throws IOException, ClassNotFoundException {
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

    public byte[] buildObject() throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             DataOutputStream dos = new DataOutputStream(bos)) {

            dos.writeByte(version);
            dos.writeByte(codes);

            for (DataChunk dataChunk : data) {
                byte[] data = dataChunk.data();
                dos.writeShort(data.length); // Write length of data chunk
                dos.write(data); // Write data chunk
            }

            dos.writeShort(0); // End of data chunks

            dos.flush();
            return bos.toByteArray();
        }
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
        byte version = inputStream.readByte();
        byte code = inputStream.readByte();

        List<DataChunk> dataChunkList = new ArrayList<>();

        while (true) {
            int dataLength = inputStream.readUnsignedShort();
            if (dataLength == 0) {
                break; // End of data chunks
            }

            byte[] dataContent = new byte[dataLength];
            inputStream.readFully(dataContent); // Read the full data chunk

            dataChunkList.add(new DataChunk(dataContent));
        }

        return new Packet(version, code, dataChunkList);
    }


}