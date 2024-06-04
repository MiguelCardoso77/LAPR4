package core.domain.application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void testSubmitted() {
        Status status = Status.RECEIVED;
        assertEquals("RECEIVED", status.toString());
    }

    @Test
    public void testPending() {
        Status status = Status.CHOSEN;
        assertEquals("CHOSEN", status.toString());
    }

    @Test
    public void testAccepted() {
        Status status = Status.ACCEPTED;
        assertEquals("ACCEPTED", status.toString());
    }

    @Test
    public void testDeclined() {
        Status status = Status.DECLINED;
        assertEquals("DECLINED", status.toString());
    }
}
