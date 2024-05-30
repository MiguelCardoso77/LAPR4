package backoffice.presentation.interview;

import core.application.controllers.*;
import core.domain.interview.JobInterview;
import core.domain.interviewModel.InterviewModel;
import core.domain.jobOpening.JobOpening;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

/**
 * User Interface for evaluating job interview answers.
 *
 * @author 1220812@isep.ipp.pt
 */

public class JobInterviewEvaluationUI extends AbstractUI {
    final SelectJobOpeningController selectJobOpeningController = new SelectJobOpeningController();
    final InterviewsEvaluationProcessController evaluationProcessController = new InterviewsEvaluationProcessController();
    final ListJobOpeningInterviewsController listJobOpeningInterviewsController = new ListJobOpeningInterviewsController();

    /**
     *
     * This method is responsible for displaying the available job openings,
     * allowing the user to select a job opening, listing the interviews for the selected job opening,
     * and executing the evaluation process for these interviews.
     *
     * @return false after the operations are completed.
     */
    @Override
    protected boolean doShow() {

        JobOpening jobOpening = selectJobOpeningController.selectJobOpeningAnalysis();

        List<JobInterview> interviews = listJobOpeningInterviewsController.allInterviewOfJobOpening(jobOpening);

        InterviewModel interviewModel = jobOpening.myInterviewModel();

        boolean isProcessSuccessful = executeEvaluationProcess(interviews, interviewModel);

        if(isProcessSuccessful){
            System.out.println("Evaluation process completed successfully.");
        } else {
            System.out.println("Evaluation process failed.");
        }

        return false;
    }

    /**
     * Executes the evaluation process for the provided list of JobInterview objects using the provided InterviewModel.
     * Returns a boolean indicating the success or failure of the process.
     *
     * @param interviews List of JobInterview objects for which the evaluation process is to be executed.
     * @param interviewModel The InterviewModel to be used in the evaluation process.
     * @return boolean indicating whether the evaluation process was successful (true) or not (false).
     */
    private boolean executeEvaluationProcess(List<JobInterview> interviews, InterviewModel interviewModel){
        try {
            evaluationProcessController.evaluationProcessExecution(interviews, interviewModel);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * This method returns a list of JobInterview objects for the provided JobOpening object.
     *
     * @param jobOpening selected job opening
     * @return list with all the job interviews on the selected job opening
     */

    private List<JobInterview> jobOpeningInterviews(JobOpening jobOpening){
        return listJobOpeningInterviewsController.allInterviewOfJobOpening(jobOpening);
    }

    /**
     * This method returns the headline for this UI, which is “Job interview answers evaluation process”.
     *
     * @return “Job interview answers evaluation process”
     */
    @Override
    public String headline() {
        return "Job interview answers evaluation process";
    }
}