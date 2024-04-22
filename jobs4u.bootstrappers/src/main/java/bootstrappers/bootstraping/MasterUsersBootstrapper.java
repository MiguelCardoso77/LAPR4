package bootstrappers.bootstraping;

import core.domain.user.Jobs4URoles;
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
        registerCandidate("CandidateEx", "ExCandidate1", "CandidateOne", "ExampleOne", "excandidate1@gmail.com");
        registerCustomer("CustomerEx", "ExCustomer1", "CustomerOne", "ExampleOne", "excustomer1@gmail.com");
        registerCustomerManager("CustomerManagerEx", "ExCustomerManager1", "CustomerManager", "Example", "excustomermanager@gmail.com");
        registerLanguageEngineer("LanguageEngineerEx", "ExLanguageEngineer1", "LanguageEngineer", "Example", "exlanguageengineer@gmail.com");
        registerOperator("OperatorEx", "ExOperator1", "Operator", "Example", "exoperator@gmail.com");

        addSignUpRequest("excandidate2@gmail.com", "ExCandidate2", "CandidateTwo", "ExampleTwo",  "912345999");
        addSignUpRequest("excandidate3@gmail.com", "ExCandidate3", "CandidateThree", "ExampleThree", "912345000");
        addSignUpRequest("excustomer2@gmail.com", "ExCustomer2", "CustomerTwo", "ExampleTwo", "922000888");

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

    private void addSignUpRequest(final String email, final String password, final String firstName, final String lastName, final String telephoneNumber) {
        signupUser(password, firstName, lastName, email, telephoneNumber);
    }
}
