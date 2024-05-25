package core.domain.jobOpening;

import eapli.framework.representations.dto.DTO;

import java.util.Date;

/**
 * Represents a job opening in the system.
 *
 * @author 1220812@isep.ipp.pt
 */
@DTO
public class JobOpeningDTO{
    public String jobReference;
    public String position;
    public Date activeSince;
    public int numberOfApplicants;

    /**
     * Creates a new instance of {@code JobOpeningDTO} with the provided attributes.
     *
     * @param jobReference job reference
     * @param position     position
     * @param activeSince  active since
     * @param numberOfApplicants number of applicants
     */
    public JobOpeningDTO(final String jobReference, final String position, final Date activeSince, final int numberOfApplicants){
        this.jobReference = jobReference;
        this.position = position;
        this.activeSince = activeSince;
        this.numberOfApplicants = numberOfApplicants;
    }

    @Override
    public String toString() {
        return "jobReference : " + jobReference +
                ", position : " + position +
                ", date of activation : " + activeSince +
                ", number of applicants = " + numberOfApplicants;
    }
}