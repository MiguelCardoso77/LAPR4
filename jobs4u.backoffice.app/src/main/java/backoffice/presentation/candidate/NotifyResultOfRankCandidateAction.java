package backoffice.presentation.candidate;

import eapli.framework.actions.Action;

/**
 * Action class responsible for executing the UI to notify candidates of their rank.
 * It instantiates and shows the {@link NotifyResultOfRankCandidatesUI} to perform the notification process.
 *
 * @author Tomás Gonçalves
 */
public class NotifyResultOfRankCandidateAction implements Action {

    /**
     * Executes the action to notify candidates of their rank by displaying the UI.
     *
     * @return true indicating the successful execution of the notification process.
     */
    @Override
    public boolean execute() {
        return new NotifyResultOfRankCandidatesUI().show();
    }
}
