package user.console.presentation.applications;

import eapli.framework.actions.Action;

/**
 * Action class responsible for listing candidate applications.
 *
 * @author Diana Neves
 */
public class ListCandidateApplicationsAction implements Action {
    private final String email;

    public ListCandidateApplicationsAction(String email) {
        this.email = email;
    }

    @Override
    public boolean execute() {
        return new ListCandidateApplicationsUI(email).show();
    }
}
