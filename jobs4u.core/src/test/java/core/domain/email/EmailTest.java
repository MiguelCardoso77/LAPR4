package core.domain.email;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {
    private Email email;
    private String toWho;
    private String subject;
    private String body;

    @BeforeEach
    void setUp() {
        toWho = "test@example.com";
        subject = "Test Subject";
        body = "Test Body";

        email = new Email(toWho, subject, body);
    }

    @Test
    void testToWho() {
        assertEquals(toWho, email.toWho());
    }

    @Test
    void testSubject() {
        assertEquals(subject, email.subject());
    }

    @Test
    void testBody() {
        assertEquals(body, email.body());
    }

    @Test
    void testToString() {
        String expectedString = "To: " + toWho + "\n" +
                "Subject: " + subject + "\n\n" +
                body + "\n";
        assertEquals(expectedString, email.toString());
    }

}