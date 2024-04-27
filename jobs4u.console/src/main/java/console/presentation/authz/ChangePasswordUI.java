package console.presentation.authz;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

/**
 * UI for changing user passwords.
 * Allows users to change their passwords by providing their old and new passwords.
 * Handles password change requests through the AuthenticationService.
 *
 * @author Miguel Cardoos
 */
@SuppressWarnings("squid:S106")
public class ChangePasswordUI extends AbstractUI {
    private final AuthenticationService authenticationService = AuthzRegistry.authenticationService();

    /**
     * Displays the user interface for changing passwords.
     * Prompts users to enter their old and new passwords.
     * Calls the AuthenticationService to change the password.
     *
     * @return true if the password change is successful, false otherwise
     */
    @Override
    protected boolean doShow() {
        final String oldPassword = Console.readLine("Old Password:");
        final String newPassword = Console.readLine("New Password:");

        try {
            if (this.authenticationService.changePassword(oldPassword, newPassword)) {
                System.out.println("Password Successfully changed");
                return true;
            } else {
                System.out.println("Invalid authentication");
                return false;
            }
        } catch (ConcurrencyException | IntegrityViolationException e) {
            System.out.println("An error has occurred> " + e.getLocalizedMessage());
            return false;
        }
    }

    /**
     * Returns the headline for the change password user interface.
     *
     * @return the headline for the change password user interface
     */
    @Override
    public String headline() {
        return "Change Password";
    }
}
