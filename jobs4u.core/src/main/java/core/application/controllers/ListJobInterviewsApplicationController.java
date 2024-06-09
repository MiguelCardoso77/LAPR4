package core.application.controllers;

import core.domain.application.Application;
import core.domain.interview.JobInterview;
import core.services.JobInterviewService;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller responsible for managing job interviews related to a specific application.
 *
 * @author tomasgoncalves
 */
public class ListJobInterviewsApplicationController {
    private final JobInterviewService jobInterviewService = new JobInterviewService();

    /**
     * Retrieves all job interviews associated with a given application.
     *
     * @param application The application to retrieve job interviews for.
     * @return An iterable of job interviews associated with the specified application.
     */
    public Iterable<JobInterview> allJobInterviewsOfApplication(Application application){
        Iterable<JobInterview> allJobInterviews = jobInterviewService.allJobInterviews();
        List<JobInterview> allJobInterviewsApplication = new ArrayList<>();

        for (JobInterview jobInterview : allJobInterviews){
            if (jobInterview.application().equals(application)){
                allJobInterviewsApplication.add(jobInterview);
            }
        }
        return allJobInterviewsApplication;
    }

    /**
     * Finds a job interview by its identity.
     *
     * @param identity The identity of the job interview to find.
     * @return The job interview if found, otherwise null.
     */
    public JobInterview findJobInterviewById(Integer identity) {
        Iterable<JobInterview> allJobInterviews = jobInterviewService.allJobInterviews();

        for (JobInterview jobInterview : allJobInterviews){
            if (jobInterview.identity().equals(identity)){
               return jobInterview;
            }
        }
        return null;
    }
}
