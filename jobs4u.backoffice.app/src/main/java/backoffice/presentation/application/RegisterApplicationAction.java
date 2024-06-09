package backoffice.presentation.application;

import eapli.framework.actions.Action;

/**
 * Action class responsible for registering an application.
 * This class implements the Action interface from the EAPLI framework.
 * When executed, it invokes the UI for registering an application.
 *
 * @author Diogo Ribeiro
 */
public class RegisterApplicationAction implements Action {

    /**
     * Executes the action by showing the UI for registering an application.
     *
     * @return true if the action is executed successfully, false otherwise.
     */
    @Override
    public boolean execute() {
        return new RegisterApplicationUI().doShow();
    }
}