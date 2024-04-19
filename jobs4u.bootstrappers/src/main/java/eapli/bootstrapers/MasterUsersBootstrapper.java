package eapli.bootstrapers;

import eapli.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.Role;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Paulo Gandra Sousa
 */
public class MasterUsersBootstrapper extends UsersBootstrapper implements Action {

    @Override
    public boolean execute() {
        registerAdmin("admin", TestDataConstants.PASSWORD1, "Jane", "Doe Admin", "jane.doe@email.local");
        registerAdmin("Miguel77", "Miguel2004", "Miguel", "Cardoso", "miguel7704@gmail.com");
        return true;
    }

    /**
     *
     */
    private void registerAdmin(final String username, final String password, final String firstName, final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.ADMIN);
        registerUser(username, password, firstName, lastName, email, roles);
    }
}
