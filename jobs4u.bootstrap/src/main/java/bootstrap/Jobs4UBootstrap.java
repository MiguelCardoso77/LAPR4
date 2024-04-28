package bootstrap;

import bootstrappers.Jobs4USmokeTester;
import console.BaseApplication;
import bootstrappers.Jobs4UBootstrapper;
import core.application.eventhandlers.NewUserRegisteredFromSignupWatchDog;
import core.domain.events.NewUserRegisteredFromSignupEvent;
import core.domain.events.SignupAcceptedEvent;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.pubsub.EventDispatcher;
import core.persistence.PersistenceContext;
import core.application.eventhandlers.SignupAcceptedWatchDog;
import core.domain.user.Jobs4UPasswordPolicy;

public class Jobs4UBootstrap extends BaseApplication {
    private Jobs4UBootstrap() {
    }

    private final boolean isToBootstrapTestData = false;

    public static void main(final String[] args) {
        AuthzRegistry.configure(PersistenceContext.repositories().users(), new Jobs4UPasswordPolicy(), new PlainTextEncoder());
        new Jobs4UBootstrap().run(args);
    }

    @Override
    protected void doMain(final String[] args) {
        if (isToBootstrapTestData) {
            System.out.println("\n\n------- TEST DATA -------");
            new Jobs4USmokeTester().execute();
        } else {
            System.out.println("\n\n------- MASTER DATA -------");
            new Jobs4UBootstrapper().execute();
        }
    }

    @Override
    protected String appTitle() {
        if (isToBootstrapTestData) {
            return "Bootstrapping Jobs4u test data";
        } else {
            return "Bootstrapping Jobs4u data ";
        }
    }

    @Override
    protected String appGoodbye() {
        return "Bootstrap data done.";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doSetupEventHandlers(final EventDispatcher dispatcher) {
        dispatcher.subscribe(new NewUserRegisteredFromSignupWatchDog(), NewUserRegisteredFromSignupEvent.class);
        dispatcher.subscribe(new SignupAcceptedWatchDog(), SignupAcceptedEvent.class);
    }
}
