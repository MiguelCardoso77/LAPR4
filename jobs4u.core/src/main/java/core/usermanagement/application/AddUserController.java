package core.usermanagement.application;

import core.usermanagement.domain.Jobs4URoles;
import core.usermanagement.domain.Jobs4UPasswordPolicy;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.time.util.CurrentTimeCalendars;

import java.util.Calendar;
import java.util.Set;

/**
 *
 * Created by nuno on 21/03/16.
 */
@UseCaseController
public class AddUserController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final UserManagementService userSvc = AuthzRegistry.userService();

    /**
     * Get existing RoleTypes available to the user.
     *
     * @return a list of RoleTypes
     */
    public Role[] getRoleTypes() {
        return Jobs4URoles.nonUserValues();
    }

    public Role[] getAllRoles() {
        return Jobs4URoles.allValues();
    }

    public String passwordGenerator(String name) {
        Jobs4UPasswordPolicy generator = new Jobs4UPasswordPolicy();
        return generator.passwordGenerator(name);
    }

    public SystemUser addUser(String username, final String password, final String firstName, final String lastName, final String email, final Set<Role> roles, final Calendar createdOn) {
        username = email;
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.POWER_USER, Jobs4URoles.ADMIN, Jobs4URoles.CUSTOMER_MANAGER, Jobs4URoles.OPERATOR);

        return userSvc.registerNewUser(username, password, firstName, lastName, email, roles, createdOn);
    }

    public SystemUser addUser(String username, final String password, final String firstName, final String lastName, final String email, final Set<Role> roles) {
        username = email;
        return addUser(username, password, firstName, lastName, email, roles, CurrentTimeCalendars.now());
    }
}
