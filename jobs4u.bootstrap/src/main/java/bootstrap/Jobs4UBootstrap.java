package bootstrap;

import console.BaseApplication;
import bootstrappers.Jobs4UBootstrapper;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.pubsub.EventDispatcher;
import core.persistence.PersistenceContext;
import core.domain.user.Jobs4UPasswordPolicy;

/**
 * Bootstrap application for Jobs4U.
 * This class configures the authentication registry and runs the bootstrapping process for the Jobs4U application.
 *
 * @author Miguel Cardoso
 */
public class Jobs4UBootstrap extends BaseApplication {

    /**
     * Private constructor to prevent instantiation.
     */
    private Jobs4UBootstrap() {
    }

    /**
     * Main method to run the bootstrap process.
     *
     * @param args command-line arguments.
     */
    public static void main(final String[] args) {
        AuthzRegistry.configure(PersistenceContext.repositories().users(), new Jobs4UPasswordPolicy(), new PlainTextEncoder());
        new Jobs4UBootstrap().run(args);
    }

    /**
     * Executes the main logic of the application.
     *
     * @param args command-line arguments.
     */
    @Override
    protected void doMain(final String[] args) {
        new Jobs4UBootstrapper().execute();
    }

    /**
     * Returns the title of the application.
     *
     * @return the application title.
     */
    @Override
    protected String appTitle() {
        return "Bootstrapping Jobs4u Data ";
    }

    /**
     * Returns the goodbye message of the application.
     *
     * @return the application goodbye message.
     */
    @Override
    protected String appGoodbye() {
        return "Bootstrap data done.";
    }

    /**
     * Sets up event handlers. Currently, this implementation does not set up any handlers.
     *
     * @param dispatcher the event dispatcher.
     */
    @Override
    protected void doSetupEventHandlers(final EventDispatcher dispatcher) {
        // nothing to do
    }
}
