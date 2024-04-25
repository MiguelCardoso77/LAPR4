package backoffice.presentation.candidate;

import eapli.framework.actions.Action;

public class RegisterCandidateAction implements Action {
    @Override
    public boolean execute() {
        return new RegisterCandidateUI().show();
    }
}
