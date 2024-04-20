package user.console;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.persistence.PersistenceContext;
import eapli.usermanagement.domain.Jobs4UPasswordPolicy;
import user.console.presentation.FrontMenu;

/**
 * Base User App.
 */
@SuppressWarnings("squid:S106")
public final class CustomerApp {

    /**
     * Empty constructor is private to avoid instantiation of this class.
     */
    private CustomerApp() {
    }

    public static void main(final String[] args) {
        System.out.println("=====================================");
        System.out.println("Customer App");
        System.out.println("(C) 2024");
        System.out.println("=====================================");

        AuthzRegistry.configure(PersistenceContext.repositories().users(), new Jobs4UPasswordPolicy(), new PlainTextEncoder());
        new FrontMenu().show();

        // exiting the application, closing all threads
        System.exit(0);
    }
}
