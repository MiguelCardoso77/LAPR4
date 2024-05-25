package core.services;

import core.domain.application.*;
import core.domain.candidate.Candidate;
import core.domain.company.Company;
import core.domain.customer.Customer;
import core.domain.jobOpening.JobOpening;
import core.persistence.PersistenceContext;
import core.repositories.ApplicationRepository;
import core.repositories.CustomerRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing job applications.
 * This class provides methods for registering, retrieving, and listing job applications.
 *
 * @author 1220812@isep.ipp.pt
 */
@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository = PersistenceContext.repositories().applications();
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customerUsers();

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

    public List<Application> findApplicationsForJobOpening(JobOpening jobOpening) {
        List<Application> applications = new ArrayList<>();

        for (Application a : applicationRepository.allApplications()) {
            if (a.jobReference().jobReference().equals(jobOpening.jobReference())) {
                applications.add(a);
            }
        }

        return applications;
    }

    public Application updateRank(int rank, Application application) {
        application.updateRank(rank);
        return applicationRepository.save(application);
    }

    public List<Application> applicationsByCM(SystemUser cm) {
        List<Application> applications = new ArrayList<>();
        List<Customer> customers = (List<Customer>) customerRepository.findAll();

        for (Application a : applicationRepository.allApplications()) {
            Company company = a.jobReference().customer().company();

            for (Customer c : customers) {
                if (c.company().equals(company) && c.customerManager().equals(cm)) {
                    if (!applications.contains(a)) {
                        applications.add(a);
                    }
                }
            }
        }

        return applications;
    }

    public List<Application> applicationsByCandidate(Candidate candidate) {
        List<Application> applications = new ArrayList<>();

        for (Application a : applicationRepository.allApplications()) {
            if (a.candidate().equals(candidate)) {
                applications.add(a);
            }
        }

        return applications;
    }
}
