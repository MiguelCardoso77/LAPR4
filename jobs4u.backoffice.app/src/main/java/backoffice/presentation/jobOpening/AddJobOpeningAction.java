package backoffice.presentation.jobOpening;

import eapli.framework.actions.Action;

public class AddJobOpeningAction implements Action {
    @Override
    public boolean execute() {
        return new AddJobOpeningUI().show();
    }
}
