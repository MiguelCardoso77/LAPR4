package bootstrappers.bootstraping;

import core.application.controllers.*;
import core.domain.company.Company;
import core.domain.user.Jobs4URoles;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Bootstraps users, candidates, and customers.
 * This bootstrapper registers users, candidates, and customers using the respective controllers.
 * Requires access to the AddUserController, RegisterCandidateController, ListUsersController, and AddCustomerController.
 * Does not extend Action since it is not meant to be used directly in the bootstrapping process but rather as a helper class.
 *
 * @author Miguel Cardoso
 */
public class UsersBootstrapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersBootstrapper.class);

    private final AddUserController userController = new AddUserController();
    private final RegisterCandidateController registerCandidateController = new RegisterCandidateController();
    private final ListUsersController listUserController = new ListUsersController();
    private final AddCustomerController customerController = new AddCustomerController();

    public UsersBootstrapper() {
        super();
    }

    /**
     * Registers a user with the given details.
     * @param username the username
     * @param password the password
     * @param firstName the first name
     * @param lastName the last name
     * @param email the email
     * @param roles the roles
     */
    protected void registerUser(final String username, final String password, final String firstName, final String lastName, final String email, final Set<Role> roles) {
        try {
            userController.addUser(username, password, firstName, lastName, email, roles);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            listUserController.find(Username.valueOf(username)).orElseThrow(() -> e);
        }
    }

    /**
     * Registers a candidate with the given details.
     * @param firstName the first name
     * @param lastName the last name
     * @param email the email
     * @param telephoneNumber the telephone number
     * @param curriculum the curriculum
     */
    protected void registerCandidate(final String firstName, final String lastName, final String email, final String telephoneNumber, final String curriculum) {
        try {
            final Set<Role> roles = new HashSet<>();
            roles.add(Jobs4URoles.CANDIDATE);

            final Calendar createdOn = Calendar.getInstance();

            registerCandidateController.registerCandidate(firstName, lastName, email, roles, createdOn, telephoneNumber, curriculum);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            LOGGER.error("Error performing the operation", e);
            System.out.println("Unfortunatelly there was an unexpected error in the application. Please try again and if the problem persists, contact your system admnistrator.");
        }
    }

    /**
     * Registers a customer with the given details.
     * @param firstName the first name
     * @param lastName the last name
     * @param email the email
     * @param customerManager the customer manager
     * @param company the company
     */
    protected void registerCustomer(final String firstName, final String lastName, final String email, SystemUser customerManager, Company company) {
        try {
            final Set<Role> roles = new HashSet<>();
            roles.add(Jobs4URoles.CUSTOMER);

            final Calendar createdOn = Calendar.getInstance();

            customerController.registerCustomer(firstName, lastName, email, roles, createdOn, company, customerManager);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            LOGGER.error("Error performing the operation", e);
            System.out.println("Unfortunatelly there was an unexpected error in the application. Please try again and if the problem persists, contact your system admnistrator.");
        }
    }
}
