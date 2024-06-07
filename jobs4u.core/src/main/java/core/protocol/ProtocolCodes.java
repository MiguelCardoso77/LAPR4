package core.protocol;

public enum ProtocolCodes {
    COMMTEST((byte) 0),
    DISCONNECT((byte) 1),
    ACK((byte) 2),
    ERR((byte) 3),
    AUTH((byte) 4),
    EMAIL((byte) 5),
    APPLICATIONS((byte) 6),
    JOB_OPENINGS((byte) 7);
    private final byte code;

    ProtocolCodes(byte code) {
        this.code = code;
    }

    public byte code() {
        return code;
    }
}