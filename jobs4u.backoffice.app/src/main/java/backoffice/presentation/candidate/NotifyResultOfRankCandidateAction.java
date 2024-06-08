package backoffice.presentation.candidate;

import eapli.framework.actions.Action;

public class NotifyResultOfRankCandidateAction implements Action {
    @Override
    public boolean execute() {
        return new NotifyResultOfRankCandidatesUI().show();
    }
}
