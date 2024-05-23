package core.application.controllers;

import core.domain.interview.JobInterview;
import core.domain.interview.Score;
import core.services.InterviewScoreUpdateService;
import eapli.framework.application.UseCaseController;

import java.util.List;

/**
 * Controller for managing the interview evaluation process.
 *
 * @author 1220812@isep.ipp.pt
 */

@UseCaseController
public class InterviewsEvaluationProcessController {
    InterviewScoreUpdateService jobInterviewService;

    /**
     * Updates the score of a given job interview.
     *
     * @param newScore the new Score to be assigned to the job interview
     * @param interview the JobInterview object for which the score is to be updated
     */
    public void interviewScoreUpdate(Score newScore, JobInterview interview){
        jobInterviewService.interviewScoreUpdate(interview.identity(), newScore);
    }
    /**
     * Processes the evaluation of a given job interview and returns a Score.
     *
     * @param jobInterview the JobInterview object to be evaluated
     * @return the Score resulting from the evaluation of the job interview
     */
    public Score interviewEvaluation(JobInterview jobInterview){
        return null;
    }

    /**
     * Executes the evaluation process for all the job interviews associated to the selected job opening
     *
     * @param jobOpeningInterviews list of job interviews to be evaluated
     */

    public void evaluationProcessExecution(List<JobInterview> jobOpeningInterviews){
        for (JobInterview interview : jobOpeningInterviews) {
            Score score = interviewEvaluation(interview);
            interviewScoreUpdate(score, interview);
        }
    }
}