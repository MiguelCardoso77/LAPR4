package core.domain.jobOpening;

import eapli.framework.domain.model.ValueObject;
import java.util.Random;

/**
 * Represents a job reference, which is a unique identifier for a job opening.
 *
 * @author Diana Neves
 */
public class JobReference implements ValueObject, Comparable<JobReference> {

    private String jobReference;

    /**
     * Default constructor for ORM (Object-Relational Mapping) purposes.
     */
    protected JobReference() {
        // for ORM
    }

    /**
     * Constructs a new JobReference based on the provided company name.
     *
     * @param companyName The name of the company.
     * @throws IllegalArgumentException If the company name is null or empty.
     */
    public JobReference(final String companyName) {
        if (companyName == null || companyName.isEmpty()) {
            throw new IllegalArgumentException("Company Number should not be empty");
        }
        String companyReference = buildCompanyReference(companyName);
        this.jobReference = buildJobReference(companyReference);
    }

    /**
     * Builds the company reference from the company name.
     *
     * @param companyName The name of the company.
     * @return The company reference (first 3 characters of the company name).
     */
    private String buildCompanyReference(String companyName) {
        String companyReference = null;
        if (companyName != null && companyName.length() >= 3) {
            companyReference = companyName.substring(0, 3);
        } else {
            System.out.println("Company name is either null or too short.");
        }
        return companyReference;
    }

    /**
     * Builds the complete job reference by combining the company reference and a random numeric part.
     *
     * @param companyReference The company reference.
     * @return The complete job reference.
     */
    public String buildJobReference(final String companyReference) {
        Random rand = new Random(); // Generate random 5 digit code
        int randomPart = rand.nextInt(90000) + 10000;

        return companyReference + "-" + randomPart;
    }

    /**
     * Creates a JobReference instance from an existing job reference string.
     *
     * @param jobReference The job reference string.
     * @return A JobReference instance.
     */
    public static JobReference valueOf(final String jobReference) {
        return new JobReference(jobReference);
    }

    /**
     * Returns the job reference string.
     *
     * @return The job reference string.
     */
    @Override
    public String toString() {
        return jobReference;
    }

    /**
     * Compares this job reference to another job reference.
     *
     * @param o The other job reference to compare to.
     * @return A negative integer, zero, or a positive integer if this job reference is less than,
     *         equal to, or greater than the specified job reference, respectively.
     */
    @Override
    public int compareTo(JobReference o) {
        return jobReference.compareTo(o.jobReference);
    }

    /**
     * Computes the hash code for this job reference.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return jobReference.hashCode();
    }

    /**
     * Checks if this job reference is equal to another object.
     *
     * @param obj The object to compare to.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
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
