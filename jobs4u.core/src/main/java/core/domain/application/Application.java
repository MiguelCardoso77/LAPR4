package core.domain.application;

import core.domain.jobOpening.JobReference;
import eapli.framework.domain.model.AggregateRoot;
import jakarta.persistence.*;

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

    public Application(IdApplication idApplication, Rank rank, SubmissionDate submissionDate, Status status,
                       ApplicationDataFile applicationDataFile, FilesAttachedContent filesAttachedContent,
                       EmailFilesAttached emailFilesAttached, EmailContentFile emailContentFile, JobReference jobReference) {
        this.idApplication = idApplication;
        this.rank = rank;
        this.submissionDate = submissionDate;
        this.status = status;
        this.applicationDataFile = applicationDataFile;
        this.filesAttachedContent = filesAttachedContent;
        this.emailFilesAttached = emailFilesAttached;
        this.emailContentFile = emailContentFile;
        this.jobReference = jobReference;
    }

    protected Application() {
        // for ORM only
    }

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
                && emailContentFile.equals(that.emailContentFile) && jobReference.equals(that.jobReference);
    }

    public int compareTo(IdApplication other) {
        return AggregateRoot.super.compareTo(other);
    }

    @Override
    public IdApplication identity() {
        return null;
    }

    public boolean hasIdentity(IdApplication id) {
        return AggregateRoot.super.hasIdentity(id);
    }


    public IdApplication idApplication() {
        return this.idApplication;
    }

    public void changeIdApplication(IdApplication idApplication) {
        this.idApplication = idApplication;
    }

    public Rank rank() {
        return this.rank;
    }

    public void changeRank(Rank rank) {
        this.rank = rank;
    }

    public SubmissionDate submissionDate() {
        return this.submissionDate;
    }

    public void changeSubmissionDate(SubmissionDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Status status() {
        return this.status;
    }

    public void changeStatus(Status status) {
        this.status = status;
    }

    public ApplicationDataFile applicationDataFile() {
        return this.applicationDataFile;
    }

    public void changeApplicationDataFile(ApplicationDataFile applicationDataFile) {
        this.applicationDataFile = applicationDataFile;
    }

    public FilesAttachedContent filesAttachedContent() {
        return this.filesAttachedContent;
    }

    public void changeFilesAttachedContent(FilesAttachedContent filesAttachedContent) {
        this.filesAttachedContent = filesAttachedContent;
    }

    public EmailFilesAttached emailFilesAttached() {
        return this.emailFilesAttached;
    }

    public void changeEmailFilesAttached(EmailFilesAttached emailFilesAttached) {
        this.emailFilesAttached = emailFilesAttached;
    }

    public EmailContentFile emailContentFile() {
        return this.emailContentFile;
    }

    public void changeEmailContentFile(EmailContentFile emailContentFile) {
        this.emailContentFile = emailContentFile;
    }

    public JobReference jobReference() {
        return this.jobReference;
    }
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
                '}';
    }

}
