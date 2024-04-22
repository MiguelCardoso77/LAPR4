package core.jobOpening.domain;

import eapli.framework.domain.model.ValueObject;

import java.util.Random;

public class JobReference implements ValueObject, Comparable<JobReference> {
    private int jobReference;

    public JobReference(final int companyNumber) {
        if (companyNumber < 0) {
            throw new IllegalArgumentException("Company Number should be a positive number");
        }

        jobReference = buildJobReference(companyNumber);
    }

    protected JobReference() {
        // for ORM
    }

    private int buildJobReference(final int companyNumber) {
        Random rand = new Random(); // Generate random 5 digit code
        int randomPart = rand.nextInt(90000) + 10000;

        return Integer.parseInt(companyNumber + String.valueOf(randomPart));
    }

    public static JobReference valueOf(final int jobReference) {
        return new JobReference(jobReference);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JobReference)) {
            return false;
        }

        final JobReference that = (JobReference) o;
        return this.jobReference == that.jobReference;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.jobReference);
    }

    @Override
    public String toString() {
        return Integer.toString(this.jobReference);
    }

    @Override
    public int compareTo(final JobReference arg0) {
        return Integer.compare(jobReference, arg0.jobReference);
    }

}
