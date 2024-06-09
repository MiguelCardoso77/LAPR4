package backoffice.presentation.authz;

import eapli.framework.actions.Action;


/**
 * Action class responsible for activating a user.
 * This class implements the Action interface from the EAPLI framework.
 * When executed, it invokes the UI for activating a user.
 *
 * @author Diana Neves
 */
public class ActivateUserAction implements Action {

    /**
     * Executes the action by showing the UI for activating a user.
     *
     * @return true if the action is executed successfully, false otherwise.
     */
    @Override
    public boolean execute() {
        return new ActivateUserUI().show();
    }
}