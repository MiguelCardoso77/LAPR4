package core.protocol;

/**
 * Represents an unsigned integer stored in a byte.
 *
 * @author Diana Neves
 */
public class UnsignedInteger {
    private final byte value;

    /**
     * Constructs an {@code UnsignedInteger} with the specified byte value.
     *
     * @param value the byte value to be stored.
     */
    public UnsignedInteger(byte value) {
        this.value = value;
    }

    /**
     * Returns the raw byte value.
     * This is the value as it was originally stored, which may be a negative number.
     *
     * @return the raw byte value.
     */
    public byte rawValue() {
        return value;
    }

    /**
     * Returns the value as an unsigned integer.
     * This interprets the byte value as an unsigned 8-bit integer, which is always non-negative.
     *
     * @return the unsigned integer value.
     */
    public int positiveValue() {
        return value & 0xFF;
    }
}
