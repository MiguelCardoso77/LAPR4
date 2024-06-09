package core.application.controllers;

import core.domain.user.Jobs4URoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

/**
 * Controller for managing user deactivation in the Jobs4U system.
 * This class provides methods to retrieve active users and to deactivate a user.
 * It uses the AuthorizationService and UserManagementService from the eapli framework.
 *
 * @author Miguel Cardoso
 */
@UseCaseController
public class DeactivateUserController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final UserManagementService userSvc = AuthzRegistry.userService();

    /**
     * Retrieves all active users.
     *
     * @return an iterable collection of active users
     */
    public Iterable<SystemUser> activeUsers() {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.ADMIN);

        return userSvc.activeUsers();
    }

    /**
     * Deactivates a user.
     *
     * @param user the user to deactivate
     */
    public void deactivateUser(final SystemUser user) {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.ADMIN);

        userSvc.deactivateUser(user);
    }
}
