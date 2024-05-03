package core.application.controllers;

import core.domain.application.Application;
import core.domain.customer.Customer;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import core.domain.user.Jobs4URoles;
import core.persistence.PersistenceContext;
import core.repositories.CustomerRepository;
import core.services.ApplicationService;
import core.services.JobOpeningService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller responsible for listing applications of a job opening.
 *
 * @author Tomás Gonçalves
 */
public class ListJobOpeningApplicationsController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final JobOpeningService jobserv = new JobOpeningService();
    private final ApplicationService appServ = new ApplicationService();



    /**
     * Retrieves all applications associated with a specific job opening.
     *
     * @param jobReference The reference to the job opening.
     * @return Iterable of applications associated with the specified job opening.
     */
    public Iterable<Application> allApplicationsOfJobOpening(JobReference jobReference) {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.CUSTOMER_MANAGER);
        Iterable<Application> allApplications = appServ.allApplications();

        List<Application> allApplicationsJobOpening = new ArrayList<>();
        for (Application a : allApplications) {
            if (a.jobReference().equals(jobReference)) {
                allApplicationsJobOpening.add(a);
            }
        }
        return  allApplicationsJobOpening;
    }

    /**
     * Finds a job opening by its reference.
     *
     * @param jobReference The reference to the job opening.
     * @return The job opening if found, otherwise null.
     */
    public JobOpening findJobOpening(JobReference jobReference) {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.CUSTOMER_MANAGER);
        Iterable<JobOpening> allJobOpenings = jobserv.allJobOpenings();
        for (JobOpening j : allJobOpenings) {
            if (j.jobReference().equals(jobReference)) {
                return j;
            }
        }
        return null;
    }

    /**
     * Finds an application by its ID.
     *
     * @param id The ID of the application.
     * @return The application if found, otherwise null.
     */
    public Application findApplicationByID(int id){
        Iterable<Application> allApplications = appServ.allApplications();
        for(Application application : allApplications){
            if (application.identity() == id){
                return application;
            }
        }
        return null;
    }


}