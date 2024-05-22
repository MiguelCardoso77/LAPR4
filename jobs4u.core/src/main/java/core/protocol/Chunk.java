package core.protocol;

import java.io.Serializable;

public class Chunk implements Serializable {
    private PositiveByte lenL;
    private PositiveByte lenM;
    private byte[] data;

    public Chunk(PositiveByte lenL, PositiveByte lenM, byte[] data) {
        this.lenL = lenL;
        this.lenM = lenM;
        this.data = data;
    }

    public PositiveByte getLenL() {
        return lenL;
    }

    public PositiveByte getLenM() {
        return lenM;
    }

    public byte[] getData() {
        return data;
    }
}
