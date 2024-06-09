package core.application.controllers;

import core.domain.application.Application;
import core.domain.interview.JobInterview;
import core.domain.jobOpening.JobOpening;
import core.services.ApplicationService;
import core.services.JobInterviewService;
import eapli.framework.application.UseCaseController;

import java.util.ArrayList;
import java.util.List;
/**
 * Controller for ranking candidates in the Jobs4U system.
 * This class provides methods to find applications for a job opening, find interviews for an application,
 * update the rank of an application, clear assigned ranks, filter by non-ranked applications,
 * and check if a rank is already assigned.
 * It uses the ApplicationService and JobInterviewService from the core services.
 *
 * @author Miguel Cardoso
 */
@UseCaseController
public class RankCandidatesController {
    private final ApplicationService applicationService = new ApplicationService();
    private final JobInterviewService jobInterviewService = new JobInterviewService();
    private final List<Integer> assignedRanks = new ArrayList<>();
    /**
     * Finds the applications for a job opening.
     *
     * @param jobOpening the job opening to find applications for
     * @return a list of the applications for the job opening
     */
    public List<Application> findApplicationsForJobOpening(JobOpening jobOpening) {
        return applicationService.findApplicationsForJobOpening(jobOpening);
    }
    /**
     * Finds the interviews for an application.
     *
     * @param application the application to find interviews for
     * @return a list of the interviews for the application
     */
    public List<JobInterview> findInterviewsForApplication(Application application) {
        return jobInterviewService.findInterviewsForApplication(application);
    }
    /**
     * Updates the rank of an application.
     *
     * @param rank the rank to update the application with
     * @param application the application to update the rank of
     * @return the application with the updated rank
     */
    public Application updateRank(int rank, Application application) {
        assignedRanks.add(rank);
        return applicationService.updateRank(rank, application);
    }
    /**
     * Clears the assigned ranks.
     */
    public void clearAssignedRanks() {
        assignedRanks.clear();
    }
    /**
     * Filters by non-ranked applications.
     *
     * @param applications the applications to filter
     * @return a list of the non-ranked applications
     */
    public List<Application> filterByNonRankedApplications(List<Application> applications) {
        List<Application> filteredApplications = new ArrayList<>();

        for (Application a : applications) {
            if (!a.rank().isRanked()) {
                filteredApplications.add(a);
            }
        }

        return filteredApplications;
    }
    /**
     * Checks if a rank is already assigned.
     *
     * @param rank the rank to check
     * @return true if the rank is already assigned, false otherwise
     */
    public boolean isRankAlreadyAssigned(int rank) {
        return assignedRanks.contains(rank);
    }
}
