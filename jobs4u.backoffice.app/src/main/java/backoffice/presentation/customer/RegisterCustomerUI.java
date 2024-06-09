package backoffice.presentation.customer;

import backoffice.presentation.jobOpening.AddJobOpeningUI;
import console.presentation.utils.ConsoleColors;
import core.application.controllers.AddCompanyController;
import core.application.controllers.AddCustomerController;
import core.application.controllers.AddUserController;
import core.application.controllers.ListCompaniesController;
import core.domain.company.Company;
import core.domain.customer.Customer;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * UI class responsible for registering a new customer.
 * It prompts the user to enter the customer's details such as first name, last name, and email.
 * It also allows the user to select an existing company or create a new one for the customer.
 * Upon successful registration, the customer is associated with the selected company and added to the system.
 * If the authentication fails or any error occurs during the registration process, appropriate error messages are displayed.
 *
 * @author Diogo Ribeiro
 */
public class RegisterCustomerUI extends AbstractUI {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddJobOpeningUI.class);

    private final AddUserController controller = new AddUserController();
    private final AddCustomerController theController = new AddCustomerController();
    private final ListCompaniesController companiesController = new ListCompaniesController();
    private final AddCompanyController addCompanyController = new AddCompanyController();

    /**
     * Displays the UI for registering a new customer.
     *
     * @return true if the registration process is successful, false otherwise.
     */
    @Override
    protected boolean doShow() {
        SystemUser currentUser = controller.getLoggedInUser();

        if (currentUser != null) {
            final String firstName = Console.readLine("First Name:");
            final String lastName = Console.readLine("Last Name:");
            final String email = Console.readLine("Email:");

            final Calendar createdOn = Calendar.getInstance();
            final Set<Role> roles = new HashSet<>();

            Company selectedCompany = selectOrCreateCompany();

            try {
                Customer registeredCustomer = theController.registerCustomer(firstName, lastName, email, roles, createdOn, selectedCompany, currentUser);

                if (registeredCustomer != null) {
                    System.out.println("Customer registered successfully!");
                } else {
                    System.out.println("Failed to register customer.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println(ConsoleColors.RED + "No authenticated user found." + ConsoleColors.RESET);
        }

        return true;
    }

    /**
     * Allows the user to select an existing company or create a new one.
     *
     * @return the selected or created company.
     */
    private Company selectOrCreateCompany() {
        showCompanies();
        System.out.println();
        System.out.println("Select or Create a Company:");
        System.out.println("1. Select Existing Company");
        System.out.println("2. Create New Company");
        int option = Console.readInteger("Enter your choice: ");

        Company company;
        switch (option) {
            case 1:
                company = selectCompany();
                break;
            case 2:
                company = createNewCompany();
                break;
            default:
                System.out.println("Invalid choice. Selecting Existing Company by default.");
                company = selectCompany();
        }
        return company;
    }

    /**
     * Displays the list of existing companies.
     */
    private void showCompanies() {
        final Iterable<Company> iterable = companiesController.allCompanies();

        if (!iterable.iterator().hasNext()) {
            System.out.println("There are no companies");
        } else {
            int cont = 1;
            System.out.println("List of registered Companies: \n");
            for (Company company : iterable) {
                System.out.printf("%-6s%-30s%n", cont, company.companyName());
                cont++;
            }
        }
    }

    /**
     * Allows the user to select an existing company.
     *
     * @return the selected company.
     */
    private Company selectCompany() {
        final List<Company> list = new ArrayList<>();
        for (Company company : companiesController.allCompanies()) {
            list.add(company);
        }

        Company company = null;
        final int option = Console.readInteger("Enter the number of the company");
        if (option == 0) {
            System.out.println("No company selected");
        } else {
            try {
                company = this.companiesController.findCompany(list.get(option - 1).identity());
            } catch (IntegrityViolationException | ConcurrencyException ex) {
                LOGGER.error("Error performing the operation", ex);
                System.out.println(
                        "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
            }
        }

        return company;
    }

    /**
     * Creates a new company based on user input.
     *
     * @return the newly created company.
     */
    private Company createNewCompany() {
        String companyName = Console.readLine("Enter the company name: ");
        Company newCompany = addCompanyController.addCompany(companyName);
        return companiesController.findCompany(newCompany.identity());
    }

    /**
     * Returns the headline for the UI.
     *
     * @return a string representing the headline for the UI.
     */
    @Override
    public String headline() {
        return "Register Customer";
    }
}