package backoffice.presentation.candidate;

import eapli.framework.actions.Action;

/**
 * An action class to execute the user interface for ranking candidates.
 * This class implements the Action interface from EAPLI framework.
 * When executed, it instantiates and displays the {@link RankCandidatesUI} to facilitate the ranking process.
 *
 * @see Action
 * @see RankCandidatesUI
 * @author Miguel Cardoso
 */
public class RankCandidatesAction implements Action {

    /**
     * Executes the action by displaying the UI for ranking candidates.
     *
     * @return true indicating the successful execution of the action.
     */
    @Override
    public boolean execute() {
        return new RankCandidatesUI().show();
    }
}
