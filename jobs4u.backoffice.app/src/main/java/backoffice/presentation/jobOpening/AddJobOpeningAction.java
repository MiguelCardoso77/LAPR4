package backoffice.presentation.jobOpening;

import eapli.framework.actions.Action;

/**
 * Action class for adding a new job opening.
 * Executes the user interface for adding a job opening when triggered.
 * Implements the Action interface.
 *
 * @author Miguel Cardoso
 */
public class AddJobOpeningAction implements Action {

    /**
     * Executes the action by showing the user interface for adding a job opening.
     *
     * @return true if the action is successfully executed, false otherwise
     */
    @Override
    public boolean execute() {
        return new AddJobOpeningUI().show();
    }
}
