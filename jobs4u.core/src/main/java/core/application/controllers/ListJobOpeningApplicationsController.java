package core.application.controllers;

import core.domain.application.Application;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import core.domain.user.Jobs4URoles;
import core.services.ApplicationService;
import core.services.JobOpeningService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller responsible for listing applications of a job opening.
 *
 * @author Tomás Gonçalves
 */
public class ListJobOpeningApplicationsController {

    private final JobOpeningService jobserv = new JobOpeningService();
    private final ApplicationService appServ = new ApplicationService();


    /**
     * Retrieves all applications associated with a specific job opening.
     *
     * @param jobReference The reference to the job opening.
     * @return Iterable of applications associated with the specified job opening.
     */
    public Iterable<Application> allApplicationsOfJobOpening(JobReference jobReference) {
        Iterable<Application> allApplications = appServ.allApplications();

        List<Application> allApplicationsJobOpening = new ArrayList<>();
        for (Application a : allApplications) {
            if (a.jobReference().sameReference(jobReference)) {
                allApplicationsJobOpening.add(a);
            }
        }
        return allApplicationsJobOpening;
    }

    public Iterable<Application> showApplicationsOfJobOpening(JobReference jobReference) {
        Iterable<Application> iterable = allApplicationsOfJobOpening(jobReference);

        if (!iterable.iterator().hasNext()) {
            System.out.println("There are no applications for this job opening ");
        } else {
            System.out.printf("%-30s%-30s%-30s%-30s%-30s%n", "Application ID", "Rank", "Status",  "Job Reference" , "Candidate");
            for (Application application : iterable) {
                System.out.printf("%-30s%-30s%-30s%-30s%-30s%n", application.identity(), application.rank(), "Submitted", application.jobReference().jobReference(), application.candidate().user().identity());
            }
        }

        return iterable;
    }

    /**
     * Finds a job opening by its reference.
     *
     * @param jobReference The reference to the job opening.
     * @return The job opening if found, otherwise null.
     */
    public JobOpening findJobOpening(JobReference jobReference) {
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
    public Application findApplicationByID(int id) {
        Iterable<Application> allApplications = appServ.allApplications();
        for (Application application : allApplications) {
            if (application.identity() == id) {
                return application;
            }
        }
        return null;
    }


}