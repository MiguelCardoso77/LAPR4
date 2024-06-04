package core.services;

import core.domain.email.Email;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailServiceTest {
    private final EmailService emailService = new EmailService();

    @Test
    void testCreateEmail() {
        String candidateEmail = "test@example.com";
        String subject = "Test Subject";
        String body = "Test Body";

        Email email = emailService.createEmail(candidateEmail, subject, body);

        assertEquals(candidateEmail, email.toWho());
        assertEquals(subject, email.subject());
        assertEquals(body, email.body());
    }
}