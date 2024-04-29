package core.domain.application;

import core.domain.candidate.TelephoneNumber;
import core.domain.jobOpening.JobReference;
import eapli.framework.domain.model.AggregateRoot;
import jakarta.persistence.*;

/**
 * Represents an application for a job opening.
 *
 * @author Tomás Gonçalves
 */

@Entity
@Table(name = "APPLICATION")
public class Application implements AggregateRoot<IdApplication> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private IdApplication idApplication;

    @Column(name = "RANK")
    private Rank rank;

    @Column(name = "SUBMISSION_DATE")
    private SubmissionDate submissionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;

    @Column(name = "APPLICATION_DATA_FILE")
    private ApplicationDataFile applicationDataFile;

    @Column(name = "FILES_ATTACHED_CONTENT")
    private FilesAttachedContent filesAttachedContent;

    @Column(name = "EMAIL_FILES_ATTACHED")
    private EmailFilesAttached emailFilesAttached;

    @Column(name = "EMAIL_CONTENT_FILE")
    private EmailContentFile emailContentFile;

    @Column(name = "ASSOCIATED_JOB_OPENING")
    private JobReference jobReference;

    @Column(name = "CANDIDATE")
    private TelephoneNumber telephoneNumber;


    /**
     * Constructs an application object.
     *
     * @param idApplication         The application ID.
     * @param rank                  The rank of the application.
     * @param submissionDate        The submission date of the application.
     * @param status                The status of the application.
     * @param applicationDataFile   The data file associated with the application.
     * @param filesAttachedContent  The attached files content.
     * @param emailFilesAttached    The files attached to the email.
     * @param emailContentFile      The content file of the email.
     * @param jobReference          The job reference associated with the application.
     * @param telephoneNumber       The telephone number of the candidate.
     */
    public Application(IdApplication idApplication, Rank rank, SubmissionDate submissionDate, Status status,
                       ApplicationDataFile applicationDataFile, FilesAttachedContent filesAttachedContent,
                       EmailFilesAttached emailFilesAttached, EmailContentFile emailContentFile, JobReference jobReference, TelephoneNumber telephoneNumber) {
        this.idApplication = idApplication;
        this.rank = rank;
        this.submissionDate = submissionDate;
        this.status = status;
        this.applicationDataFile = applicationDataFile;
        this.filesAttachedContent = filesAttachedContent;
        this.emailFilesAttached = emailFilesAttached;
        this.emailContentFile = emailContentFile;
        this.jobReference = jobReference;
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * Default constructor required by ORM.
     */
    protected Application() {
        // for ORM only
    }

    /**
     * Checks if this application is the same as another object.
     *
     * @param other The object to compare with.
     * @return True if the objects are the same, false otherwise.
     */
    public boolean sameAs(final Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Application)) {
            return false;
        }

        final Application that = (Application) other;

        return  idApplication.equals(that.idApplication) && rank.equals(that.rank) && submissionDate.equals(that.submissionDate)
                && status.equals(that.status) && applicationDataFile.equals(that.applicationDataFile)
                && filesAttachedContent.equals(that.filesAttachedContent) && emailFilesAttached.equals(that.emailFilesAttached)
                && emailContentFile.equals(that.emailContentFile) && jobReference.equals(that.jobReference)
                && telephoneNumber.equals(that.telephoneNumber);
    }

    /**
     * Compares this application to the specified ID.
     *
     * @param other The ID to compare with.
     * @return The comparison result.
     */
    public int compareTo(IdApplication other) {
        return AggregateRoot.super.compareTo(other);
    }

    @Override
    public IdApplication identity() {
        return null;
    }

    /**
     * Checks if this application has the specified ID.
     *
     * @param id The ID to check.
     * @return True if the application has the given ID, false otherwise.
     */
    public boolean hasIdentity(IdApplication id) {
        return AggregateRoot.super.hasIdentity(id);
    }

    /**
     * Retrieves the telephone number associated with this application.
     *
     * @return The telephone number of the candidate.
     */
    public TelephoneNumber telephoneNumber() {
        return telephoneNumber;
    }

    /**
     * Retrieves the ID of this application.
     *
     * @return The ID of the application.
     */
    public IdApplication idApplication() {
        return this.idApplication;
    }

    /**
     * Retrieves the rank of this application.
     *
     * @return The rank of the application.
     */
    public Rank rank() {
        return this.rank;
    }

    /**
     * Retrieves the submission date of this application.
     *
     * @return The submission date of the application.
     */
    public SubmissionDate submissionDate() {
        return this.submissionDate;
    }

    /**
     * Retrieves the status of this application.
     *
     * @return The status of the application.
     */
    public Status status() {
        return this.status;
    }

    /**
     * Retrieves the data file associated with this application.
     *
     * @return The data file associated with the application.
     */
    public ApplicationDataFile applicationDataFile() {
        return this.applicationDataFile;
    }

    /**
     * Retrieves the content of files attached to this application.
     *
     * @return The content of files attached to the application.
     */
    public FilesAttachedContent filesAttachedContent() {
        return this.filesAttachedContent;
    }

    /**
     * Retrieves the files attached to the email associated with this application.
     *
     * @return The files attached to the email.
     */
    public EmailFilesAttached emailFilesAttached() {
        return this.emailFilesAttached;
    }

    /**
     * Retrieves the content file of the email associated with this application.
     *
     * @return The content file of the email.
     */
    public EmailContentFile emailContentFile() {
        return this.emailContentFile;
    }

    /**
     * Retrieves the job reference associated with this application.
     *
     * @return The job reference associated with the application.
     */
    public JobReference jobReference() {
        return this.jobReference;
    }

    /**
     * Returns a string representation of this application.
     *
     * @return A string representation of the application.
     */
    @Override
    public String toString() {
        return "Application{" +
                "idApplication=" + idApplication +
                ", rank=" + rank +
                ", submissionDate=" + submissionDate +
                ", status=" + status +
                ", applicationDataFile=" + applicationDataFile +
                ", filesAttachedContent=" + filesAttachedContent +
                ", emailFilesAttached=" + emailFilesAttached +
                ", emailContentFile=" + emailContentFile +
                ", jobReference=" + jobReference +
                ", telephoneNumber= " + telephoneNumber +
                '}';
    }


}
