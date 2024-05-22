package core.protocol;

public class PositiveByte {
    private byte value;

    public PositiveByte(byte value) {
        this.value = value;
    }

    public byte rawValue() {
        return value;
    }

    public int positiveValue() {
        if (value < 0) {
            int tmp = value;
            return tmp +  256;
        }
        return value;
    }
}
