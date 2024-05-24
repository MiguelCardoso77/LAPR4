package backoffice.presentation.candidate;

import eapli.framework.actions.Action;

public class ListCandidateApplicationsAction implements Action {
    @Override
    public boolean execute() {
        return new ListCandidateApplicationsUI().show();
    }
}
