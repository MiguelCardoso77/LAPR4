package backoffice.presentation.interview;

import core.application.controllers.*;
import core.domain.interview.JobInterview;
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

        JobOpening jobOpening = selectJobOpening();

        List<JobInterview> interviews = jobOpeningInterviews(jobOpening);
        executeEvaluationProcess(interviews);

        // TO DO: Mensagem de sucesso ou outro tipo de print

        return false;
    }
    /**
     * Allows the user to select a job opening in "Analysis" phase from the displayed list.
     *
     * @return the selected JobOpening object
     */
    private JobOpening selectJobOpening() {
        return selectJobOpeningController.selectJobOpeningAnalysis();
    }

    /**
     * This method executes the evaluation process for the provided list of JobInterview objects.
     *
     * @param interviews list of JobInterview objects for the provided JobOpening object
     */
    private void executeEvaluationProcess(List<JobInterview> interviews){
        evaluationProcessController.evaluationProcessExecution(interviews);
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