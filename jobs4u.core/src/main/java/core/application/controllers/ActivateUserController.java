package core.application.controllers;

import core.domain.user.Jobs4URoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

/**
 * Controller for managing user activation in the Jobs4U system.
 * This class provides methods to retrieve deactivated users and to activate a user.
 * It uses the AuthorizationService and UserManagementService from the eapli framework.
 *
 * @author Diana Neves
 */
public class ActivateUserController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final UserManagementService userSvc = AuthzRegistry.userService();

    /**
     * Retrieves all deactivated users.
     *
     * @return an iterable collection of deactivated users
     */
    public Iterable<SystemUser> deactivatedUsers() {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.ADMIN);

        return userSvc.deactivatedUsers();
    }

    /**
     * Activates a user.
     *
     * @param user the user to activate
     */
    public void activateUser(final SystemUser user) {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.ADMIN);

        userSvc.activateUser(user);
    }
}
