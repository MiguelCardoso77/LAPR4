package core.domain.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {
    private Message message;

    @BeforeEach
    void setUp() {
        message = Message.valueOf("Test message");
    }

    @Test
    void testValueOf() {
        Message newMessage = Message.valueOf("New test message");
        assertEquals("New test message", newMessage.toString());
    }

    @Test
    void testEquals() {
        Message sameMessage = Message.valueOf("Test message");
        Message differentMessage = Message.valueOf("Different message");

        assertTrue(message.equals(sameMessage));
        assertFalse(message.equals(differentMessage));
    }

    @Test
    void testHashCode() {
        Message sameMessage = Message.valueOf("Test message");
        assertEquals(message.hashCode(), sameMessage.hashCode());
    }

    @Test
    void testToString() {
        assertEquals("Test message", message.toString());
    }
    @Test
    void testConstructor() {
        String expectedMessage = "Test message";

        Message message = new Message(expectedMessage);

        assertEquals(expectedMessage, message.toString());
    }
}