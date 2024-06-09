package backoffice.presentation.jobOpening;

import core.application.controllers.ListCustomerController;
import core.application.controllers.AddJobOpeningController;
import core.domain.customer.Customer;
import core.domain.jobOpening.ContractType;
import core.domain.jobOpening.JobReference;
import core.domain.jobOpening.Mode;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * User interface for adding a new job opening.
 * Allows users to input details such as description, vacancies number, address, mode, contract type, title or function, and customer.
 * Displays a list of customers to choose from when selecting a customer.
 * Utilizes controllers to interact with the domain layer.
 *
 * @author Miguel Cardoso
 */
public class AddJobOpeningUI extends AbstractUI {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddJobOpeningUI.class);
    private final AddJobOpeningController theController = new AddJobOpeningController();
    private final ListCustomerController customerController = new ListCustomerController();

    /**
     * Displays the UI for adding a new job opening and handles user input.
     *
     * @return false indicating that the UI should exit after execution
     */
    @Override
    protected boolean doShow() {
        final String description = Console.readLine("Description");
        final int vacanciesNumber = Console.readInteger("Vacancies Number");
        final String address = Console.readLine("Address");

        System.out.println("Mode");
        final Mode modes = showModes();

        System.out.println("Contract Type");
        final ContractType contractTypes = showContractTypes();

        final String titleOrFunction = Console.readLine("Title or Function");

        showCustomer();
        Customer customer = selectCustomer();

        final JobReference jobReference = new JobReference(customer.company().companyName().toString(), true);

        try {
            this.theController.addJobOpening(jobReference, description, vacanciesNumber, address, modes, contractTypes, titleOrFunction, customer);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("That job reference is already in use.");
        }

        return false;
    }

    /**
     * Displays a list of modes.
     *
     * @return the selected mode
     */
    private Mode showModes() {
        System.out.println("Select Mode:");
        System.out.println("1. HYBRID");
        System.out.println("2. ON_SITE");
        System.out.println("3. REMOTE");

        int selectedOption = Console.readInteger("Enter your choice:");

        switch (selectedOption) {
            case 1:
                return Mode.HYBRID;
            case 2:
                return Mode.ON_SITE;
            case 3:
                return Mode.REMOTE;
            default:
                System.out.println("Invalid option. Please select again.");
                return showModes();
        }
    }

    /**
     * Displays a list of contract types.
     *
     * @return the selected contract type
     */
    private ContractType showContractTypes() {
        System.out.println("Select Contract Type:");
        System.out.println("1. FULL_TIME");
        System.out.println("2. PART_TIME");

        int selectedOption = Console.readInteger("Enter your choice:");

        switch (selectedOption) {
            case 1:
                return ContractType.FULL_TIME;
            case 2:
                return ContractType.PART_TIME;
            default:
                System.out.println("Invalid option. Please select again.");
                return showContractTypes();
        }
    }

    /**
     * Displays a list of customers.
     */
    private void showCustomer(){
        final Iterable<Customer> iterable = customerController.allCustomers();

        if (!iterable.iterator().hasNext()) {
            System.out.println("There are no customers");
        } else {
            int cont = 1;
            System.out.println("List of registered Customers: \n");
            for (Customer customer : iterable) {
                System.out.printf("%-6s%-30s%n", cont, customer.identity());
                cont++;
            }
        }
    }

    /**
     * Displays a list of customers and allows the user to select one.
     *
     * @return the selected customer
     */
    private Customer selectCustomer(){
        final List<Customer> list = new ArrayList<>();
        for (Customer customer : customerController.allCustomers()) {
            list.add(customer);
        }

        Customer customer = null;
        final int option = Console.readInteger("Enter the number of the customer");
        if (option == 0) {
            System.out.println("No customer selected");
        } else {
            try {
                customer = this.customerController.findCustomer(list.get(option - 1));
            } catch (IntegrityViolationException | ConcurrencyException ex) {
                LOGGER.error("Error performing the operation", ex);
                System.out.println(
                        "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
            }
        }

        return customer;
    }

    /**
     * Returns the headline for the UI.
     *
     * @return the headline for the UI
     */
    @Override
    public String headline() {
        return "Add Job Opening";
    }
}
