package backoffice.presentation.candidate;

import eapli.framework.presentation.console.AbstractUI;

public class ListCandidateApplicationsUI extends AbstractUI {
    @Override
    protected boolean doShow() {
        return true;
    }

    @Override
    public String headline() {
        return "List all candidate applications";
    }
}
