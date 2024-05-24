package user.console.presentation.applications;

import eapli.framework.actions.Action;

public class ListCandidateApplicationsAction implements Action {
    @Override
    public boolean execute() {
        return new ListCandidateApplicationsUI().show();
    }
}
