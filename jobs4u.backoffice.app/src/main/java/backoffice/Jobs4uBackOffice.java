package backoffice;

import backoffice.presentation.MainMenu;
import console.BaseApplication;
import console.presentation.authz.LoginUI;
import eapli.clientusermanagement.application.eventhandlers.NewUserRegisteredFromSignupWatchDog;
import eapli.clientusermanagement.domain.events.NewUserRegisteredFromSignupEvent;
import eapli.clientusermanagement.domain.events.SignupAcceptedEvent;
import eapli.usermanagement.domain.Jobs4URoles;
import infrastructure.authz.AuthenticationCredentialHandler;
import eapli.persistence.PersistenceContext;
import eapli.usermanagement.application.eventhandlers.SignupAcceptedWatchDog;
import eapli.usermanagement.domain.BasePasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.pubsub.EventDispatcher;

/**
 *
 * @author Paulo Gandra Sousa
 */
@SuppressWarnings("squid:S106")
public final class Jobs4uBackOffice extends BaseApplication {

    /**
     * avoid instantiation of this class.
     */
    private Jobs4uBackOffice() {
    }

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(final String[] args) {
        AuthzRegistry.configure(PersistenceContext.repositories().users(), new BasePasswordPolicy(), new PlainTextEncoder());
        new Jobs4uBackOffice().run(args);
    }

    @Override
    protected void doMain(final String[] args) {
        // login and go to the main menu
        LoginUI loginUI = new LoginUI(new AuthenticationCredentialHandler(),
                Jobs4URoles.ADMIN,
                Jobs4URoles.CUSTOMER_MANAGER,
                Jobs4URoles.LANGUAGE_ENGINEER,
                Jobs4URoles.OPERATOR);

        if (loginUI.show()) {
            // Go to the main menu
            final MainMenu menu = new MainMenu();
            menu.mainLoop();
        }
    }

    @Override
    protected String appTitle() {
        return "Jobs4u Back Office";
    }

    @Override
    protected String appGoodbye() {
        return "Jobs4u Back Office";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doSetupEventHandlers(final EventDispatcher dispatcher) {
        dispatcher.subscribe(new NewUserRegisteredFromSignupWatchDog(), NewUserRegisteredFromSignupEvent.class);
        dispatcher.subscribe(new SignupAcceptedWatchDog(), SignupAcceptedEvent.class);
    }
}
