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
 * Base Bootstrapping data app
 *
 * @author Paulo Gandra de Sousa
 */
@SuppressWarnings("squid:S106")
public class Jobs4UBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(Jobs4UBootstrapper.class);
    private static final String BOOTSTRAP = "bootstrap@gmail.com";
    private static final String BOOTSTRAP_PWD = "Bootstrap9000";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AuthenticationService authenticationService = AuthzRegistry.authenticationService();
    private final UserRepository userRepository = PersistenceContext.repositories().users();

    @Override
    public boolean execute() {
        final Action[] actions = {
                new CompanyBootstrapper(),
                new MasterUsersBootstrapper(),
                new JobsBootstrapper(),
                new ProcessBootstrapper(),
                new ApplicationsBootstrapper(),
                new JobInterviewsBootstrapper(),
                new RequirementsBootstrapper()};



        registerBootstrapAccount();
        authenticateForBootstrapping();

        // execute all bootstrapping
        boolean ret = true;
        for (final Action boot : actions) {
            System.out.println("\nBootstrapping " + nameOfEntity(boot) + "...");
            ret &= boot.execute();
        }
        return ret;
    }

    /**
     * register a power user directly in the persistence layer as we need to
     * circumvent authorisations in the Application Layer
     */
    private void registerBootstrapAccount() {
        final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername(BOOTSTRAP).withPassword(BOOTSTRAP_PWD).withName("Bootstrap", "Machine").withEmail("bootstrapmachine@gmail.com").withRoles(Jobs4URoles.BOOTSTRAP);
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
     * authenticate a super user to be able to register new users
     *
     */
    protected void authenticateForBootstrapping() {
        authenticationService.authenticate(BOOTSTRAP, BOOTSTRAP_PWD);
        Invariants.ensure(authz.hasSession());
    }

    private String nameOfEntity(final Action boot) {
        final String name = boot.getClass().getSimpleName();
        return Strings.left(name, name.length() - "Bootstrapper".length());
    }
}
