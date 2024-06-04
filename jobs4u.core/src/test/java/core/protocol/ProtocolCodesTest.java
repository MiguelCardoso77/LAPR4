package core.protocol;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProtocolCodesTest {

    @Test
    void testCommtestCode() {
        assertEquals((byte) 0, ProtocolCodes.COMMTEST.code());
    }

    @Test
    void testDisconnectCode() {
        assertEquals((byte) 1, ProtocolCodes.DISCONNECT.code());
    }

    @Test
    void testAckCode() {
        assertEquals((byte) 2, ProtocolCodes.ACK.code());
    }

    @Test
    void testErrCode() {
        assertEquals((byte) 3, ProtocolCodes.ERR.code());
    }

    @Test
    void testAuthCode() {
        assertEquals((byte) 4, ProtocolCodes.AUTH.code());
    }

    @Test
    void testEmailCode() {
        assertEquals((byte) 5, ProtocolCodes.EMAIL.code());
    }

    @Test
    void testListApplicationsCode() {
        assertEquals((byte) 6, ProtocolCodes.APPLICATIONS.code());
    }

    @Test
    void testListJobOpeningsCode() {
        assertEquals((byte) 7, ProtocolCodes.LIST_JOB_OPENINGS.code());
    }

}