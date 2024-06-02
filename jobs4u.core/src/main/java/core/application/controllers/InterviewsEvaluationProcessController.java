package core.application.controllers;

import core.domain.interview.JobInterview;
import core.domain.interview.Score;
import core.domain.interviewModel.InterviewModel;
import core.services.JobInterviewService;
import eapli.framework.application.UseCaseController;
import plugin.interviewModule.InterviewPlugin;

import java.util.List;

/**
 * Controller for managing the interview evaluation process.
 *
 * @author 1220812@isep.ipp.pt
 */

@UseCaseController
public class InterviewsEvaluationProcessController {
    private JobInterviewService jobInterviewModelService = new JobInterviewService();
    private InterviewPlugin interviewPlugin = new InterviewPlugin();

    final String RED = "\u001B[31m";
    final String GREEN = "\u001B[32m";
    final String RESET = "\u001B[0m";

    /**
     * Updates the score of a given job interview.
     *
     * @param newScore  the new Score to be assigned to the job interview
     * @param interview the JobInterview object for which the score is to be updated
     */
    public void interviewScoreUpdate(Score newScore, JobInterview interview) {
        jobInterviewModelService.updateInterviewScore(newScore, interview.identity());
    }

    /**
     * Processes the evaluation of a given job interview and returns a Score.
     *
     * @param jobInterview the JobInterview object to be evaluated
     * @return the Score resulting from the evaluation of the job interview
     */
    public Score interviewEvaluation(JobInterview jobInterview, InterviewModel interviewModel) {
        List<String> interviewAnswers = jobInterview.interviewAnswers().answersList();
        if (jobInterview.interviewAnswers() == null) {
            System.out.println("Interview answers not found.");
        } else {
            int score;
            score = interviewPlugin.grammarChecker(interviewModel.model(), interviewAnswers);
            return new Score(score);
        }
        return null;
    }

    /**
     * Executes the evaluation process for all the job interviews associated to the selected job opening
     *
     * @param jobOpeningInterviews list of job interviews to be evaluated
     */

    public void evaluationProcessExecution(List<JobInterview> jobOpeningInterviews, InterviewModel interviewModel) {
        for (JobInterview interview : jobOpeningInterviews) {
            if (interview.interviewAnswers() == null) {
                System.out.println();
                System.out.println(RED + "Interview answers not found for the interview " + interview.identity() + "." + RESET);
                System.out.println("=======================================================");
            } else {
                Score score = interviewEvaluation(interview, interviewModel);
                interviewScoreUpdate(score, interview);
                System.out.println(GREEN + "Success: Interview " + interview.identity() + " evaluated successfully." + RESET);
                System.out.println("=======================================================");
            }
        }
    }
}