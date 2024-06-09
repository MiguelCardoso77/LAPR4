package backoffice.presentation.process;

import eapli.framework.actions.Action;

/**
 * Action class responsible for executing the change process status action.
 * It triggers the presentation of the UI for changing the status of a process.
 *
 * @author Diana Neves
 */
public class ChangeProcessStatusAction implements Action {

    /**
     * Executes the change process status action by displaying the UI for changing the status of a process.
     *
     * @return true if the process status was successfully changed, false otherwise
     */
    @Override
    public boolean execute() {
        return new ChangeProcessStatusUI().show();
    }
}
