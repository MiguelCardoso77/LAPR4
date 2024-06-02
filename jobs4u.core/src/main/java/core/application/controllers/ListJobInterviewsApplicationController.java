package core.application.controllers;

import core.domain.application.Application;
import core.domain.interview.JobInterview;
import core.services.JobInterviewService;
import java.util.ArrayList;
import java.util.List;

public class ListJobInterviewsApplicationController {
    private final JobInterviewService jobInterviewService = new JobInterviewService();

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
