package backoffice.presentation.application;

import eapli.framework.actions.Action;

/**
 * Action class responsible for executing the upload requirements answers action.
 * It triggers the presentation of the UI for uploading candidate requirements answers.
 *
 * @author Diana Neves
 */
public class UploadRequirementsAnswersAction implements Action {

    /**
     * Executes the upload requirements answers action by displaying the UI for uploading candidate requirements answers.
     *
     * @return true if the requirement answers were successfully uploaded, false otherwise
     */
    @Override
    public boolean execute() {
        return new UploadRequirementsAnswersUI().doShow();
    }
}
