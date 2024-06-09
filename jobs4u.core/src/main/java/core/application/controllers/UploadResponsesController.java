package core.application.controllers;

import core.domain.interview.InterviewAnswers;
import core.domain.interview.JobInterview;
import core.services.JobInterviewService;
import eapli.framework.application.UseCaseController;
import plugin.interviewModule.InterviewPlugin;

import java.util.ArrayList;
import java.util.List;
/**
 * Controller for uploading responses in the Jobs4U system.
 * This class provides methods to find an interview by ID, upload responses, retrieve responses,
 * and find all interviews with a model assigned.
 * It uses the JobInterviewService from the core services and the InterviewPlugin.
 *
 * @author Miguel Cardoso
 */
@UseCaseController
public class UploadResponsesController {
    private final JobInterviewService jobInterviewService = new JobInterviewService();
    /**
     * Finds an interview by its ID.
     *
     * @param jobInterviewID the ID of the interview to find
     * @return the found interview
     */
    public JobInterview findInterviewByID(int jobInterviewID) {
        return jobInterviewService.findById(jobInterviewID);
    }
    /**
     * Uploads responses to an interview.
     *
     * @param responses the responses to upload
     * @param jobInterview the interview to upload the responses to
     */
    public void uploadResponses(List<String> responses, JobInterview jobInterview) {
        InterviewAnswers interviewAnswers = new InterviewAnswers(responses);
        jobInterviewService.uploadResponses(interviewAnswers, jobInterview);
    }
    /**
     * Retrieves responses from a file.
     *
     * @param path the path to the file to retrieve the responses from
     * @return the retrieved responses
     */
    public List<String> retrieveResponses(String path) {
        InterviewPlugin plugin = new InterviewPlugin();
        return plugin.retrieveAnswers(path);
    }
    /**
     * Finds all interviews with a model assigned.
     *
     * @return a list of all interviews with a model assigned
     */
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
