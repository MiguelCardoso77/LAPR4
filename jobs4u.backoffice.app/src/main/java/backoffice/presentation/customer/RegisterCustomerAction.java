package backoffice.presentation.customer;


import eapli.framework.actions.Action;

/**
 * @author 1220812@isep.ipp.pt
 */
public class RegisterCustomerAction implements Action {
    @Override
    public boolean execute() {
        return new RegisterCustomerUI().show();
    }
}
