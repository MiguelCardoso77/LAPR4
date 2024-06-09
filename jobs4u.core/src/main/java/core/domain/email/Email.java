package core.domain.email;

import eapli.framework.validations.Preconditions;

import java.io.Serializable;

/**
 * Represents an email.
 * This class provides methods for getting the recipient, subject, and body of the email, and for getting a string representation of the email.
 *
 * @author Miguel Cardoso
 */
public class Email implements Serializable {
    private String toWho;
    private String subject;
    private String body;

    /**
     * Constructs an Email object with the specified recipient, subject, and body.
     *
     * @param toWho The recipient of the email.
     * @param subject The subject of the email.
     * @param body The body of the email.
     */
    public Email(final String toWho, final String subject, final String body) {
        Preconditions.nonNull(toWho);
        Preconditions.nonNull(subject);
        Preconditions.nonNull(body);

        this.toWho = toWho;
        this.subject = subject;
        this.body = body;
    }

    /**
     * Protected constructor for ORM usage.
     */
    protected Email() {
        // for ORM
    }

    /**
     * Retrieves the recipient of this email.
     *
     * @return The recipient of this email.
     */
    public String toWho() {
        return toWho;
    }

    /**
     * Retrieves the subject of this email.
     *
     * @return The subject of this email.
     */
    public String subject() {
        return subject;
    }

    /**
     * Retrieves the body of this email.
     *
     * @return The body of this email.
     */
    public String body() {
        return body;
    }

    /**
     * Returns a string representation of this email.
     *
     * @return A string representation of this email.
     */
    @Override
    public String toString() {
        return "To: " + toWho + "\n" +
                "Subject: " + subject + "\n\n" +
                body + "\n";
    }

}
