package backoffice.presentation.candidate;

import eapli.framework.actions.Action;

public class ListCandidatesAction implements Action {
    @Override
    public boolean execute() {
        return new ListCandidatesUI().show();
    }
}
