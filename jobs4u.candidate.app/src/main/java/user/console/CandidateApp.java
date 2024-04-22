package user.console;

import core.domain.user.Jobs4UPasswordPolicy;
import user.console.presentation.FrontMenu;
import core.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;

/**
 * Base User App.
 */
@SuppressWarnings("squid:S106")
public final class CandidateApp {

    /**
     * Empty constructor is private to avoid instantiation of this class.
     */
    private CandidateApp() {
    }

    public static void main(final String[] args) {
        System.out.println("=====================================");
        System.out.println("Candidate App");
        System.out.println("(C) 2024");
        System.out.println("=====================================");

        AuthzRegistry.configure(PersistenceContext.repositories().users(), new Jobs4UPasswordPolicy(), new PlainTextEncoder());
        new FrontMenu().show();

        // exiting the application, closing all threads
        System.exit(0);
    }
}
