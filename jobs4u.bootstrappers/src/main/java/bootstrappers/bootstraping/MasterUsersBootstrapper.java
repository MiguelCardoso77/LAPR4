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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Bootstraps master users, including admin, customer managers, language engineers, and operators.
 * Additionally, registers bootstrap candidates and customers.
 * Requires access to the AddUserController, RegisterCandidateController, and AddCustomerController.
 * Extends Action to be used as part of the bootstrapping process.
 *
 * @author Miguel Cardoso
 */
public class MasterUsersBootstrapper extends UsersBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(MasterUsersBootstrapper.class);

    private final UserRepository userRepository = PersistenceContext.repositories().users();
    private final CompanyRepository companyRepository = PersistenceContext.repositories().companies();

    @Override
    public boolean execute() {
        registerBackOfficeUser("AdminEx", "ExAdmin1", "Admin", "Example", "admin@jobs4u.com", Jobs4URoles.ADMIN);
        registerBackOfficeUser("CustomerManagerEx", "ExCustomerManager1", "Customer", "Manager", "customermanager@jobs4u.com", Jobs4URoles.CUSTOMER_MANAGER);
        registerBackOfficeUser("LanguageEngineerEx", "ExLanguageEngineer1", "Language", "Engineer", "languageengineer@jobs4u.com", Jobs4URoles.LANGUAGE_ENGINEER);
        registerBackOfficeUser("OperatorEx", "ExOperator1", "Operator", "Example", "operator@jobs4u.com", Jobs4URoles.OPERATOR);

        registerBootstrapCandidate("Diana", "Neves", "1221194@isep.ipp.pt", "911111111", "curriculumPathOne");
        registerBootstrapCandidate("Diogo", "Ribeiro", "1220812@isep.ipp.pt", "922222222", "fileBot_OutputDirectory/3");
        registerBootstrapCandidate("Miguel", "Cardoso", "1220772@isep.ipp.pt", "933333333", "curriculumPathThree");
        registerBootstrapCandidate("Tomás", "Gonçalves", "1220917@isep.ipp.pt", "944444444", "fileBot_OutputDirectory/IBM-000123/1/1-cv.txt");
        registerBootstrapCandidate("Marco", "Ferreira", "1220913@isep.ipp.pt", "955555555", "fileBot_OutputDirectory/IBM-000123/1/1-cv.txt");
        registerBootstrapCandidate("Carolina", "Sousa", "1220618@isep.ipp.pt", "966666666", "fileBot_OutputDirectory/IBM-000123/1/1-cv.txt");

        registerCustomer("CustomerOne", "ExampleOne", "customerOne@jobs4u.com");
        registerCustomer("CustomerTwo", "ExampleTwo", "customerTwo@jobs4u.com");

        return true;
    }

    /**
     * Registers a back office user with the given details.
     * @param username the username
     * @param password the password
     * @param firstName the first name
     * @param lastName the last name
     * @param email the email
     * @param role the role
     */
    private void registerBackOfficeUser(final String username, final String password, final String firstName, final String lastName, final String email, final Role role) {
        final Set<Role> roles = new HashSet<>();
        roles.add(role);
        registerUser(username, password, firstName, lastName, email, roles);
    }

    /**
     * Registers a bootstrap candidate with the given details.
     * @param firstName the first name
     * @param lastName the last name
     * @param email the email
     * @param telephoneNumber the telephone number
     * @param curriculum the curriculum
     */
    private void registerBootstrapCandidate(final String firstName, final String lastName, final String email, final String telephoneNumber, final String curriculum) {
        registerCandidate(firstName, lastName, email, telephoneNumber, curriculum);
    }

    /**
     * Registers a new customer with the given details.
     *
     * @param firstName the first name of the customer
     * @param lastName the last name of the customer
     * @param email the email address of the customer
     */
    private void registerCustomer(final String firstName, final String lastName, final String email) {
        Optional<SystemUser> currentUser = userRepository.ofIdentity(Username.valueOf("customermanager@jobs4u.com"));
        Optional<Company> company = companyRepository.ofIdentity(1);

        if (currentUser.isPresent() && company.isPresent()) {

            SystemUser customerManager = currentUser.get();
            Company selectedCompany = company.get();
            registerCustomer(firstName, lastName, email, customerManager, selectedCompany);

        } else {
            LOGGER.error("Failed to register customer: Either customer manager or company not found");
        }
    }
}