package core.domain.application;

import eapli.framework.domain.model.ValueObject;

import java.util.Date;

public class SubmissionDate implements ValueObject, Comparable<SubmissionDate> {

    private Date submissionDate;

    public SubmissionDate(final Date submissionDate) {
        if (submissionDate == null) {
            throw new IllegalArgumentException("Submission date cannot be null");
        }

        this.submissionDate = new Date(submissionDate.getTime()); // Clone the date to avoid mutability issues
    }

    protected SubmissionDate() {
        // for ORM
    }

    public static SubmissionDate valueOf(final Date submissionDate) {
        return new SubmissionDate(submissionDate);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubmissionDate)) {
            return false;
        }

        final SubmissionDate that = (SubmissionDate) o;
        return this.submissionDate.equals(that.submissionDate);
    }

    @Override
    public int hashCode() {
        return this.submissionDate.hashCode();
    }

    @Override
    public String toString() {
        return this.submissionDate.toString();
    }

    @Override
    public int compareTo(final SubmissionDate arg0) {
        return this.submissionDate.compareTo(arg0.submissionDate);
    }
}
