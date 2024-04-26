package backoffice.presentation.customer;

import core.application.controllers.AddCompanyController;
import core.application.controllers.AddCustomerController;
import core.application.controllers.AddUserController;
import core.application.controllers.ListCompaniesController;
import core.domain.company.Company;
import core.domain.customer.Customer;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class RegisterCustomerUI extends AbstractUI {
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
        for (Company company : companiesController.allCompanies()){
            company.toString();
        }
        int companyNumber = Console.readInteger("Enter the number of the company you want to select: ");
        return companiesController.findCompany(Integer.valueOf(companyNumber));
    }

    private Company createNewCompany() {
        String companyName = Console.readLine("Enter the company name: ");
        Company newCompany = addCompanyController.addCompany(companyName);
        return companiesController.findCompany(newCompany.companyNumber());
    }

    @Override
    public String headline() {
        return "Register Customer";
    }
}