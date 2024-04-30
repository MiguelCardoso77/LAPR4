package core.domain.application;

import eapli.framework.domain.model.ValueObject;

import java.util.Calendar;

/**
 * Represents a submission date for a specific task or assignment.
 * This class is a value object, meaning it is immutable and can be compared for equality.
 *
 * @author Tomás Gonçalves
 */
public class SubmissionDate implements ValueObject, Comparable<SubmissionDate> {

    private Calendar submissionDate;

    /**
     * Constructs a `SubmissionDate` with the given calendar date.
     *
     * @param submissionDate The submission date (must not be null).
     * @throws IllegalArgumentException if the submission date is null.
     */
    public SubmissionDate(final Calendar submissionDate) {
        if (submissionDate == null) {
            throw new IllegalArgumentException("Submission date cannot be null");
        }

        this.submissionDate = submissionDate;
    }

    /**
     * Default constructor for ORM mapping.
     */
    protected SubmissionDate() {
        // for ORM
    }

    /**
     * Factory method to create a `SubmissionDate` from a calendar date.
     *
     * @param submissionDate The submission date (must not be null).
     * @return A new `SubmissionDate` instance.
     */
    public static SubmissionDate valueOf(final Calendar submissionDate) {
        return new SubmissionDate(submissionDate);
    }

    /**
     * Checks if this `SubmissionDate` is equal to another object.
     *
     * @param o The object to compare.
     * @return true if the objects are equal, false otherwise.
     */
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

    /**
     * Computes the hash code for this `SubmissionDate`.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return this.submissionDate.hashCode();
    }

    /**
     * Returns a string representation of this `SubmissionDate`.
     *
     * @return The string representation of the submission date.
     */
    @Override
    public String toString() {
        return this.submissionDate.toString();
    }

    /**
     * Compares this `SubmissionDate` with another `SubmissionDate`.
     *
     * @param arg0 The other `SubmissionDate` to compare.
     * @return A negative integer, zero, or a positive integer if this date is before, equal to, or after the other date.
     */
    @Override
    public int compareTo(final SubmissionDate arg0) {
        return this.submissionDate.compareTo(arg0.submissionDate);
    }
}
