package backoffice.presentation.authz;

import eapli.framework.actions.Action;

/**
 * Action to list users.
 *
 * @author Miguel Cardoso
 */
public class ListUsersAction implements Action {

    /**
     * Executes the action to display the UI for listing users.
     *
     * @return true if the action was successfully executed
     */
    @Override
    public boolean execute() {
        return new ListUsersUI().show();
    }
}
