package bootstrap;

import console.BaseApplication;
import bootstrappers.Jobs4UBootstrapper;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.pubsub.EventDispatcher;
import core.persistence.PersistenceContext;
import core.domain.user.Jobs4UPasswordPolicy;

public class Jobs4UBootstrap extends BaseApplication {
    private Jobs4UBootstrap() {
    }

    public static void main(final String[] args) {
        AuthzRegistry.configure(PersistenceContext.repositories().users(), new Jobs4UPasswordPolicy(), new PlainTextEncoder());
        new Jobs4UBootstrap().run(args);
    }

    @Override
    protected void doMain(final String[] args) {
        System.out.println("\n\n------- MASTER DATA -------");
        new Jobs4UBootstrapper().execute();
    }

    @Override
    protected String appTitle() {
        return "Bootstrapping Jobs4u Data ";
    }

    @Override
    protected String appGoodbye() {
        return "Bootstrap data done.";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doSetupEventHandlers(final EventDispatcher dispatcher) {
        // nothing to do
    }
}
