package core.protocol;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnsignedIntegerTest {

    @Test
    void testRawValue() {
        byte value = (byte) 128;
        UnsignedInteger unsignedInteger = new UnsignedInteger(value);

        assertEquals(value, unsignedInteger.rawValue());
    }

    @Test
    void testPositiveValue() {
        byte value = (byte) 128;
        UnsignedInteger unsignedInteger = new UnsignedInteger(value);

        int expectedPositiveValue = 128;
        assertEquals(expectedPositiveValue, unsignedInteger.positiveValue());
    }

    @Test
    void testPositiveValueWithZero() {
        byte value = (byte) 0;
        UnsignedInteger unsignedInteger = new UnsignedInteger(value);

        int expectedPositiveValue = 0;
        assertEquals(expectedPositiveValue, unsignedInteger.positiveValue());
    }

    @Test
    void testPositiveValueWithMaxByte() {
        byte value = (byte) 255;
        UnsignedInteger unsignedInteger = new UnsignedInteger(value);

        int expectedPositiveValue = 255;
        assertEquals(expectedPositiveValue, unsignedInteger.positiveValue());
    }

    @Test
    void testPositiveValueWithNegativeByte() {
        byte value = (byte) -1;
        UnsignedInteger unsignedInteger = new UnsignedInteger(value);

        int expectedPositiveValue = 255;
        assertEquals(expectedPositiveValue, unsignedInteger.positiveValue());
    }

}