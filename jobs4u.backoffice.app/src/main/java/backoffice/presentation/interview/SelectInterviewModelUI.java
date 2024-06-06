package backoffice.presentation.interview;

import console.presentation.utils.ConsoleColors;
import core.application.controllers.*;
import core.domain.interviewModel.InterviewModel;
import core.domain.jobOpening.JobOpening;
import eapli.framework.presentation.console.AbstractUI;

public class SelectInterviewModelUI extends AbstractUI {
    final SelectInterviewModelController selectInterviewModelController = new SelectInterviewModelController();
    final SelectJobOpeningController selectJobOpeningController = new SelectJobOpeningController();

    @Override
    protected boolean doShow() {

        JobOpening jobOpening = selectJobOpening();

        InterviewModel interviewModel = selectInterviewModel();

        updateInterviewModel(jobOpening, interviewModel);

        return false;
    }

    private void updateInterviewModel(JobOpening jobOpening, InterviewModel interviewModel) {
        selectInterviewModelController.updateInterviewModel(jobOpening.identity(), interviewModel);
        System.out.println(ConsoleColors.GREEN + "Interview Model updated successfully!" + ConsoleColors.RESET);
    }

    private JobOpening selectJobOpening() {
        return selectJobOpeningController.selectJobOpening();
    }

    private InterviewModel selectInterviewModel() {
        return selectInterviewModelController.listAndSelectInterviewModels();
    }

    @Override
    public String headline() {
        return "Select a Interview Model for Interviews of a Job Opening";
    }
}
