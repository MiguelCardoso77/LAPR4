package bootstrappers;

import bootstrappers.bootstraping.*;
import core.persistence.PersistenceContext;
import core.domain.user.Jobs4URoles;
import core.domain.user.UserBuilderHelper;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.strings.util.Strings;
import eapli.framework.validations.Invariants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Bootstraps the core data required for the application.
 * This includes initializing essential entities and setting up initial user accounts.
 * The bootstrapper creates a master user account with administrative privileges.
 *
 * @author Miguel Cardoso
 */
@SuppressWarnings("squid:S106")
public class Jobs4UBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(Jobs4UBootstrapper.class);
    private static final String BOOTSTRAP = "bootstrapmachine@jobs4u.com";
    private static final String BOOTSTRAP_PWD = "Bootstrap9000";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AuthenticationService authenticationService = AuthzRegistry.authenticationService();
    private final UserRepository userRepository = PersistenceContext.repositories().users();

    /**
     * Executes the bootstrapping process.
     * This method initializes the core data and sets up initial user accounts.
     *
     * @return true if bootstrapping is successful, false otherwise
     */
    @Override
    public boolean execute() {
        System.out.println("Bootstrapping MasterUser...");
        registerBootstrapAccount();
        authenticateForBootstrapping();

        final Action[] actions = {
                new InterviewModelBootstrapper(),
                new RequirementsBootstrapper(),
                new CompanyBootstrapper(),
                new MasterUsersBootstrapper(),
                new JobOpeningsBootstrapper(),
                new ApplicationsBootstrapper(),
                new JobInterviewsBootstrapper(),
                new NotificationsBootstrapper(),
        };

        boolean ret = true;
        for (final Action boot : actions) {
            System.out.println("\nBootstrapping " + entityName(boot) + "...");
            ret &= boot.execute();
        }

        return ret;
    }

    /**
     * Registers a master user account.
     * This method creates a system user with administrative privileges.
     */
    private void registerBootstrapAccount() {
        final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername(BOOTSTRAP).withPassword(BOOTSTRAP_PWD).withName("Bootstrap", "Machine").withEmail("bootstrapmachine@jobs4u.com").withRoles(Jobs4URoles.BOOTSTRAP);
        final SystemUser newUser = userBuilder.build();

        SystemUser bootstrapper;
        try {
            bootstrapper = userRepository.save(newUser);
            assert bootstrapper != null;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
            LOGGER.trace("Assuming existing record", e);
        }
    }

    /**
     * Authenticates the master user for bootstrapping.
     * This method ensures that the master user has a valid session to perform bootstrapping actions.
     */
    protected void authenticateForBootstrapping() {
        authenticationService.authenticate(BOOTSTRAP, BOOTSTRAP_PWD);
        Invariants.ensure(authz.hasSession());
    }

    /**
     * Extracts the name of the entity from the bootstrapping action class name.
     *
     * @param boot the bootstrapping action
     * @return the name of the entity being bootstrapped
     */
    private String entityName(final Action boot) {
        final String name = boot.getClass().getSimpleName();
        return Strings.left(name, name.length() - "Bootstrapper".length());
    }
}
