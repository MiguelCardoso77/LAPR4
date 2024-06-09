package backoffice;

import backoffice.presentation.MainMenu;
import console.BaseApplication;
import console.presentation.authz.LoginUI;
import core.domain.user.Jobs4UPasswordPolicy;
import core.domain.user.Jobs4URoles;
import infrastructure.authz.AuthenticationCredentialHandler;
import core.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.pubsub.EventDispatcher;

/**
 * The main class for the Jobs4u Back Office application.
 * This class initializes the authentication system, manages user login,
 * and displays the main menu of the application.
 *
 * <p>This application is intended for administrative users, such as
 * administrators, customer managers, language engineers, and operators.</p>
 *
 * <p>It extends the {@link BaseApplication} class and overrides its methods
 * to provide specific functionality for the Jobs4u Back Office.</p>
 *
 * <p>Note: This class suppresses warnings for standard input/output operations.</p>
 *
 * @author Miguel Cardoso
 * @see BaseApplication
 * @see LoginUI
 * @see MainMenu
 * @see AuthzRegistry
 * @see Jobs4UPasswordPolicy
 * @see PlainTextEncoder
 * @see AuthenticationCredentialHandler
 * @see Jobs4URoles
 * @see PersistenceContext
 * @see EventDispatcher
 */
@SuppressWarnings("squid:S106")
public final class Jobs4uBackOffice extends BaseApplication {

    /**
     * Default constructor for the Jobs4uBackOffice application.
     */
    public Jobs4uBackOffice() {
    }

    /**
     * The main entry point for the Jobs4u Back Office application.
     * This method sets up the authentication registry and starts the application.
     *
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        AuthzRegistry.configure(PersistenceContext.repositories().users(), new Jobs4UPasswordPolicy(), new PlainTextEncoder());
        new Jobs4uBackOffice().run(args);
    }

    /**
     * The main method that contains the application's primary logic.
     * It handles user authentication and displays the main menu upon successful login.
     *
     * @param args the command line arguments
     */
    @Override
    protected void doMain(final String[] args) {
        LoginUI loginUI = new LoginUI(new AuthenticationCredentialHandler(),
                Jobs4URoles.ADMIN,
                Jobs4URoles.CUSTOMER_MANAGER,
                Jobs4URoles.LANGUAGE_ENGINEER,
                Jobs4URoles.OPERATOR);

        if (loginUI.show()) {
            final MainMenu menu = new MainMenu();
            menu.mainLoop();
        }
    }

    /**
     * Returns the title of the application.
     *
     * @return the application title
     */
    @Override
    protected String appTitle() {
        return "Jobs4u Back Office";
    }

    /**
     * Returns the goodbye message for the application.
     *
     * @return the application goodbye message
     */
    @Override
    protected String appGoodbye() {
        return "Jobs4u Back Office";
    }

    /**
     * Sets up event handlers for the application.
     * Currently, there are no event handlers to set up.
     *
     * @param dispatcher the event dispatcher
     */
    @Override
    protected void doSetupEventHandlers(final EventDispatcher dispatcher) {
        // nothing to do
    }
}