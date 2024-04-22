package backoffice.presentation.jobs;

import eapli.framework.actions.Action;

public class AddJobOpeningAction implements Action {
    @Override
    public boolean execute() {
        return new AddJobOpeningUI().show();
    }
}
