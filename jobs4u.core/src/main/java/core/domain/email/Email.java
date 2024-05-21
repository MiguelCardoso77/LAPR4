package core.domain.email;

import eapli.framework.validations.Preconditions;

import java.io.Serializable;

public class Email implements Serializable {
    private String fromWho;
    private String toWho;
    private String subject;
    private String body;

    public Email(final String fromWho, final String toWho, final String subject, final String body) {
        Preconditions.nonNull(fromWho);
        Preconditions.nonNull(toWho);
        Preconditions.nonNull(subject);
        Preconditions.nonNull(body);

        this.fromWho = fromWho;
        this.toWho = toWho;
        this.subject = subject;
        this.body = body;
    }

    protected Email() {
        // for ORM
    }

    public String fromWho() {
        return fromWho;
    }

    public String toWho() {
        return toWho;
    }

    public String subject() {
        return subject;
    }

    public String body() {
        return body;
    }

    @Override
    public String toString() {
        return "From: " + fromWho + "\n" +
                "To: " + toWho + "\n" +
                "Subject: " + subject + "\n\n" +
                body + "\n";
    }
}
