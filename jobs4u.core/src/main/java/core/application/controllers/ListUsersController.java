package core.application.controllers;

import core.domain.user.Jobs4URoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controller for managing the listing of users in the Jobs4U system.
 * This class provides methods to retrieve all users, all backoffice users, and to find a user by username.
 * It uses the AuthorizationService and UserManagementService from the eapli framework.
 *
 * @author Miguel Cardoso
 */
@UseCaseController
public class ListUsersController{
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final UserManagementService userSvc = AuthzRegistry.userService();

    /**
     * Retrieves all users.
     *
     * @return an iterable collection of all users
     */
    public Iterable<SystemUser> allUsers() {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.ADMIN);

        return userSvc.allUsers();
    }

    /**
     * Retrieves all backoffice users.
     *
     * @return an iterable collection of all backoffice users
     */
    public Iterable<SystemUser> allBackofficeUsers() {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.ADMIN);
        List<SystemUser> backofficeUsers = new ArrayList<>();

        for (SystemUser u : userSvc.allUsers()) {
            if (u.hasAny(Jobs4URoles.ADMIN, Jobs4URoles.BOOTSTRAP, Jobs4URoles.CUSTOMER_MANAGER, Jobs4URoles.LANGUAGE_ENGINEER, Jobs4URoles.OPERATOR)) {
                backofficeUsers.add(u);
            }
        }

        return backofficeUsers;
    }

    /**
     * Finds a user by their username.
     *
     * @param u the username of the user to find
     * @return the user with the specified username
     */
    public Optional<SystemUser> find(final Username u) {
        return userSvc.userOfIdentity(u);
    }
}
