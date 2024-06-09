package core.application.controllers;

import core.domain.user.Jobs4URoles;
import core.domain.user.Jobs4UPasswordPolicy;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.time.util.CurrentTimeCalendars;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

/**
 * Controller for adding users.
 * This class provides methods for adding users, generating passwords, getting all roles, and getting all users.
 *
 * @author Diogo Ribeiro
 */
@UseCaseController
public class AddUserController {

    /**
     * Authorization service instance.
     */
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * User management service instance.
     */
    private final UserManagementService userSvc = AuthzRegistry.userService();

    /**
     * Get existing RoleTypes available to the user.
     *
     * @return an array of RoleTypes
     */
    public Role[] getAllRoles() {
        return Jobs4URoles.allValues();
    }

    /**
     * Get existing RoleTypes available to the user.
     *
     * @return a list of RoleTypes
     */
    public String passwordGenerator(String name) {
        Jobs4UPasswordPolicy generator = new Jobs4UPasswordPolicy();
        return generator.passwordGenerator(name);
    }

    /**
     * Retrieves the currently logged-in user.
     *
     * @return The currently logged-in user, or null if no user is logged in.
     */
    public SystemUser getLoggedInUser() {
        return authz.session().map(UserSession::authenticatedUser).orElse(null);
    }

    /**
     * Adds a new user with the provided details.
     *
     * @param password  The password of the new user.
     * @param firstName The first name of the new user.
     * @param lastName  The last name of the new user.
     * @param email     The email of the new user.
     * @param roles     The roles of the new user.
     * @param createdOn The creation date of the new user.
     * @return The created user.
     */
    public SystemUser addUser(final String password, final String firstName, final String lastName, final String email, final Set<Role> roles, final Calendar createdOn) {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.ADMIN, Jobs4URoles.CUSTOMER_MANAGER, Jobs4URoles.OPERATOR);

        return userSvc.registerNewUser(email, password, firstName, lastName, email, roles, createdOn);
    }

    /**
     * Adds a new user with the provided details.
     *
     * @param password  The password of the new user.
     * @param firstName The first name of the new user.
     * @param lastName  The last name of the new user.
     * @param email     The email of the new user.
     * @param roles     The roles of the new user.
     * @return The created user.
     */
    public SystemUser addUser(final String password, final String firstName, final String lastName, final String email, final Set<Role> roles) {
        return addUser(password, firstName, lastName, email, roles, CurrentTimeCalendars.now());
    }

    /**
     * Retrieves all users.
     *
     * @return A list of all users.
     */
    public List<SystemUser> allUsers() {
        return (List<SystemUser>) userSvc.allUsers();
    }
}