package backoffice.presentation.customer;

import core.application.controllers.RegisterCustomerUserController;
import core.domain.customer.Company;
import core.domain.customer.Customer;
import core.domain.customer.TelephoneNumber;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;


import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class RegisterCustomerUI extends AbstractUI {

    private final RegisterCustomerUserController controller = new RegisterCustomerUserController();
    @Override
    protected boolean doShow() {
        final String username = Console.readLine("Username");
        final String password = Console.readLine("Password");
        final String firstName = Console.readLine("First Name");
        final String lastName = Console.readLine("Last Name");
        final String email = Console.readLine("Email");
        final String telephoneNumber = Console.readLine("Telephone Number");
        final String company = Console.readLine("Company");

        final Calendar createdOn = Calendar.getInstance();
        final Set<Role> roles = new HashSet<>(); // Add roles if needed

        try {
            TelephoneNumber telephoneNumber1 = new TelephoneNumber(telephoneNumber);
            Company company1 = new Company(company);
            Customer registeredCustomer = controller.registerCustomer(username, password, firstName, lastName, email, roles, createdOn, telephoneNumber1, company1);
            if (registeredCustomer != null) {
                System.out.println("Customer registered successfully:");
                System.out.println(registeredCustomer); // Print customer details
            } else {
                System.out.println("Failed to register customer.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return false;
    }

    @Override
    public String headline() {
        return "Register Customer";
    }

}