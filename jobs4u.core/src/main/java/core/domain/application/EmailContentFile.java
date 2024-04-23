package core.domain.application;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

@Embeddable
public class EmailContentFile implements ValueObject {
    private String EmailContentFile;

    public EmailContentFile(final String EmailContentFile) {
        if (EmailContentFile == null || EmailContentFile.isEmpty()) {
            throw new IllegalArgumentException("EmailContentFile should neither be null nor empty");
        }

        this.EmailContentFile = EmailContentFile;
    }

    protected EmailContentFile() {
        // for ORM
    }

    public static EmailContentFile valueOf(final String EmailContentFile) {
        return new EmailContentFile(EmailContentFile);
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
        return this.EmailContentFile.equals(that.EmailContentFile);
    }

    @Override
    public int hashCode() {
        return this.EmailContentFile.hashCode();
    }

    @Override
    public String toString() {
        return EmailContentFile;
    }
}




