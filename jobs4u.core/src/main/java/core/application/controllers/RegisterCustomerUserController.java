package core.application.controllers;

import core.domain.customer.Company;
import core.domain.customer.Customer;
import core.domain.customer.CustomerBuilder;
import core.domain.customer.TelephoneNumber;
import core.domain.user.Jobs4URoles;
import core.persistence.PersistenceContext;
import core.repositories.CustomerRepository;
import core.services.RegisterCustomerService;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.*;
import eapli.framework.validations.Preconditions;

import java.util.Calendar;
import java.util.Set;
@UseCaseController

public class RegisterCustomerUserController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final UserManagementService userService = AuthzRegistry.userService();

    private final CustomerRepository customerRepository;
    /**
     * Constructor for RegisterCustomerUserController.
     *
     * @param customerRepository the repository for managing customer data
     */
    public RegisterCustomerUserController(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public RegisterCustomerUserController() {
        this.customerRepository = PersistenceContext.repositories().customerUsers();
    }

    /**
     * Registers a new customer.
     *
     * @param username       the username of the customer
     * @param password       the password of the customer
     * @param firstName      the first name of the customer
     * @param lastName       the last name of the customer
     * @param email          the email of the customer
     * @param roles          the roles assigned to the customer
     * @param createdOn      the date when the customer was created
     * @param telephoneNumber the telephone number of the customer
     * @param company        the company of the customer
     * @return the registered customer
     */
    public Customer registerCustomer(String username, final String password, final String firstName, final String lastName, final String email, final Set<Role> roles, final Calendar createdOn, final TelephoneNumber telephoneNumber, final Company company) {
        final SystemUser newUser = createSystemUserForClientUser(username, password, firstName, lastName, email, roles, createdOn);
        return createCustomer(newUser, telephoneNumber, company);
    }
    /**
     * Creates a new system user for the customer.
     *
     * @param username    the username of the customer
     * @param password    the password of the customer
     * @param firstName   the first name of the customer
     * @param lastName    the last name of the customer
     * @param email       the email of the customer
     * @param roles       the roles assigned to the customer
     * @param createdOn   the date when the customer was created
     * @return the created system user
     */
    private SystemUser createSystemUserForClientUser(final String username, final String password, final String firstName, final String lastName, final String email, final Set<Role> roles, final Calendar createdOn) {
        Preconditions.nonNull(username);
        Preconditions.nonNull(password);
        Preconditions.nonNull(firstName);
        Preconditions.nonNull(lastName);
        Preconditions.nonNull(email);
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.CUSTOMER);
        return userService.registerNewUser(username, password, firstName, lastName, email, roles, createdOn);
    }
    /**
     * Creates a new customer using the provided system user, telephone number, and company information.
     *
     * @param newUser          the system user associated with the customer
     * @param telephoneNumber  the telephone number of the customer
     * @param company          the company of the customer
     * @return the created customer
     */
    private Customer createCustomer(final SystemUser newUser, final TelephoneNumber telephoneNumber, final Company company) {
        Preconditions.nonNull(newUser);
        Preconditions.nonNull(telephoneNumber);
        Preconditions.nonNull(company);
        RegisterCustomerService registerCustomerService = new RegisterCustomerService(customerRepository);
        //TelephoneNumber phoneNumber = new TelephoneNumber(telephoneNumber);
        //Company customerCompany = new Company(company);
        return registerCustomerService.registerNewCustomer(newUser, telephoneNumber, company);
    }
}