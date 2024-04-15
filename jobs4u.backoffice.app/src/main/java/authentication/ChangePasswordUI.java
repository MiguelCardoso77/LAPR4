package authentication;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class ChangePasswordUI extends AbstractUI {

    private final AuthenticationService authenticationService = AuthzRegistry.authenticationService();

    @Override
    protected boolean doShow() {
        final String oldPassword = Console.readLine("Old Password:");
        final String newPassword = Console.readLine("New Password:");

        try {
            boolean toContinue;
            if (authenticationService.changePassword(oldPassword, newPassword)) {
                System.out.println("Password Successfuly changed");
                toContinue = true;
            } else {
                System.out.println("Invalid authentication");
                toContinue = false;
            }
            return toContinue;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            System.out.println("An error has occurred> " + e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public String headline() {
        return "Change Password";
    }
}