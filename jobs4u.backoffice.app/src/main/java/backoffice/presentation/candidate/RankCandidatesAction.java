package backoffice.presentation.candidate;

import eapli.framework.actions.Action;

public class RankCandidatesAction implements Action {
    @Override
    public boolean execute() {
        return new RankCandidatesUI().show();
    }
}
