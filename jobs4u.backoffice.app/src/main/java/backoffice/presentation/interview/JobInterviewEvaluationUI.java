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

        evaluationProcessController.evaluationProcessExecution(interviews, interviewModel);

        return false;
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