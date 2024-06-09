package backoffice.presentation.candidate;

import eapli.framework.actions.Action;

/**
 * An action to register a new candidate.
 *
 * @author Miguel Cardoso
 */
public class RegisterCandidateAction implements Action {

    /**
     * Executes the action by showing the UI for registering a candidate.
     *
     * @return true if the action is successfully executed, false otherwise.
     */
    @Override
    public boolean execute() {
        return new RegisterCandidateUI().show();
    }
}
