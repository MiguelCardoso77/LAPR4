package core.application.controllers;

import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import core.domain.process.ProcessState;
import core.services.JobOpeningService;

/**
 * Controller for managing the listing of job openings in the Jobs4U system.
 * This class provides methods to retrieve all job openings, to show all job openings,
 * to show all job openings in the analysis phase, and to find a job opening by its reference.
 * It uses the JobOpeningService from the core services.
 *
 * @author Diogo Ribeiro
 */
public class ListJobOpeningController {
    private final JobOpeningService jobOpeningService = new JobOpeningService();

    /**
     * Displays all job openings.
     *
     * @return an iterable collection of all job openings
     */
    public Iterable<JobOpening> showJobOpenings() {
        Iterable<JobOpening> iterable = allJobOpenings();

        if (!iterable.iterator().hasNext()) {
            System.out.println("There are no Job Openings");
        } else {
            int count = 1;
            System.out.println("List of registered Job Openings: ");
            for (JobOpening jobOpening : iterable) {
                System.out.println(count + " - " + jobOpening.jobReference() + ", published by: " + jobOpening.customer());
                count++;
            }
        }
        return iterable;
    }

    /**
     * Displays all job openings in the analysis phase.
     *
     * @return an iterable collection of all job openings in the analysis phase
     */
    public Iterable<JobOpening> showJobOpeningsAnalysis() {
        Iterable<JobOpening> iterable = allJobOpenings();

        if (!iterable.iterator().hasNext()) {
            System.out.println("There are no Job Openings");
        } else {
            int count = 1;
            System.out.println("List of registered Job Openings in Analysis phase: ");
            for (JobOpening jobOpening : iterable) {
                if (jobOpening.process().processState().equals(ProcessState.ANALYSIS)) {
                    System.out.println(count + " - " + jobOpening.jobReference() + ", published by: " + jobOpening.customer());
                    count++;
                }
            }
        }
        return iterable;
    }

    /**
     * Retrieves all job openings.
     *
     * @return an iterable collection of all job openings
     */
    public Iterable<JobOpening> allJobOpenings() {
        return jobOpeningService.allJobOpenings();
    }

    /**
     * Finds a job opening by its reference.
     *
     * @param jobReference the reference of the job opening to find
     * @return the job opening with the specified reference
     */
    public JobOpening findJobOpeningByJobReference(JobReference jobReference) {
        return jobOpeningService.findJobOpening(jobReference);
    }
}

