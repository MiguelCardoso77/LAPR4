package core.protocol;

import java.io.Serializable;

/**
 * Represents a data chunk with a specified length and data content.
 *
 * @author Diana Neves
 * **/
public class DataChunk implements Serializable {
    private UnsignedInteger dataLengthL;
    private UnsignedInteger dataLengthM;
    private final byte[] data;

    /**
     * Constructs a {@code DataChunk} with specified length metadata and data.
     *
     * @param dataLengthL the lower byte of the data length.
     * @param dataLengthM the higher byte of the data length.
     * @param data the byte array containing the data.
     */
    public DataChunk(UnsignedInteger dataLengthL, UnsignedInteger dataLengthM, byte[] data) {
        this.dataLengthL = dataLengthL;
        this.dataLengthM = dataLengthM;
        this.data = data;
    }

    /**
     * Constructs a {@code DataChunk} with specified data and no length metadata.
     *
     * @param data the byte array containing the data.
     */
    public DataChunk (byte[] data) {
        this.data = data;
    }

    /**
     * Returns the lower byte of the data length.
     *
     * @return the lower byte of the data length as an {@link UnsignedInteger}.
     */
    public UnsignedInteger dataLenL() {
        return dataLengthL;
    }

    /**
     * Returns the higher byte of the data length.
     *
     * @return the higher byte of the data length as an {@link UnsignedInteger}.
     */
    public UnsignedInteger dataLenM() {
        return dataLengthM;
    }

    /**
     * Returns the data contained in this chunk.
     *
     * @return a byte array containing the data.
     */
    public byte[] data() {
        return data;
    }
}