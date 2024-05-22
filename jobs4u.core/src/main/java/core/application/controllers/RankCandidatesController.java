package core.application.controllers;

import core.domain.application.Application;
import core.domain.interview.JobInterview;
import core.domain.jobOpening.JobOpening;
import core.services.ApplicationService;
import core.services.JobInterviewService;
import eapli.framework.application.UseCaseController;

import java.util.ArrayList;
import java.util.List;

@UseCaseController
public class RankCandidatesController {
    private final ApplicationService applicationService = new ApplicationService();
    private final JobInterviewService jobInterviewService = new JobInterviewService();
    private final List<Integer> assignedRanks = new ArrayList<>();

    public List<Application> findApplicationsForJobOpening(JobOpening jobOpening) {
        return applicationService.findApplicationsForJobOpening(jobOpening);
    }

    public List<JobInterview> findInterviewsForApplication(Application application) {
        return jobInterviewService.findInterviewsForApplication(application);
    }

    public Application updateRank(int rank, Application application) {
        assignedRanks.add(rank);
        return applicationService.updateRank(rank, application);
    }

    public void clearAssignedRanks() {
        assignedRanks.clear();
    }

    public List<Application> filterByNonRankedApplications(List<Application> applications) {
        List<Application> filteredApplications = new ArrayList<>();

        for (Application a : applications) {
            if (!a.rank().isRanked()) {
                filteredApplications.add(a);
            }
        }

        return filteredApplications;
    }

    public boolean isRankAlreadyAssigned(int rank) {
        return assignedRanks.contains(rank);
    }
}
