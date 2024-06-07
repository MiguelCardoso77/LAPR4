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
    /**
     * Returns the job reference of the job opening.
     *
     * @return The job reference of the job opening.
     */
    public String myJobReference(){
        return jobReference;
    }
    /**
     * Returns the position of the job opening.
     *
     * @return The position of the job opening.
     */
    public String myPosition(){
        return position;
    }
    /**
     * Returns the date when the job opening was activated.
     *
     * @return The date when the job opening was activated.
     */
    public Date myActiveSince(){
        return activeSince;
    }
    /**
     * Returns the number of applicants for the job opening.
     *
     * @return The number of applicants for the job opening.
     */
    public int myNumberOfApplicants(){
        return numberOfApplicants;
    }
    /**
     * Returns a string representation of the job opening.
     *
     * @return A string representation of the job opening.
     */
    @Override
    public String toString() {
        return jobReference + "                    " + activeSince + "    " + numberOfApplicants + "                                    " + position;
    }
}