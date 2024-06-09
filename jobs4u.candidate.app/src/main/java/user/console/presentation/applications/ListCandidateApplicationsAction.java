package user.console.presentation.applications;

import eapli.framework.actions.Action;

/**
 * Action class responsible for listing candidate applications.
 *
 * @author Diana Neves
 */
public class ListCandidateApplicationsAction implements Action {
    private final String email;

    /**
     * Constructs a new ListCandidateApplicationsAction.
     *
     * @param email The email of the customer.
     */
    public ListCandidateApplicationsAction(String email) {
        this.email = email;
    }

    /**
     * Executes the action to list candidate applications.
     *
     * @return true if the action was executed successfully, false otherwise.
     */
    @Override
    public boolean execute() {
        return new ListCandidateApplicationsUI(email).show();
    }
}
