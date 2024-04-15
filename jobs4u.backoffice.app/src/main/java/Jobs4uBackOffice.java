import app.Jobs4uBaseApplication;
import authentication.AuthenticationCredentialHandler;
import authentication.Jobs4uPasswordPolicy;
import authentication.LoginUI;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.pubsub.EventDispatcher;
import menus.MainMenu;
import persistence.PersistenceContext;

public class Jobs4uBackOffice extends Jobs4uBaseApplication {

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
        new Jobs4uBackOffice().run(args);
    }

    /**
     * Method to run the application if the user is authenticated
     */
    @Override
    protected void doMain(final String[] args) {
        final boolean authenticated = new LoginUI(new AuthenticationCredentialHandler()).show();
        if (authenticated) {
            final var menu = new MainMenu();
            menu.mainLoop();
        }
    }

    @Override
    protected String appTitle() {
        return "Jobs4U Back Office";
    }

    @Override
    protected String appGoodbye() {
        return "Jobs4U Back Office";
    }

    @Override
    protected void configureAuthz() {
        AuthzRegistry.configure(PersistenceContext.repositories().users(), new Jobs4uPasswordPolicy(), new PlainTextEncoder());
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doSetupEventHandlers(final EventDispatcher dispatcher) {
        dispatcher.subscribe(new NewUserRegisteredFromSignupWatchDog(), NewUserRegisteredFromSignupEvent.class);
        dispatcher.subscribe(new SignupAcceptedWatchDog(), SignupAcceptedEvent.class);
        dispatcher.subscribe(new MealBookedWatchDog(), BookedEvent.class);
    }
}
