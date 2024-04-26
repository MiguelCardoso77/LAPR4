package user.console;

import core.domain.user.Jobs4UPasswordPolicy;
import user.console.presentation.FrontMenu;
import core.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;

/**
 * Main class for the Jobs4U Candidate Application.
 * This class initializes the application by configuring the authorization registry and displaying the front menu.
 *
 * @author Miguel Cardoso
 */
@SuppressWarnings("squid:S106")
public final class CandidateApp {

    /**
     * Private constructor to prevent instantiation of this class.
     */
    private CandidateApp() {
    }

    /**
     * Main method to start the application.
     *
     * @param args command-line arguments
     */
    public static void main(final String[] args) {
        System.out.println("=====================================");
        System.out.println("Candidate App");
        System.out.println("(C) 2024");
        System.out.println("=====================================");

        AuthzRegistry.configure(PersistenceContext.repositories().users(), new Jobs4UPasswordPolicy(), new PlainTextEncoder());
        new FrontMenu().show();

        System.exit(0);
    }
}
