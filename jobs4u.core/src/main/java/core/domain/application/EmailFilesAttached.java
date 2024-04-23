package core.domain.application;

import eapli.framework.domain.model.ValueObject;

public class EmailFilesAttached  implements ValueObject {
    private String emailFilesAttached;

    public EmailFilesAttached(final String EmailFilesAttached) {
        if (emailFilesAttached == null || emailFilesAttached.isEmpty()) {
            throw new IllegalArgumentException("EmailFilesAttached should neither be null nor empty");
        }

        this.emailFilesAttached = EmailFilesAttached;
    }

    protected EmailFilesAttached() {
        // for ORM
    }

    public static EmailFilesAttached valueOf(final String EmailFilesAttached) {
        return new EmailFilesAttached(EmailFilesAttached);
    }

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

    @Override
    public int hashCode() {
        return this.emailFilesAttached.hashCode();
    }

    @Override
    public String toString() {
        return emailFilesAttached;
    }
}


