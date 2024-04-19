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
        registerCandidate("CandidateEx", "ExCandidate1", "Candidate", "Example", "excandidate@gmail.com");
        registerCustomer("CustomerEx", "ExCustomer1", "Customer", "Example", "excustomer@gmail.com");
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

    private void registerCandidate(final String username, final String password, final String firstName, final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.CANDIDATE);
        registerUser(username, password, firstName, lastName, email, roles);
    }

    private void registerCustomer(final String username, final String password, final String firstName, final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(BaseRoles.CUSTOMER);
        registerUser(username, password, firstName, lastName, email, roles);
    }
}
