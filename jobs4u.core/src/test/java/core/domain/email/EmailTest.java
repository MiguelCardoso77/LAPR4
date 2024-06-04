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
    void testCreateEmail() {
        String candidateEmail = "test@example.com";
        String subject = "Test Subject";
        String body = "Test Body";

        Email email = new Email(candidateEmail, subject, body);

        assertEquals(candidateEmail, email.toWho());
        assertEquals(subject, email.subject());
        assertEquals(body, email.body());
    }

    @Test
    void testEmailToString() {
        String candidateEmail = "test@example.com";
        String subject = "Test Subject";
        String body = "Test Body";

        Email email = new Email(candidateEmail, subject, body);

        String expected = "To: " + candidateEmail + "\n" +
                "Subject: " + subject + "\n\n" +
                body + "\n";
        assertEquals(expected, email.toString());
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
}