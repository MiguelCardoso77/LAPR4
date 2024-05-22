package backoffice.presentation.application;

import eapli.framework.actions.Action;

public class UploadRequirementsAnswersAction implements Action {
    @Override
    public boolean execute() {
        return new UploadRequirementsAnswersUI().doShow();
    }
}
