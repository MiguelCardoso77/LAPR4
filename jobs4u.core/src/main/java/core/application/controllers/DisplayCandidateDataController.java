package core.application.controllers;

import core.domain.application.Application;
import core.domain.candidate.Candidate;
import core.domain.candidate.TelephoneNumber;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import core.domain.user.Jobs4URoles;
import core.services.ApplicationService;
import core.services.CandidateService;
import core.services.JobOpeningService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller responsible for displaying candidate data and managing job openings.
 *
 * @author Tomás Gonçalves
 */

public class DisplayCandidateDataController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final UserManagementService userSvc = AuthzRegistry.userService();
    private final JobOpeningService jobserv = new JobOpeningService();
    private final ApplicationService appServ = new ApplicationService();
    private final CandidateService candServ = new CandidateService();

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
        return allApplicationsJobOpening;
    }


    /**
     * Retrieves all job openings.
     *
     * @return Iterable of all job openings.
     */
    public Iterable<JobOpening> allJobOpening() {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.CUSTOMER_MANAGER);
        return jobserv.allJobOpenings();
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
     * Retrieves the candidate associated with an application.
     *
     * @param application The application to retrieve the candidate from.
     * @return The candidate associated with the application, or null if not found.
     */
    public Candidate candidateOfApplication(Application application) {
        if (application != null) {
            TelephoneNumber telephoneNumber = application.candidate().identity();
            if (telephoneNumber != null) {
                return candServ.findCandidateByTelephoneNumber(telephoneNumber);
            }
        }
        return null;
    }

    /**
     * Retrieves all candidates.
     *
     * @return Iterable of all candidates.
     */
    public Iterable<Candidate> allCandidates() {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.CUSTOMER_MANAGER, Jobs4URoles.ADMIN);
        return candServ.allCandidates();
    }

    /**
     * Finds an application by the job reference.
     *
     * @param jobReference The reference to the job opening.
     * @return The application if found, otherwise null.
     */
    public Application findApplication(JobReference jobReference) {
        return null;
    }


    /**
     * Displays candidate data.
     *
     * @param candidateSave The candidate whose data needs to be displayed.
     * @return true if the data is displayed successfully, otherwise false.
     */
    public boolean displayCandidateData(Candidate candidateSave ){

        SystemUser userCandidate = candidateSave.user();
        System.out.println("Candidate Information:");
        System.out.println("Name: " + userCandidate.name());
        System.out.println("Email: " + userCandidate.email());
        System.out.println("Phone Number: " + candidateSave.identity());
        System.out.println("Curriculum: " + candidateSave.curriculum());
    return true;
    }


}
