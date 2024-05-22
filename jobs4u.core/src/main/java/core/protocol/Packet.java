package core.protocol;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Packet<T> implements Serializable {
    private static final int MAX_CHUNK_LEN = 255 + 256 * 255;

    private byte version;
    private ProtocolCodes code;
    private List<Chunk> data;

    public Packet(byte version, ProtocolCodes code, T data) throws IOException {
        this.version = version;
        this.code = code;
        this.data = chunkerize(data);
    }

    public byte getVersion() {
        return version;
    }

    public ProtocolCodes getCode() {
        return code;
    }

    public List<Chunk> getData() {
        return data;
    }

    public <T> T buildObject() throws IOException, ClassNotFoundException {
        List<Byte> rawData = new ArrayList<>();
        for (Chunk chunk : data) {
            for (byte b : chunk.getData()) {
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

    private List<Chunk> chunkerize(T data) throws IOException {
        if (data == null) return new ArrayList<>();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(data);
        byte[] rawData = baos.toByteArray();

        int nChunks = rawData.length / MAX_CHUNK_LEN + 1;

        int index = 0;
        List<Chunk> chunks = new ArrayList<>(nChunks);
        for (int i = 0; i < nChunks; i++) {
            byte[] arr = new byte[MAX_CHUNK_LEN];
            for (int j = 0; j < Math.min(rawData.length / (MAX_CHUNK_LEN * i), MAX_CHUNK_LEN); j++) {
                arr[j] = rawData[index];
                index++;
            }
            Chunk fdp = new Chunk(new PositiveByte((byte) (arr.length % 256)), new PositiveByte((byte) (arr.length / 256)), arr);
            chunks.add(fdp);
        }
        return chunks;
    }


}