package core.domain.application;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

/**
 * Represents the content of an email file.
 * <p>
 * This class is a value object used to encapsulate the content of an email file.
 */
@Embeddable
public class EmailContentFile implements ValueObject {
    private String emailContentFile;

    /**
     * Constructs an EmailContentFile object with the given content.
     *
     * @param emailContentFile the content of the email file
     * @throws IllegalArgumentException if the content is null or empty
     */
    public EmailContentFile(final String emailContentFile) {
        if (emailContentFile == null || emailContentFile.isEmpty()) {
            throw new IllegalArgumentException("EmailContentFile should neither be null nor empty");
        }

        this.emailContentFile = emailContentFile;
    }

    /**
     * Default constructor required by ORM.
     */
    protected EmailContentFile() {
        // for ORM
    }

    /**
     * Creates an EmailContentFile object with the given content.
     *
     * @param emailContentFile the content of the email file
     * @return the EmailContentFile object
     */
    public static EmailContentFile valueOf(final String emailContentFile) {
        return new EmailContentFile(emailContentFile);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmailContentFile)) {
            return false;
        }

        final EmailContentFile that = (EmailContentFile) o;
        return this.emailContentFile.equals(that.emailContentFile);
    }

    @Override
    public int hashCode() {
        return this.emailContentFile.hashCode();
    }

    @Override
    public String toString() {
        return emailContentFile;
    }
}
