package backoffice.presentation.interview;

import eapli.framework.actions.Action;

public class UploadResponsesAction implements Action {
    @Override
    public boolean execute() {
        return new UploadResponsesUI().show();
    }
}
