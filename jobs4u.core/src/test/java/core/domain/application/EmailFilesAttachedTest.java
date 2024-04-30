package core.domain.application;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmailFilesAttachedTest {

    @Test
    void testEmailFilesAttachedEquality() {
        EmailFilesAttached email1 = EmailFilesAttached.valueOf("file1.txt");
        EmailFilesAttached email2 = EmailFilesAttached.valueOf("file1.txt");
        EmailFilesAttached email3 = EmailFilesAttached.valueOf("file2.txt");

        assertEquals(email1, email2); // Should be equal
        assertNotEquals(email1, email3); // Should not be equal
    }

    @Test
    void testEmailFilesAttachedHashCode() {
        EmailFilesAttached email1 = EmailFilesAttached.valueOf("file1.txt");
        EmailFilesAttached email2 = EmailFilesAttached.valueOf("file1.txt");

        assertEquals(email1.hashCode(), email2.hashCode());
    }

    @Test
    void testEmailFilesAttachedToString() {
        EmailFilesAttached email = EmailFilesAttached.valueOf("file1.txt");
        assertEquals("file1.txt", email.toString());
    }

    @Test
    void testInvalidEmailFilesAttached() {
        assertThrows(IllegalArgumentException.class, () -> {
            EmailFilesAttached.valueOf(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            EmailFilesAttached.valueOf("");
        });
    }
}
