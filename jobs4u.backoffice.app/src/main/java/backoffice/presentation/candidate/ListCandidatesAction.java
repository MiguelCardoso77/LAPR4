package backoffice.presentation.candidate;

import eapli.framework.actions.Action;

/**
 * Action class responsible for listing all candidates.
 * It triggers the UI to display a list of all registered candidates.
 * The UI provides options to filter and search for candidates based on various criteria.
 * This action allows users to view and manage candidate information effectively.
 * If there are no candidates registered, appropriate messages are displayed.
 * The execution of this action returns true upon completion.
 *
 * @author Miguel Cardoso
 */
public class ListCandidatesAction implements Action {

    /**
     * Executes the action to list all candidates.
     *
     * @return true upon successful execution.
     */
    @Override
    public boolean execute() {
        return new ListCandidatesUI().show();
    }
}
