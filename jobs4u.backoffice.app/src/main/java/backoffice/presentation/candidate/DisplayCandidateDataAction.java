package backoffice.presentation.candidate;

import eapli.framework.actions.Action;

public class DisplayCandidateDataAction implements Action {

    @Override
    public boolean execute() {
        return new DisplayCandidateDataUI().doShow();
    }


}
