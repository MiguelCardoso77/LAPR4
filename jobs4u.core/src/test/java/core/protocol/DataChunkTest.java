package core.protocol;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataChunkTest {

    @Test
    void testConstructorWithUnsignedIntegers() {
        UnsignedInteger dataLengthL = new UnsignedInteger((byte) 10);
        UnsignedInteger dataLengthM = new UnsignedInteger((byte) 20);
        byte[] data = new byte[] {1, 2, 3, 4, 5};

        DataChunk dataChunk = new DataChunk(dataLengthL, dataLengthM, data);

        assertEquals(dataLengthL, dataChunk.dataLenL());
        assertEquals(dataLengthM, dataChunk.dataLenM());
        assertArrayEquals(data, dataChunk.data());
    }

    @Test
    void testConstructorWithDataOnly() {
        byte[] data = new byte[] {1, 2, 3, 4, 5};

        DataChunk dataChunk = new DataChunk(data);

        assertNull(dataChunk.dataLenL());
        assertNull(dataChunk.dataLenM());
        assertArrayEquals(data, dataChunk.data());
    }

    @Test
    void testDataLenL() {
        UnsignedInteger dataLengthL = new UnsignedInteger((byte) 10);
        DataChunk dataChunk = new DataChunk(dataLengthL, null, new byte[0]);

        assertEquals(dataLengthL, dataChunk.dataLenL());
    }

    @Test
    void testDataLenM() {
        UnsignedInteger dataLengthM = new UnsignedInteger((byte) 20);
        DataChunk dataChunk = new DataChunk(null, dataLengthM, new byte[0]);

        assertEquals(dataLengthM, dataChunk.dataLenM());
    }

    @Test
    void testData() {
        byte[] data = new byte[] {1, 2, 3, 4, 5};
        DataChunk dataChunk = new DataChunk(data);

        assertArrayEquals(data, dataChunk.data());
    }

}