package core.services;

import core.domain.application.*;
import core.domain.jobOpening.JobReference;
import core.persistence.PersistenceContext;
import core.repositories.ApplicationRepository;
import jakarta.transaction.Transactional;

import java.util.Calendar;

public class ApplicationService {
    private final ApplicationRepository applicationRepository = PersistenceContext.repositories().applications();

    @Transactional
    public Application registerApplication(long idApplication, int rank, Calendar submissionDate, Status status,
                                           String applicationDataFile, String filesAttachedContent, String emailFilesAttached,
                                           String emailContentFile, String jobReference){

        ApplicationBuilder applicationBuilder = new ApplicationBuilder();
        applicationBuilder.withAll(idApplication,rank,submissionDate,status,applicationDataFile,filesAttachedContent,
                emailFilesAttached,emailContentFile,jobReference);
        Application application = applicationBuilder.build();
        return applicationRepository.save(application);
    }

    public Iterable<Application> allApplication() {
        return applicationRepository.allApplications();
    }
}