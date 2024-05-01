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
 *
 * @author Paulo Gandra Sousa
 */
@SuppressWarnings("squid:S106")
public final class Jobs4uBackOffice extends BaseApplication {

    public Jobs4uBackOffice() {
    }

    public static void main(final String[] args) {
        AuthzRegistry.configure(PersistenceContext.repositories().users(), new Jobs4UPasswordPolicy(), new PlainTextEncoder());
        new Jobs4uBackOffice().run(args);
    }

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
        // nothing to do
    }
}