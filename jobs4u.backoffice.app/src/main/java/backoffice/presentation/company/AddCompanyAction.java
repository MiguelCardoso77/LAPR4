package backoffice.presentation.company;

import backoffice.presentation.jobs.AddJobOpeningUI;
import eapli.framework.actions.Action;

public class AddCompanyAction implements Action {

    @Override
    public boolean execute() {
        return new AddCompanyUI().show();
    }
}
