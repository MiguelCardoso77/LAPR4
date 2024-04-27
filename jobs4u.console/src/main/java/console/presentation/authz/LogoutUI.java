package console.presentation.authz;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;

/**
 * LogoutUI represents a user interface for performing logout operations.
 * It clears the user's session and displays a logout message.
 *
 * @author Miguel Cardoso
 */
public class LogoutUI extends AbstractUI {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * Returns the headline for the logout UI.
     *
     * @return the headline for the logout UI
     */
    @Override
    public String headline() {
        return "Logout sucessful!!\n Make a new Login";
    }

    /**
     * Performs the logout operation by clearing the user's session.
     *
     * @return true indicating that the operation was successful
     */
    @Override
    protected boolean doShow() {
        authz.clearSession();
        return true;
    }
}
