package user.console.presentation.jobOpenings;

import eapli.framework.actions.Action;


/**
 * Action to list all job openings for a specific customer.
 * This class is responsible for initiating the user interface to display the job openings.
 *
 * @author 1220812@isep.ipp.pt
 */
public class ListCustomerJobOpeningsAction implements Action {

    private final String email;
    /**
     * Constructs a new ListCustomerJobOpeningsAction.
     *
     * @param email The email of the customer.
     */
    public ListCustomerJobOpeningsAction(String email) {
        this.email = email;
    }

    /**
     * Executes the action to list customer job openings.
     *
     * @return true if the action was executed successfully, false otherwise.
     */
    @Override
    public boolean execute() {
        return new ListCustomerJobOpeningsUI(email).show();
    }
}
