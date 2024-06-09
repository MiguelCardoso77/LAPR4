package core.protocol;

/**
 * Represents the protocol codes used in the Jobs4U system.
 * This enum provides different codes that are used for communication in the Jobs4U system.
 * Each code is associated with a specific byte value.
 *
 * @author Miguel Cardoso
 */
public enum ProtocolCodes {
    COMMTEST((byte) 0),
    DISCONNECT((byte) 1),
    ACK((byte) 2),
    ERR((byte) 3),
    AUTH((byte) 4),
    EMAIL((byte) 5),
    APPLICATIONS((byte) 6),
    JOB_OPENINGS((byte) 7),
    NEW_NOTIFICATIONS((byte) 8),
    OLD_NOTIFICATIONS((byte) 9);
    private final byte code;

    /**
     * Constructs a ProtocolCodes instance with the given byte code.
     *
     * @param code The byte code associated with the protocol code.
     */
    ProtocolCodes(byte code) {
        this.code = code;
    }

    /**
     * Returns the byte code associated with the protocol code.
     *
     * @return The byte code.
     */
    public byte code() {
        return code;
    }
}