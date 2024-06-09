package backoffice.presentation.candidate;

import eapli.framework.actions.Action;

/**
 * Action class responsible for notifying candidates.
 * It executes the user interface {@link NotifyCandidatesUI} to handle the notification process.
 * Upon execution, it returns true to indicate the successful completion of the notification action.
 *
 * @author Miguel Cardoso
 */
public class NotifyCandidatesAction implements Action {

    /**
     * Executes the action to notify candidates.
     * It invokes the {@link NotifyCandidatesUI} to perform the notification process.
     *
     * @return true indicating the successful execution of the notification action.
     */
    @Override
    public boolean execute() {
        return new NotifyCandidatesUI().show();
    }
}
