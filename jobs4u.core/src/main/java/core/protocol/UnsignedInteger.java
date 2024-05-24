package core.protocol;

public class UnsignedInteger {
    private byte value;

    public UnsignedInteger(byte value) {
        this.value = value;
    }

    public byte rawValue() {
        return value;
    }

    public int positiveValue() {
        if (value < 0) {
            int negVal = value;
            return negVal +  256;
        }
        return value;
    }
}
