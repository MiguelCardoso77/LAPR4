package user.console.presentation.applications;

import eapli.framework.actions.Action;

public class ListCandidateApplicationsAction implements Action {
    private String email;

    public ListCandidateApplicationsAction(String email) {
        this.email = email;
    }

    @Override
    public boolean execute() {
        return new ListCandidateApplicationsUI(email).show();
    }
}
