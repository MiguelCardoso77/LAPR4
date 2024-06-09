package backoffice.presentation.authz;

import eapli.framework.actions.Action;

/**
 * Menu action for deactivating a user.
 *
 * @author Diana Neves
 */
public class DeactivateUserAction implements Action {

    /**
     * Calls the UI to deactivate a user.
     *
     * @return true if the UI was shown
     */
    @Override
    public boolean execute() {
        return new DeactivateUserUI().show();
    }
}
