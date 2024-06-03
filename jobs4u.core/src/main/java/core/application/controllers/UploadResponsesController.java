package core.application.controllers;

import core.domain.interview.InterviewAnswers;
import core.domain.interview.JobInterview;
import core.services.JobInterviewService;
import eapli.framework.application.UseCaseController;
import plugin.interviewModule.InterviewPlugin;

import java.util.ArrayList;
import java.util.List;

@UseCaseController
public class UploadResponsesController {
    private final JobInterviewService jobInterviewService = new JobInterviewService();

    public JobInterview findInterviewByID(int jobInterviewID) {
        return jobInterviewService.findById(jobInterviewID);
    }

    public void uploadResponses(List<String> responses, JobInterview jobInterview) {
        InterviewAnswers interviewAnswers = new InterviewAnswers(responses);
        jobInterviewService.uploadResponses(interviewAnswers, jobInterview);
    }

    public List<String> retrieveResponses(String path) {
        InterviewPlugin plugin = new InterviewPlugin();
        return plugin.retrieveAnswers(path);
    }

    public List<JobInterview> findAllInterviewsWithModelAssigned() {
        List<JobInterview> jobInterviews = (List<JobInterview>) jobInterviewService.allJobInterviews();
        List<JobInterview> availableInterviews = new ArrayList<>();

        for (JobInterview jobInterview : jobInterviews) {
            if (jobInterview.application().jobReference().myInterviewModel() != null) {
                availableInterviews.add(jobInterview);
            }
        }

        return availableInterviews;
    }
}
