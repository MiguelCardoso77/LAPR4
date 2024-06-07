package core.domain.application;

import core.domain.candidate.Candidate;
import core.domain.jobOpening.JobOpening;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.*;

import java.util.Calendar;

/**
 * @author 1220812@isep.ipp.pt
 */

@Entity
@Table(name = "APPLICATION")
public class Application implements AggregateRoot<Integer> {

    /**
     * The unique identifier of the application.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int applicationID;

    /**
     * The rank of the candidate in the application.
     */
    @Column(name = "RANK_POSITION")
    private Rank rank;

    /**
     * The submission date of the application.
     */
    @Temporal(TemporalType.DATE)
    private Calendar submissionDate;

    /**
     * The status of the application.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;

    /**
     * The files attached to the application.
     */
    @Column(name = "DIRECTORY_PATH")
    private String applicationFiles;

    /**
     * The job opening associated with the application.
     */
    @ManyToOne
    @JoinColumn(name = "ASSOCIATED_JOB_OPENING")
    private JobOpening jobReference;

    /**
     * The candidate who submitted the application.
     */
    @ManyToOne
    @JoinColumn(name = "CANDIDATE_TELEPHONE_NUMBER")
    private Candidate candidate;

    /**
     * The operator who registered the application.
     */
    @ManyToOne
    @JoinColumn(name = "OPERATOR_EMAIL")
    private SystemUser operator;

    @Column(name = "Candidate_Requirements")
    private CandidateRequirements candidateRequirements;

    /**
     * Constructs an application with the specified parameters.
     *
     * @param rank             the rank of the candidate in the application
     * @param createdOn        the submission date of the application
     * @param applicationFiles the files attached to the application
     * @param jobReference     the job opening associated with the application
     * @param candidate        the candidate who submitted the application
     * @param operator         the operator who registered the application
     */
    public Application(final Rank rank, final Calendar createdOn,
                       final String applicationFiles, final JobOpening jobReference,
                       final Candidate candidate, final SystemUser operator) {
        Preconditions.noneNull(rank, createdOn, applicationFiles, jobReference, candidate);
        this.rank = rank;
        this.submissionDate = createdOn;
        this.applicationFiles = applicationFiles;
        this.jobReference = jobReference;
        this.candidate = candidate;
        this.operator = operator;
    }

    /**
     * Default constructor required by JPA.
     */
    protected Application() {
        // for ORM only
    }

    /**
     * Checks if this application is equal to another object.
     *
     * @param other the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    /**
     * Checks if this application is equal to another object.
     *
     * @param obj the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(final Object obj) {
        return DomainEntities.areEqual(this, obj);
    }

    /**
     * Generates a hash code for this application.
     *
     * @return the hash code value for this object
     */
    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    /**
     * Compares this application with the specified object for order.
     *
     * @param other the object to be compared
     * @return a negative integer, zero, or a positive integer as this application
     * is less than, equal to, or greater than the specified object
     */
    @Override
    public int compareTo(Integer other) {
        return AggregateRoot.super.compareTo(other);
    }

    /**
     * Retrieves the identity of this application.
     *
     * @return the identity of this application
     */
    @Override
    public Integer identity() {
        return applicationID;
    }

    /**
     * Retrieves the rank of the candidate in this application.
     *
     * @return the rank of the candidate
     */
    public Rank rank() {
        return this.rank;
    }

    /**
     * Retrieves the submission date of this application.
     *
     * @return the submission date of the application
     */
    public Calendar submissionDate() {
        return this.submissionDate;
    }

    /**
     * Retrieves the status of this application.
     *
     * @return the status of the application
     */
    public Status status() {
        return this.status;
    }

    /**
     * Retrieves the files attached to this application.
     *
     * @return the files attached to the application
     */
    public String dataFile() {
        return this.applicationFiles;
    }

    /**
     * Retrieves the operator who registered this application.
     *
     * @return the operator who registered the application
     */
    public SystemUser operator() {
        return this.operator;
    }

    /**
     * Retrieves the candidate who submitted this application.
     *
     * @return the candidate who submitted the application
     */
    public Candidate candidate() {
        return this.candidate;
    }

    /**
     * Retrieves the job reference of the job opening.
     *
     * @return the job reference of the associated job opening
     */
    public JobOpening jobReference() {
        return this.jobReference;
    }

    public CandidateRequirements candidateRequirements() { return this.candidateRequirements; }

    public void updateRank(int rank) {
        this.rank = new Rank(rank);
    }

    public void changeStatus(Status status) {
        this.status = status;
    }

    /**
     * Generates a string representation of this application.
     *
     * @return a string representation of the application
     */
    @Override
    public String toString() {
        return "Application : " + applicationID +
                ", rank = " + rank +
                ", submitted at " + submissionDate +
                ", is " + status +
                ", submitted for the job opening " + jobReference +
                ", by the candidate " + candidate +
                ", registered by " + operator;
    }

    public String toStringServer() {
        return "Application: " + applicationID +
                " with status: " + status +
                " submitted for the job opening: " + jobReference.identity() + " ";
    }

    public void uploadCandidateRequirements(CandidateRequirements candidateRequirements) {
        Preconditions.nonNull(candidateRequirements, "Candidate Requirements answers cannot be null");
        this.candidateRequirements = candidateRequirements;
    }
}
