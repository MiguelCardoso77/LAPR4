package user.console.presentation.jobOpenings;

import core.application.controllers.AddUserController;
import core.application.controllers.ListCustomerJobOpeningsController;
import core.domain.customer.Customer;
import core.domain.jobOpening.JobOpeningDTO;
import core.services.CustomerService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

/**
 * User Interface for listing all job openings of a specific customer.
 * This class handles the display logic for showing job openings in the console.
 *
 * @author 1220812@isep.ipp.pt
 */
public class ListCustomerJobOpeningsUI extends AbstractUI {

    private final AddUserController controller = new AddUserController();
    private final ListCustomerJobOpeningsController theController = new ListCustomerJobOpeningsController();
    private final CustomerService customerService = new CustomerService();

    /**
     * Displays the list of job openings for the logged in customer.
     *
     * @return true if the display was successful, false otherwise.
     */
    @Override
    protected boolean doShow() {
        SystemUser customer = getAuthenticatedCustomer();
        Customer customerObj = getCustomer(customer);

        displayCustomerJobOpenings(customerObj);

        return true;
    }

    /**
     * Retrieves the currently authenticated system user.
     *
     * @return The system user who is currently authenticated.
     */
    private SystemUser getAuthenticatedCustomer() {
        return controller.getLoggedInUser();
    }

    /**
     * Retrieves the customer associated with the given system user.
     *
     * @param customer The system user whose associated customer is to be retrieved.
     * @return The customer associated with the specified system user, or null if no such customer is found.
     */
    private Customer getCustomer(SystemUser customer) {
        return customerService.findCustomerByUser(customer);
    }

    /**
     * Displays the job openings associated with a given customer.
     *
     * @param customer logged in customer
     */
    private void displayCustomerJobOpenings(Customer customer) {
        // To change
        List<JobOpeningDTO> customerJobOpenings = theController.customerJobOpenings(customer);

        if (customerJobOpenings.isEmpty()) {
            System.out.println("There are no job openings associated with you");
        } else{
            int cont = 1;
            System.out.println("Job Openings:");
            for (JobOpeningDTO jobOpening : customerJobOpenings) {
                System.out.printf("%d. %s\n", cont++, jobOpening.toString());
            }
        }
    }

    /**
     * Provides the headline for the UI.
     *
     * @return the headline string.
     */
    @Override
    public String headline() {
        return "List all customer Job Openings";
    }

}
