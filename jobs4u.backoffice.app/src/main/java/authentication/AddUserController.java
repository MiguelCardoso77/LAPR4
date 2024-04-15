package authentication;

import domain.Jobs4uRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.time.util.CurrentTimeCalendars;

import java.util.Calendar;
import java.util.Set;

public class AddUserController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final UserManagementService userSvc = AuthzRegistry.userService();

    /**
     * Get existing RoleTypes available to the user.
     *
     * @return an array of RoleTypes
     */
    public Role[] getRoleTypes() {
        return Jobs4uRoles.getAllAssignableRoles();
    }

    public SystemUser addUser(final String username, final String password, final String firstName, final String lastName, final String email, final Set<Role> roles, final Calendar createdOn) {
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4uRoles.CANDIDATE, Jobs4uRoles.ADMIN);
        return userSvc.registerNewUser(username, password, firstName, lastName, email, roles, createdOn);
    }

    public SystemUser addUser(final String username, final String password, final String firstName, final String lastName, final String email, final Set<Role> roles) {
        return addUser(username, password, firstName, lastName, email, roles, CurrentTimeCalendars.now());
    }
}
