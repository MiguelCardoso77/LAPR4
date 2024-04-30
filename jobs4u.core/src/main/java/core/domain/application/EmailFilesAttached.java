package core.domain.application;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

/**
 * Represents email files attached to an email.
 * This class is a value object.
 *
 * @author Tomás Gonçalves
 */
@Embeddable
public class EmailFilesAttached implements ValueObject {
    private String emailFilesAttached;

    /**
     * Constructs an `EmailFilesAttached` instance.
     *
     * @param emailFilesAttached The email files attached.
     * @throws IllegalArgumentException if `emailFilesAttached` is null or empty.
     */
    public EmailFilesAttached(final String emailFilesAttached) {
        if (emailFilesAttached == null || emailFilesAttached.isEmpty()) {
            throw new IllegalArgumentException("EmailFilesAttached should neither be null nor empty");
        }

        this.emailFilesAttached = emailFilesAttached;
    }

    /**
     * Default constructor for ORM (Object-Relational Mapping).
     */
    protected EmailFilesAttached() {
        // for ORM
    }

    /**
     * Creates an `EmailFilesAttached` instance from a given string.
     *
     * @param emailFilesAttached The email files attached.
     * @return An `EmailFilesAttached` instance.
     */
    public static EmailFilesAttached valueOf(final String emailFilesAttached) {
        return new EmailFilesAttached(emailFilesAttached);
    }

    /**
     * Checks if this `EmailFilesAttached` is equal to another object.
     *
     * @param o The object to compare.
     * @return `true` if equal, otherwise `false`.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmailFilesAttached)) {
            return false;
        }

        final EmailFilesAttached that = (EmailFilesAttached) o;
        return this.emailFilesAttached.equals(that.emailFilesAttached);
    }

    /**
     * Computes the hash code for this `EmailFilesAttached`.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return this.emailFilesAttached.hashCode();
    }

    /**
     * Returns the string representation of this `EmailFilesAttached`.
     *
     * @return The email files attached.
     */
    @Override
    public String toString() {
        return emailFilesAttached;
    }
}