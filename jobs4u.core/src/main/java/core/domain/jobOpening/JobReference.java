package core.domain.jobOpening;

import eapli.framework.domain.model.ValueObject;

import java.util.Random;

public class JobReference implements ValueObject, Comparable<JobReference> {
    private String jobReference;

    public JobReference(final String companyNumber) {
        if (companyNumber == null || companyNumber.isEmpty()) {
            throw new IllegalArgumentException("Company Number should not be empty");
        }

        jobReference = buildJobReference(companyNumber);
    }

    protected JobReference() {
        // for ORM
    }

    public String buildJobReference(final String companyNumber) {
        Random rand = new Random(); // Generate random 5 digit code
        int randomPart = rand.nextInt(90000) + 10000;

        return companyNumber + "-" + randomPart;
    }

    public static JobReference valueOf(final String jobReference) {
        return new JobReference(jobReference);
    }

    @Override
    public String toString() {
        return jobReference;
    }

    @Override
    public int compareTo(JobReference o) {
        return jobReference.compareTo(o.jobReference);
    }

    @Override
    public int hashCode() {
        return jobReference.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        JobReference other = (JobReference) obj;
        return jobReference.equals(other.jobReference);
    }
}
