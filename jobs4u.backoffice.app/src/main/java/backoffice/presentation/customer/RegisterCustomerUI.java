package backoffice.presentation.customer;

import backoffice.presentation.jobs.AddJobOpeningUI;
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

public class RegisterCustomerUI extends AbstractUI {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddJobOpeningUI.class);
    private final AddUserController controller = new AddUserController();

    private final AddCustomerController theController = new AddCustomerController();

    private final ListCompaniesController companiesController = new ListCompaniesController();
    private final AddCompanyController addCompanyController = new AddCompanyController();

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
                Customer registeredCustomer = theController.registerCustomer(firstName, lastName, email, roles, createdOn, selectedCompany , currentUser);

                if (registeredCustomer != null) {
                    System.out.println("Customer registered successfully:");
                    System.out.println(registeredCustomer.toString());
                } else {
                    System.out.println("Failed to register customer.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("No authenticated user found.");
        }

        return true;
    }

    private Company selectOrCreateCompany() {
        System.out.println("Select or Create a Company:");
        System.out.println("1. Select Existing Company");
        System.out.println("2. Create New Company");
        int option = Console.readInteger("Enter your choice: ");

        switch (option) {
            case 1:
                return showAndSelectCompanies();
            case 2:
                return createNewCompany();
            default:
                System.out.println("Invalid choice. Selecting Existing Company by default.");
                return showAndSelectCompanies();
        }
    }

    private Company showAndSelectCompanies() {
        final List<Company> list = new ArrayList<>();
        final Iterable<Company> iterable = companiesController.allCompanies();

        Company company = null;
        if (!iterable.iterator().hasNext()) {
            System.out.println("There are no companies");
        } else {
            int cont = 1;
            System.out.println("List of Companies: \n");
            for (Company company1 : iterable) {
                list.add(company1);
                System.out.printf("%-6s%-30s%n", cont, company1.companyName());
                cont++;
            }

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
        }

        return company;

    }

    private Company createNewCompany() {
        String companyName = Console.readLine("Enter the company name: ");
        Company newCompany = addCompanyController.addCompany(companyName);
        return companiesController.findCompany(newCompany.identity());
    }

    @Override
    public String headline() {
        return "Register Customer";
    }
}