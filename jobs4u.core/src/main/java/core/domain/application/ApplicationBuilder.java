package core.domain.application;

import core.domain.candidate.Candidate;
import core.domain.jobOpening.JobOpening;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;


import java.util.Calendar;

/**
 * Builder for creating instances of the {@link Application} class.
 *
 * @author Tomás Gonçalves
 */
public class ApplicationBuilder implements DomainFactory<Application> {

    private Rank rank;
    private Calendar submissionDate;
    private Status status;
    private String applicationFiles;
    private JobOpening jobReference;
    private Candidate telephoneNumber;

    private SystemUser operator;

    /**
     * Sets all the attributes of the application builder.
     *
     * @param rank             the rank of the candidate in the application
     * @param applicationFiles the files attached to the application
     * @param jobReference     the job opening associated with the application
     * @param telephoneNumber  the candidate who submitted the application
     * @param operator         the operator who registered the application
     * @return this application builder instance
     */
    public ApplicationBuilder withAll( final Rank rank,
                                      final String applicationFiles,
                                      final JobOpening jobReference , final Candidate telephoneNumber, final SystemUser operator) {
        this.rank = rank;
        this.submissionDate = Calendar.getInstance();
        this.status = Status.RECEIVED;
        this.applicationFiles = applicationFiles;
        this.jobReference = jobReference;
        this.telephoneNumber = telephoneNumber;
        this.operator = operator;
        return this;
    }

    /**
     * Constructs an {@link Application} instance based on the provided data.
     *
     * @return The created application.
     */
    @Override
    public Application build() {
        return new Application(rank, submissionDate, applicationFiles, jobReference, telephoneNumber, operator);
    }
}