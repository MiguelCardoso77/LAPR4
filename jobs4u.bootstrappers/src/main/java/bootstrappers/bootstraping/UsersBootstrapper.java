package bootstrappers.bootstraping;

import core.application.controllers.RegisterCandidateController;
import core.application.controllers.SignupController;
import core.application.controllers.AddUserController;
import core.application.controllers.ListUsersController;
import core.domain.user.Jobs4URoles;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.Username;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class UsersBootstrapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersBootstrapper.class);
    final SignupController signupController = new SignupController();
    final AddUserController userController = new AddUserController();
    final RegisterCandidateController registerCandidateController = new RegisterCandidateController();
    final ListUsersController listUserController = new ListUsersController();

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
            LOGGER.debug("»»» %s", username);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            listUserController.find(Username.valueOf(username)).orElseThrow(() -> e);
        }
    }

    protected void signupUser(final String password, final String firstName, final String lastName, final String email, String telephoneNumber) {
        try {
            signupController.signup(password, firstName, lastName, email, telephoneNumber, Calendar.getInstance());
            LOGGER.debug("»»» %s", email);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            LOGGER.error("Error performing the operation", e);
            System.out.println("Unfortunatelly there was an unexpected error in the application. Please try again and if the problem persists, contact your system admnistrator.");
        }
    }

    protected void registerCandidate(final String firstName, final String lastName, final String email, final String telephoneNumber, final String curriculum) {
        try {
            final Set<Role> roles = new HashSet<>();
            roles.add(Jobs4URoles.CANDIDATE);

            final Calendar createdOn = Calendar.getInstance();

            registerCandidateController.registerCandidate(firstName, lastName, email, roles, createdOn, telephoneNumber, curriculum);

            LOGGER.debug("»»» %s", email);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            LOGGER.error("Error performing the operation", e);
            System.out.println("Unfortunatelly there was an unexpected error in the application. Please try again and if the problem persists, contact your system admnistrator.");
        }
    }
}
