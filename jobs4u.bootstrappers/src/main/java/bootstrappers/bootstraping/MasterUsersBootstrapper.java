package bootstrappers.bootstraping;

import core.domain.company.Company;
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
        registerAdmin("AdminEx", "ExAdmin1", "Admin", "Example", "admin@jobs4u.com");
        registerCustomerManager("CustomerManagerEx", "ExCustomerManager1", "CustomerManager", "Example", "customermanager@jobs4u.com");
        registerLanguageEngineer("LanguageEngineerEx", "ExLanguageEngineer1", "LanguageEngineer", "Example", "languageengineer@jobs4u.com");
        registerOperator("OperatorEx", "ExOperator1", "Operator", "Example", "operator@jobs4u.com");

        registerBootstrapCandidate("Diana", "Neves", "1221194@isep.ipp.pt", "911111111", "curriculumPathOne");
        registerBootstrapCandidate("Diogo", "Ribeiro", "1220812@isep.ipp.pt", "922222222", "fileBot_OutputDirectory/3");
        registerBootstrapCandidate("Miguel", "Cardoso", "1220772@isep.ipp.pt", "933333333", "curriculumPathThree");
        registerBootstrapCandidate("Tomás", "Gonçalves", "1220917@isep.ipp.pt", "944444444", "fileBot_OutputDirectory/IBM-000123/1/1-cv.txt");

        registerCustomer("CustomerOne", "ExampleOne", "customerOne@jobs4u.com");
        registerCustomer("CustomerTwo", "ExampleTwo", "customerTwo@jobs4u.com");

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
        Optional<SystemUser> currentUser = userRepository.ofIdentity(Username.valueOf("customermanager@jobs4u.com"));
        Optional<Company> company = companyRepository.ofIdentity(1);

        SystemUser customerManager = currentUser.get();
        Company selectedCompany = company.get();

        final Set<Role> roles = new HashSet<>();
        roles.add(Jobs4URoles.CUSTOMER);
        registerCustomer(firstName, lastName, email, customerManager, selectedCompany);
    }
}