package core.application.controllers;

import core.domain.application.Application;
import core.domain.application.Status;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import core.services.ApplicationService;
import core.services.JobOpeningService;
import core.services.NotificationService;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller responsible for listing applications of a job opening.
 * Provides methods to retrieve applications based on different criteria
 * and allows selecting specific applications or job openings.
 *
 * @author tomasgoncalves
 */
public class ListJobOpeningApplicationsController {
    private final JobOpeningService jobOpeningService = new JobOpeningService();
    private final ApplicationService appServ = new ApplicationService();
    private final NotificationService notificationService = new NotificationService();

    /**
     * Retrieves all applications associated with a specific job opening.
     *
     * @param jobReference The reference to the job opening.
     * @return Iterable of applications associated with the specified job opening.
     */
    public Iterable<Application> allApplicationsOfJobOpening(JobReference jobReference) {
        Iterable<Application> allApplications = appServ.allApplications();
        int count = 0;

        List<Application> allApplicationsJobOpening = new ArrayList<>();
        for (Application a : allApplications) {
            if (a.jobReference().sameReference(jobReference)) {
                allApplicationsJobOpening.add(a);
                count++;
            }
        }
        if(count == 0){
            return null;
        }
        return allApplicationsJobOpening;
    }

    /**
     * Retrieves all applications associated with a specific job opening that have status RECEIVED.
     *
     * @param jobReference The reference to the job opening.
     * @return Iterable of applications associated with the specified job opening.
     */
    public Iterable<Application> allApplicationsOfJobOpeningReceived(JobReference jobReference) {
        Iterable<Application> allApplications = appServ.allApplications();
        int count = 0;

        List<Application> allApplicationsJobOpening = new ArrayList<>();
        for (Application a : allApplications) {
            if (a.jobReference().sameReference(jobReference) && a.status().toString().equals("RECEIVED")) {
                allApplicationsJobOpening.add(a);
                count++;
            }
        }
        if(count == 0){
            System.out.println("There is no applications in status: RECEIVED , for the selected job opening.");
        }
        return allApplicationsJobOpening;
    }

    /**
     * Retrieves all applications associated with a specific job opening that have status ACCEPTED.
     *
     * @param jobOpening The job opening.
     * @return Iterable of applications associated with the specified job opening.
     */
    public Iterable<Application> allApplicationsOfJobOpeningAccepted(JobOpening jobOpening) {
        Iterable<Application> allApplications = appServ.allApplications();
        int count = 0;

        int vacancies = Integer.parseInt(jobOpening.vacanciesNumber().toString());

        List<Application> allApplicationsOfJobOpeningAccepted = new ArrayList<>();
        for (Application a : allApplications) {
            String rankCandidate = a.rank().toString();
            if(!rankCandidate.equals("Not Ranked")) {
                int rankCandidate1 = Integer.parseInt(a.rank().toString());
                if (a.jobReference().sameReference(jobOpening.jobReference()) && a.status().toString().equals("ACCEPTED") && rankCandidate1 <= vacancies) {
                    allApplicationsOfJobOpeningAccepted.add(a);
                    appServ.updateStatus(Status.CHOSEN , a);
                    notificationService.createNotification(a, "You have been chosen for the job opening ", a.candidate());
                    count++;
                }
            }
        }
        if(count == 0){
            return null;
        }
        return allApplicationsOfJobOpeningAccepted;
    }

    /**
     * Displays all applications associated with a specific job opening.
     *
     * @param jobReference The reference to the job opening.
     * @return Iterable of applications associated with the specified job opening.
     */
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
     * Allows the user to select an application by entering its ID.
     *
     * @return The selected Application object, or null if no application was selected.
     */
    public Application selectApplication() {
        Application application = null;
        final int option = Console.readInteger("Enter the id of the application");
        if (option == 0) {
            System.out.println("No application selected");
        } else {
            try {
                application = findApplicationByID(option);
            } catch (IntegrityViolationException | ConcurrencyException ex) {
                System.out.println("Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
            }
        }
        return application;
    }

    /**
     * Finds a job opening by its reference.
     *
     * @param jobReference The reference to the job opening.
     * @return The job opening if found, otherwise null.
     */
    public JobOpening findJobOpening(JobReference jobReference) {
        Iterable<JobOpening> allJobOpenings = jobOpeningService.allJobOpenings();
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