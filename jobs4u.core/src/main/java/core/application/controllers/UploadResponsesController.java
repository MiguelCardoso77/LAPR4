package core.application.controllers;

import core.domain.interview.InterviewAnswers;
import core.domain.interview.JobInterview;
import core.persistence.PersistenceContext;
import core.repositories.JobInterviewRepository;
import eapli.framework.application.UseCaseController;
import plugin.interviewModule.InterviewPlugin;

import java.util.ArrayList;
import java.util.List;

@UseCaseController
public class UploadResponsesController {
    private final JobInterviewRepository jobInterviewRepository = PersistenceContext.repositories().jobInterviews();

    public JobInterview findInterviewByID(int jobInterviewID) {
        return jobInterviewRepository.ofIdentity(jobInterviewID).orElse(null);
    }

    public JobInterview uploadResponses(List<String> responses, JobInterview jobInterview) {
        InterviewAnswers interviewAnswers = new InterviewAnswers(responses);

        jobInterview.uploadInterviewAnswers(interviewAnswers);
        System.out.println("\nResponses uploaded successfully!");
        return jobInterviewRepository.save(jobInterview);
    }

    public List<String> retrieveResponses(String path) {
        InterviewPlugin plugin = new InterviewPlugin();
        return plugin.retrieveAnswers(path);
    }

    public List<JobInterview> findAllInterviewsWithModelAssigned() {
        List<JobInterview> jobInterviews = (List<JobInterview>) jobInterviewRepository.allJobInterviews();
        List<JobInterview> availableInterviews = new ArrayList<>();

        for (JobInterview jobInterview : jobInterviews) {
            if (jobInterview.application().jobReference().myInterviewModel() != null) {
                availableInterviews.add(jobInterview);
            }
        }

        return availableInterviews;
    }
}
