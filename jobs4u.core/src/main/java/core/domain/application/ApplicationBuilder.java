package core.domain.application;

import core.domain.candidate.TelephoneNumber;
import core.domain.jobOpening.JobReference;
import eapli.framework.domain.model.DomainFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;

/**
 * Builder for creating instances of the {@link Application} class.
 *
 * @author Tomás Gonçalves
 */
public class ApplicationBuilder implements DomainFactory<Application> {

    private IdApplication idApplication;
    private Rank rank;
    private SubmissionDate submissionDate;
    private Status status;
    private ApplicationDataFile applicationDataFile;
    private FilesAttachedContent filesAttachedContent;
    private EmailFilesAttached emailFilesAttached;
    private EmailContentFile emailContentFile;
    private JobReference jobReference;

    private TelephoneNumber telephoneNumber;


    private static final Logger LOGGER = LogManager.getLogger(Application.class);

    /**
     * Sets all attributes of the ApplicationBuilder.
     *
     * @param idApplication         The application ID.
     * @param rank                  The rank of the application.
     * @param submissionDate        The submission date of the application.
     * @param status                The status of the application.
     * @param applicationDataFile   The data file associated with the application.
     * @param filesAttachedContent  The content of files attached to the application.
     * @param emailFilesAttached    The files attached to the application via email.
     * @param emailContentFile      The content of the email attached to the application.
     * @param jobReference          The reference to the job associated with the application.
     * @param telephoneNumber       The telephone number of the candidate.
     * @return                      The ApplicationBuilder instance with all attributes set.
     */
    public ApplicationBuilder withAll(long idApplication, int rank, Calendar submissionDate, Status status, String applicationDataFile, String filesAttachedContent, String emailFilesAttached, String emailContentFile, String jobReference , String telephoneNumber) {
        this.idApplication = new IdApplication(idApplication);
        this.rank = new Rank(rank);
        this.submissionDate = new SubmissionDate(submissionDate);
        this.status = status;
        this.applicationDataFile = new ApplicationDataFile(applicationDataFile);
        this.filesAttachedContent = new FilesAttachedContent(filesAttachedContent);
        this.emailFilesAttached = new EmailFilesAttached(emailFilesAttached);
        this.emailContentFile = new EmailContentFile(emailContentFile);
        this.jobReference = new JobReference(jobReference);
        this.telephoneNumber = new TelephoneNumber(telephoneNumber);
        return this;
    }


    /**
     * Constructs an {@link Application} instance based on the provided data.
     *
     * @return The created application.
     */
    @Override
    public Application build() {
        Application application;

        if (idApplication == null || rank == null || submissionDate == null || status == null || applicationDataFile == null || filesAttachedContent == null || emailFilesAttached == null || emailContentFile == null) {
            LOGGER.error("Missing mandatory information to build a JobOpening");
            return null;
        } else {
            LOGGER.debug("Building JobOpening with reference {}, description {}, vacancies number {}, adress {}, mode {}, contract type {}, title or function {}, telephone number{}", idApplication, rank, submissionDate, status, applicationDataFile, filesAttachedContent, emailFilesAttached, emailContentFile,jobReference, telephoneNumber);
            application = new Application(idApplication, rank, submissionDate, status, applicationDataFile, filesAttachedContent, emailFilesAttached, emailContentFile, jobReference , telephoneNumber);
        }

        return application;
    }
}