package core.application.controllers;

import core.domain.company.Company;
import core.domain.customer.Customer;
import core.domain.customer.CustomerBuilder;
import core.domain.user.Jobs4URoles;
import core.domain.user.Jobs4UPasswordPolicy;
import core.services.CustomerService;
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
    private final CustomerService customerService = new CustomerService();

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
        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.BOOTSTRAP, Jobs4URoles.ADMIN, Jobs4URoles.CUSTOMER_MANAGER, Jobs4URoles.OPERATOR);

        return userSvc.registerNewUser(username, password, firstName, lastName, email, roles, createdOn);
    }

    public SystemUser addUser(String username, final String password, final String firstName, final String lastName, final String email, final Set<Role> roles) {
        username = email;
        return addUser(username, password, firstName, lastName, email, roles, CurrentTimeCalendars.now());
    }
}
















