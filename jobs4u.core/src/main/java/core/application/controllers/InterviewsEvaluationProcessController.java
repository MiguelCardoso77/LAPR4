package core.application.controllers;

import core.domain.interview.JobInterview;
import core.domain.interview.Score;
import core.services.JobInterviewService;
import eapli.framework.application.UseCaseController;

/**
 * Controller for managing the interview evaluation process.
 *
 * @author 1220812@isep.ipp.pt
 */

@UseCaseController
public class InterviewsEvaluationProcessController {
    JobInterviewService jobInterviewService =  new JobInterviewService();

    /**
     * Updates the score of a given job interview.
     *
     * @param newScore the new Score to be assigned to the job interview
     * @param interview the JobInterview object for which the score is to be updated
     */
    public void interviewScoreUpdate(Score newScore, JobInterview interview){
        jobInterviewService.updateInterviewScore(newScore, interview.identity());
    }
    /**
     * Processes the evaluation of a given job interview and returns a Score.
     *
     * @param jobInterview the JobInterview object to be evaluated
     * @return the Score resulting from the evaluation of the job interview
     */
    public Score interviewEvaluationProcess(JobInterview jobInterview){
        return null;
    }
}