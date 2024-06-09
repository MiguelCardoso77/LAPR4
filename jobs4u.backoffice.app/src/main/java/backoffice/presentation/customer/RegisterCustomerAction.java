package backoffice.presentation.customer;


import eapli.framework.actions.Action;

/**
 * Action class responsible for executing the action to register a customer.
 * It creates an instance of the RegisterCustomerUI class and calls its show() method to display the UI for registering a customer.
 *
 * @author Diogo Ribeiro
 */
public class RegisterCustomerAction implements Action {

    /**
     * Executes the action to register a customer.
     *
     * @return true if the registration process is successful, false otherwise.
     */
    @Override
    public boolean execute() {
        return new RegisterCustomerUI().show();
    }
}
