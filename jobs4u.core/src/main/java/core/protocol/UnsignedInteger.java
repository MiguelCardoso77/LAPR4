package core.protocol;

public class UnsignedInteger {
    private final byte value;

    public UnsignedInteger(byte value) {
        this.value = value;
    }

    public byte rawValue() {
        return value;
    }

    public int positiveValue() {
        return value & 0xFF;
    }
}
