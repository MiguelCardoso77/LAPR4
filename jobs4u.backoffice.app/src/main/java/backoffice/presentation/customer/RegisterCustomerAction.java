package backoffice.presentation.customer;


import backoffice.presentation.jobs.AddJobOpeningUI;
import eapli.framework.actions.Action;

public class RegisterCustomerAction implements Action {
    @Override
    public boolean execute() {
        return new RegisterCustomerUI().show();
    }
}
