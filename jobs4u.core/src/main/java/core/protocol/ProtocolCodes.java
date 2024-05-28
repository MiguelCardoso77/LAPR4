package core.protocol;

public enum ProtocolCodes {
    COMMTEST((byte) 0),
    DISCONNECT((byte) 1),
    ACK((byte) 2),
    ERR((byte) 3),
    AUTH((byte) 4),
    LIST_APPLICATIONS((byte) 5),
    LIST_APPLICATIONS_RESPONSE((byte) 6);

    private final byte code;

    ProtocolCodes(byte code) {
        this.code = code;
    }

    public byte code() {
        return code;
    }
}