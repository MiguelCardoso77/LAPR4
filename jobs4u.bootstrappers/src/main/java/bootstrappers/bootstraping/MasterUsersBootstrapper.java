package bootstrappers.bootstraping;

import core.domain.company.Company;
import core.domain.company.CompanyName;
import core.domain.user.Jobs4URoles;
import core.persistence.PersistenceContext;
import core.repositories.CompanyRepository;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Paulo Gandra Sousa
 */
public class MasterUsersBootstrapper extends UsersBootstrapper implements Action {

    UserRepository userRepository = PersistenceContext.repositories().users();
    CompanyRepository companyRepository = PersistenceContext.repositories().companies();
    @Override
    public boolean execute() {
        registerAdmin("admin", TestDataConstants.PASSWORD1, "Jane", "Doe Admin", "jane.doe@email.local");

        registerAdmin("AdminEx", "ExAdmin1", "Admin", "Example", "exadmin@gmail.com");
        registerCustomerManager("CustomerManagerEx", "ExCustomerManager1", "CustomerManager", "Example", "excustomermanager@gmail.com");
        registerLanguageEngineer("LanguageEngineerEx", "ExLanguageEngineer1", "LanguageEngineer", "Example", "exlanguageengineer@gmail.com");
        registerOperator("OperatorEx", "ExOperator1", "Operator", "Example", "exoperator@gmail.com");

        addSignUpRequest("excandidate2@gmail.com", "ExCandidate2", "CandidateTwo", "ExampleTwo",  "912345999");
        addSignUpRequest("excandidate3@gmail.com", "ExCandidate3", "CandidateThree", "ExampleThree", "912345000");
        addSignUpRequest("excustomer2@gmail.com", "ExCustomer2", "CustomerTwo", "ExampleTwo", "922000888");

        registerBootstrapCandidate("CandidateOne", "ExampleOne", "candidateOne@gmail.com", "910920930", "curriculumPathOne");
        registerBootstrapCandidate("CandidateTwo", "ExampleTwo", "candidateTwo@gmail.com", "940950960", "curriculumpPathTwo");

        registerCustomer("CustomerOne", "ExampleOne", "customerOne@gmail.com");
        registerCustomer("CustomerTwo", "ExampleTwo", "customerTwo@gmail.com");

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

    private void registerBootstrapCandidate(final String firstName, final String lastName, final String email, final String telephoneNumber, final String curriculum) {
        registerCandidate(firstName, lastName, email, telephoneNumber, curriculum);
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

    private void registerCustomer(final String firstName, final String lastName, final String email){
        Optional<SystemUser> currentUser = userRepository.ofIdentity(Username.valueOf("excustomermanager@gmail.com"));
        Optional<Company> company = companyRepository.ofIdentity(1);

        SystemUser customerManager = currentUser.get();
        Company selectedCompany = company.get();

        final Set<Role> roles = new HashSet<>();
        roles.add(Jobs4URoles.CUSTOMER);
        registerCustomer(firstName, lastName, email, customerManager, selectedCompany);
    }

    private void addSignUpRequest(final String email, final String password, final String firstName, final String lastName, final String telephoneNumber) {
        signupUser(password, firstName, lastName, email, telephoneNumber);
    }
}