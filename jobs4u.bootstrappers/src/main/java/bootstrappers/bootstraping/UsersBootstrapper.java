package bootstrappers.bootstraping;

import core.usermanagement.application.AddUserController;
import core.usermanagement.application.ListUsersController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.Username;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class UsersBootstrapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersBootstrapper.class);

    final AddUserController userController = new AddUserController();
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
}
