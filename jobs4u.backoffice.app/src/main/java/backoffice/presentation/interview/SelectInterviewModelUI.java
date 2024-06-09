package backoffice.presentation.interview;

import console.presentation.utils.ConsoleColors;
import core.application.controllers.*;
import core.domain.interviewModel.InterviewModel;
import core.domain.jobOpening.JobOpening;
import eapli.framework.presentation.console.AbstractUI;

/**
 * UI class responsible for selecting an interview model for interviews of a job opening.
 * It allows the user to select a job opening and an interview model associated with it.
 * The selected interview model is then updated for the specified job opening.
 *
 * @author Diana Neves
 */
public class SelectInterviewModelUI extends AbstractUI {
    private final SelectInterviewModelController selectInterviewModelController = new SelectInterviewModelController();
    private final SelectJobOpeningController selectJobOpeningController = new SelectJobOpeningController();

    /**
     * Displays the UI for selecting an interview model.
     *
     * @return always returns false to indicate that the operation is completed.
     */
    @Override
    protected boolean doShow() {
        JobOpening jobOpening = selectJobOpening();

        InterviewModel interviewModel = selectInterviewModel();

        updateInterviewModel(jobOpening, interviewModel);

        return true;
    }

    /**
     * Updates the interview model for the specified job opening.
     *
     * @param jobOpening the job opening for which the interview model will be updated.
     * @param interviewModel the interview model to be updated.
     */
    private void updateInterviewModel(JobOpening jobOpening, InterviewModel interviewModel) {
        selectInterviewModelController.updateInterviewModel(jobOpening.identity(), interviewModel);
        System.out.println(ConsoleColors.GREEN + "Interview Model updated successfully!" + ConsoleColors.RESET);
    }

    /**
     * Allows the user to select a job opening.
     *
     * @return the selected job opening.
     */
    private JobOpening selectJobOpening() {
        return selectJobOpeningController.selectJobOpening();
    }

    /**
     * Allows the user to select an interview model.
     *
     * @return the selected interview model.
     */
    private InterviewModel selectInterviewModel() {
        return selectInterviewModelController.listAndSelectInterviewModels();
    }

    /**
     * Provides the headline for the UI.
     *
     * @return a string representing the headline for the UI.
     */
    @Override
    public String headline() {
        return "Select a Interview Model for a Job Opening";
    }
}
