package backoffice.presentation.authz;

import eapli.framework.actions.Action;

/**
 * Action to list backoffice users.
 *
 * @author Miguel Cardoso
 */
public class ListBackofficeUsersAction implements Action {

    /**
     * Executes the action to display the UI for listing backoffice users.
     *
     * @return true if the action was successfully executed
     */
    @Override
    public boolean execute() {
        return new ListBackofficeUsersUI().show();
    }
}
