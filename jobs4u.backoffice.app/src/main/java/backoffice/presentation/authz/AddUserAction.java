package backoffice.presentation.authz;

import eapli.framework.actions.Action;

/**
 * Menu action for adding a new user to the application.
 *
 * @author Diana Neves
 */
public class AddUserAction implements Action {

    /**
     * Calls the AddUserUI to show the form to add a new user.
     *
     * @return true if the form is shown
     */
    @Override
    public boolean execute() {
        return new AddUserUI().show();
    }
}
