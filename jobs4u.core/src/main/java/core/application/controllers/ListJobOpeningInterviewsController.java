package core.application.controllers;

import core.domain.application.Application;
import core.domain.interview.JobInterview;
import core.domain.jobOpening.JobOpening;
import eapli.framework.application.UseCaseController;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for listing all the Job Interviews on a Job Opening
 *
 * @author 1220812@isep.ipp.pt
 */

@UseCaseController
public class ListJobOpeningInterviewsController {
    final ListJobOpeningApplicationsController jobOpeningApplicationsController =  new ListJobOpeningApplicationsController();
    final ListJobInterviewsApplicationController jobInterviewsApplicationController = new ListJobInterviewsApplicationController();

    /**
     * Retrieves all job interviews associated with a specific job opening.
     * This method first retrieves all applications for the given job opening, then retrieves all job interviews
     * for each application, and collects them into a list.
     *
     * @param jobOpening The job opening for which to retrieve job interviews.
     * @return A list of {@link JobInterview} objects associated with the given job opening.
     */

    public List<JobInterview> allInterviewOfJobOpening(JobOpening jobOpening){
        List<JobInterview> jobOpeningInterviews = new ArrayList<>();

        Iterable<Application> jobOpeningApplications = jobOpeningApplicationsController.allApplicationsOfJobOpening(jobOpening.identity());
        for (Application application : jobOpeningApplications) {
            Iterable<JobInterview> interviews = jobInterviewsApplicationController.allJobInterviewsOfApplication(application);
            for (JobInterview interview : interviews) {
                jobOpeningInterviews.add(interview);
            }
        }
        return jobOpeningInterviews;
    }
}