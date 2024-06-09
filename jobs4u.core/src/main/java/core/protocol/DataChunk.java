package core.protocol;

import java.io.Serializable;

public class DataChunk implements Serializable {
    private UnsignedInteger dataLengthL;
    private UnsignedInteger dataLengthM;
    private final byte[] data;

    public DataChunk(UnsignedInteger dataLengthL, UnsignedInteger dataLengthM, byte[] data) {
        this.dataLengthL = dataLengthL;
        this.dataLengthM = dataLengthM;
        this.data = data;
    }

    public DataChunk (byte[] data) {
        this.data = data;
    }

    public UnsignedInteger dataLenL() {
        return dataLengthL;
    }

    public UnsignedInteger dataLenM() {
        return dataLengthM;
    }

    public byte[] data() {
        return data;
    }
}