package bootstrappers.bootstraping;

import core.usermanagement.domain.Jobs4URoles;
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

        registerAdmin("AdminEx", "ExAdmin1", "Admin", "Example", "exadmin@gmail.com");
        registerCandidate("CandidateEx", "ExCandidate1", "Candidate", "Example", "excandidate@gmail.com");
        registerCustomer("CustomerEx", "ExCustomer1", "Customer", "Example", "excustomer@gmail.com");
        registerCustomerManager("CustomerManagerEx", "ExCustomerManager1", "CustomerManager", "Example", "excustomermanager@gmail.com");
        registerLanguageEngineer("LanguageEngineerEx", "ExLanguageEngineer1", "LanguageEngineer", "Example", "exlanguageengineer@gmail.com");
        registerOperator("OperatorEx", "ExOperator1", "Operator", "Example", "exoperator@gmail.com");
        return true;
    }

    /**
     *
     */
    private void registerAdmin(final String username, final String password, final String firstName, final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(Jobs4URoles.ADMIN);
        registerUser(username, password, firstName, lastName, email, roles);
    }

    private void registerCandidate(final String username, final String password, final String firstName, final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(Jobs4URoles.CANDIDATE);
        registerUser(username, password, firstName, lastName, email, roles);
    }

    private void registerCustomer(final String username, final String password, final String firstName, final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(Jobs4URoles.CUSTOMER);
        registerUser(username, password, firstName, lastName, email, roles);
    }

    private void registerCustomerManager(final String username, final String password, final String firstName, final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(Jobs4URoles.CUSTOMER_MANAGER);
        registerUser(username, password, firstName, lastName, email, roles);
    }

    private void registerLanguageEngineer(final String username, final String password, final String firstName, final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(Jobs4URoles.LANGUAGE_ENGINEER);
        registerUser(username, password, firstName, lastName, email, roles);
    }

    private void registerOperator(final String username, final String password, final String firstName, final String lastName, final String email) {
        final Set<Role> roles = new HashSet<>();
        roles.add(Jobs4URoles.OPERATOR);
        registerUser(username, password, firstName, lastName, email, roles);
    }
}
