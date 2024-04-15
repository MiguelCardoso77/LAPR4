package authentication;

import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.HashSet;

public class AddUserUI extends AbstractUI {
    private final AddUserController theController = new AddUserController();

    public void registerUser() {
        System.out.println("\nRegister a new user:");
        final String username = Console.readLine("Username:");
        final String password = Console.readLine("Password:");
        final String firstName = Console.readLine("First name:");
        final String lastName = Console.readLine("Last name:");
        final String email = Console.readLine("Email:");
        Role[] roles = theController.getRoleTypes();

        theController.addUser(username, password, firstName, lastName, email, new HashSet<>());
    }

    @Override
    protected boolean doShow() {
        return false;
    }

    @Override
    public String headline() {
        return null;
    }
}

