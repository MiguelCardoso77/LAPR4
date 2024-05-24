package core.protocol;

import java.io.Serializable;

public class DataChunk implements Serializable {
    private UnsignedInteger dataLengthL;
    private UnsignedInteger dataLenghtM;
    private byte[] data;

    public DataChunk(UnsignedInteger dataLengthL, UnsignedInteger dataLenghtM, byte[] data) {
        this.dataLengthL = dataLengthL;
        this.dataLenghtM = dataLenghtM;
        this.data = data;
    }

    public UnsignedInteger dataLenL() {
        return dataLengthL;
    }

    public UnsignedInteger dataLenM() {
        return dataLenghtM;
    }

    public byte[] data() {
        return data;
    }
}
