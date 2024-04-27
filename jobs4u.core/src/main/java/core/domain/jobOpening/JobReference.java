package core.domain.jobOpening;

import eapli.framework.domain.model.ValueObject;
import java.util.Random;

public class JobReference implements ValueObject, Comparable<JobReference> {

    private String jobReference;

    protected JobReference() {
        // for ORM
    }
    public JobReference(final String companyName) {
        if (companyName == null || companyName.isEmpty()) {
            throw new IllegalArgumentException("Company Number should not be empty");
        }
        String companyReference = buildCompanyReference(companyName);
        this.jobReference = buildJobReference(companyReference);
    }

    private String buildCompanyReference(String companyName) {
        String companyReference = null;
        if (companyName != null && companyName.length() >= 3) {
            companyReference = companyName.substring(0, 3);
        } else {
            System.out.println("Company name is either null or too short.");
        }
        return companyReference;
    }



    public String buildJobReference(final String companyReference) {
        Random rand = new Random(); // Generate random 5 digit code
        int randomPart = rand.nextInt(90000) + 10000;

        return companyReference + "-" + randomPart;
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
