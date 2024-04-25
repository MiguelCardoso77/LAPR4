package backoffice.presentation.customer;

import backoffice.presentation.company.AddCompanyUI;
import backoffice.presentation.company.ListCompaniesUI;
import core.application.controllers.AddCustomerController;
import core.application.controllers.ListCompaniesController;
import core.domain.company.Company;
import core.domain.company.CompanyNumber;
import core.domain.customer.Customer;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class RegisterCustomerUI extends AbstractUI {

    private final AddCustomerController theController = new AddCustomerController();

    private final ListCompaniesController companiesController = new ListCompaniesController();

    @Override
    protected boolean doShow() {
        SystemUser currentUser = AuthzRegistry.authorizationService().session().authenticatedUser();

        if (currentUser != null) {
            final String firstName = Console.readLine("First Name:");
            final String lastName = Console.readLine("Last Name:");
            final String email = Console.readLine("Email:");

            final Calendar createdOn = Calendar.getInstance();
            final Set<Role> roles = new HashSet<>();

            final Company selectedCompany = selectOrCreateCompany();

            try {
                Customer registeredCustomer = theController.registerCustomer(firstName, lastName, email, roles, createdOn, selectedCompany , currentUser);

                if (registeredCustomer != null) {
                    System.out.println("Candidate registered successfully:");
                    System.out.println(registeredCustomer);
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
                return selectExistingCompany();
            case 2:
                return createNewCompany();
            default:
                System.out.println("Invalid choice. Selecting Existing Company by default.");
                return selectExistingCompany();
        }
    }

    private Company selectExistingCompany() {
        System.out.println("List of Companies:");
        new ListCompaniesUI().show();
        int companyNumber = Console.readInteger("Enter the number of the company you want to select: ");
        return companiesController.findCompany(CompanyNumber.valueOf(companyNumber));
    }

    private Company createNewCompany() {
        new AddCompanyUI().show();
        return selectExistingCompany();
    }

    @Override
    public String headline() {
        return "Register Customer";
    }
}