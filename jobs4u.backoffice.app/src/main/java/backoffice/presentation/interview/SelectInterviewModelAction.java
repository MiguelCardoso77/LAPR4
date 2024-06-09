package backoffice.presentation.interview;

import eapli.framework.actions.Action;

/**
 * Action class responsible for executing the UI to select an interview model.
 * It invokes the UI for presenting a list of available interview models and allows the user to select one.
 *
 * @author Diana Neves
 */
public class SelectInterviewModelAction implements Action {

    /**
     * Executes the action to select an interview model.
     *
     * @return true if the action is successfully executed, false otherwise.
     */
    @Override
    public boolean execute() {
        return new SelectInterviewModelUI().show();
    }
}
