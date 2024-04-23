package core.domain.application;

import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import eapli.framework.domain.model.DomainFactory;
import jakarta.persistence.Id;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;


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


    private static final Logger LOGGER = LogManager.getLogger(Application.class);

    public ApplicationBuilder withAll(long idApplication, int rank, Calendar submissionDate, Status status, String applicationDataFile, String filesAttachedContent, String emailFilesAttached, String emailContentFile, String jobReference) {
        this.idApplication = new IdApplication(idApplication);
        this.rank = new Rank(rank);
        this.submissionDate = new SubmissionDate(submissionDate);
        this.status = status;
        this.applicationDataFile = new ApplicationDataFile(applicationDataFile);
        this.filesAttachedContent = new FilesAttachedContent(filesAttachedContent);
        this.emailFilesAttached = new EmailFilesAttached(emailFilesAttached);
        this.emailContentFile = new EmailContentFile(emailContentFile);
        this.jobReference = new JobReference(jobReference);
        return this;
    }


    @Override
    public Application build() {
        Application application;

        if (idApplication == null || rank == null || submissionDate == null || status == null || applicationDataFile == null || filesAttachedContent == null || emailFilesAttached == null || emailContentFile == null) {
            LOGGER.error("Missing mandatory information to build a JobOpening");
            return null;
        } else {
            LOGGER.debug("Building JobOpening with reference {}, description {}, vacancies number {}, adress {}, mode {}, contract type {}, title or function {}", idApplication, rank, submissionDate, status, applicationDataFile, filesAttachedContent, emailFilesAttached, emailContentFile,jobReference);
            application = new Application(idApplication, rank, submissionDate, status, applicationDataFile, filesAttachedContent, emailFilesAttached, emailContentFile, jobReference);
        }

        return application;
    }
}