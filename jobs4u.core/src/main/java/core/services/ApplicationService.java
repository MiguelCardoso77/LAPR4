package core.services;

import core.domain.application.*;
import core.domain.candidate.Candidate;
import core.domain.jobOpening.JobOpening;
import core.persistence.PersistenceContext;
import core.repositories.ApplicationRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import jakarta.transaction.Transactional;

/**
 * Service class for managing job applications.
 * This class provides methods for registering, retrieving, and listing job applications.
 *
 * @author 1220812@isep.ipp.pt
 */
public class ApplicationService {
    private final ApplicationRepository applicationRepository = PersistenceContext.repositories().applications();

    /**
     * Registers a new job application.
     *
     * @param rank              The rank of the application.
     * @param applicationFiles  The files associated with the application.
     * @param jobReference      The job opening reference for which the application is made.
     * @param candidate         The candidate who submitted the application.
     * @param operator          The system user who registered the application.
     * @return                  The registered application.
     */
    @Transactional
    public Application registerApplication(Rank rank, String applicationFiles, JobOpening jobReference, Candidate candidate, SystemUser operator){
        ApplicationBuilder applicationBuilder = new ApplicationBuilder();
        applicationBuilder.withAll(rank, applicationFiles, jobReference, candidate, operator);
        Application application = applicationBuilder.build();
        return applicationRepository.save(application);
    }

    /**
     * Finds an application by its ID.
     *
     * @param applicationID     The ID of the application to find.
     * @return                  The application with the specified ID, or {@code null} if not found.
     */
    public Application findApplicationById(int applicationID){
        Iterable<Application> applications = applicationRepository.allApplications();
        for(Application application : applications){
            if(application.identity().equals(applicationID)){
                return application;
            }
        }
        return null;
    }

    /**
     * Retrieves all applications.
     *
     * @return  An iterable collection of all applications.
     */
    public Iterable<Application> allApplications() {
        return applicationRepository.allApplications();
    }
}
