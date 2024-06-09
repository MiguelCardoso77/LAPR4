package core.application.controllers;

import core.domain.company.Company;
import core.domain.customer.Customer;
import core.domain.user.Jobs4UPasswordPolicy;
import core.domain.user.Jobs4URoles;
import core.services.CustomerService;
import eapli.framework.application.UseCaseController;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import java.util.Calendar;
import java.util.Set;
/**
 * A controller class for adding a new customer.
 *
 * @author 1220812@isep.ipp.pt
 */
@UseCaseController
public class AddCustomerController {

    private final Jobs4UPasswordPolicy passwordPolicy = new Jobs4UPasswordPolicy();

    private final UserManagementService user = AuthzRegistry.userService();

    private final CustomerService customerService = new CustomerService();
    /**
     * Registers a new customer with the specified information.
     *
     * @param firstName      the first name of the customer
     * @param lastName       the last name of the customer
     * @param email          the email address of the customer
     * @param roles          the roles of the customer
     * @param createdOn      the date when the customer was created
     * @param company        the company to which the customer belongs
     * @param customerManager the system user who manages the customer
     * @return the registered customer
     */

    public Customer registerCustomer(final String firstName, final String lastName, final String email, final Set<Role> roles, final Calendar createdOn, final Company company, final SystemUser customerManager) {
        final SystemUser newUser = createSystemUser(firstName, lastName, email, roles, createdOn);
        return createCustomer(newUser, EmailAddress.valueOf(email), company, customerManager);
    }
    /**
     * Creates a new system user for the customer.
     *
     * @param firstName the first name of the customer
     * @param lastName  the last name of the customer
     * @param email     the email address of the customer
     * @param roles     the roles of the customer
     * @param createdOn the date when the customer was created
     * @return the newly created system user
     */
    private SystemUser createSystemUser(final String firstName, final String lastName, final String email, final Set<Role> roles, final Calendar createdOn) {
        Preconditions.nonNull(firstName);
        Preconditions.nonNull(lastName);
        Preconditions.nonNull(email);

        roles.add(Jobs4URoles.CUSTOMER);
        String password = passwordPolicy.passwordGenerator(firstName);

        return user.registerNewUser(email, password, firstName, lastName, email, roles, createdOn);
    }
    /**
     * Creates a new candidate.
     *
     * @param newUser         the system user associated with the customer
     * @param emailAddress    the email address of the customer
     * @param company         the company to which the customer belongs
     * @param customerManager the system user who manages the customer
     * @return the newly created customer
     */

    private Customer createCustomer(final SystemUser newUser, final EmailAddress emailAddress, final Company company, final SystemUser customerManager) {
        Preconditions.nonNull(newUser);
        Preconditions.nonNull(emailAddress);
        Preconditions.nonNull(company);
        Preconditions.nonNull(customerManager);

        return customerService.registerCustomer(newUser, company, customerManager, emailAddress);
    }
}
