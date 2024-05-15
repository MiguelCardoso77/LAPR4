package core.domain.application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class StatusTest {

    @Test
    public void testSubmitted() {
        Status status = Status.SUBMITTED;
        assertEquals("SUBMITTED", status.toString());
    }

    @Test
    public void testPending() {
        Status status = Status.PENDING;
        assertEquals("PENDING", status.toString());
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
