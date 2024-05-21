package backoffice.presentation.candidate;

import eapli.framework.actions.Action;

public class NotifyCandidatesAction implements Action {
    @Override
    public boolean execute() {
        return new NotifyCandidatesUI().show();
    }
}
