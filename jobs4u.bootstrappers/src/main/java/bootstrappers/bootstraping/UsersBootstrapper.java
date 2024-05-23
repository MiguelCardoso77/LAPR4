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

public class  UsersBootstrapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersBootstrapper.class);
    final AddUserController userController = new AddUserController();
    final RegisterCandidateController registerCandidateController = new RegisterCandidateController();
    final ListUsersController listUserController = new ListUsersController();

    final AddCustomerController customerController = new AddCustomerController();

    public UsersBootstrapper() {
        super();
    }

    /**
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param email
     * @param roles
     */
    protected void registerUser(final String username, final String password, final String firstName, final String lastName, final String email, final Set<Role> roles) {
        try {
            userController.addUser(username, password, firstName, lastName, email, roles);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            listUserController.find(Username.valueOf(username)).orElseThrow(() -> e);
        }
    }

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
    protected void registerCustomer(final String firstName, final String lastName, final String email, SystemUser customerManager, Company company){
        try{
            final Set<Role> roles = new HashSet<>();
            roles.add(Jobs4URoles.CUSTOMER);

            final Calendar createdOn = Calendar.getInstance();

            customerController.registerCustomer(firstName, lastName, email, roles, createdOn, company, customerManager);
        }catch (final IntegrityViolationException | ConcurrencyException e) {
            LOGGER.error("Error performing the operation", e);
            System.out.println("Unfortunatelly there was an unexpected error in the application. Please try again and if the problem persists, contact your system admnistrator.");
        }

    }
}
