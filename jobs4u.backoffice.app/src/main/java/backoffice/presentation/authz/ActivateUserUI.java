package backoffice.presentation.authz;

import console.presentation.utils.ConsoleColors;
import core.application.controllers.ActivateUserController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * UI class responsible for handling the activation of users.
 * It interacts with the ActivateUserController to perform the necessary actions.
 * This class displays a list of deactivated users and allows the operator to select a user to activate.
 *
 * @author Diana Neves
 */
public class ActivateUserUI extends AbstractUI {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivateUserUI.class);

    private final ActivateUserController theController = new ActivateUserController();

    /**
     * Displays the UI for activating a user.
     *
     * @return true if the user activation process is successful, false otherwise.
     */
    @Override
    protected boolean doShow() {
        final List<SystemUser> list = new ArrayList<>();
        final Iterable<SystemUser> iterable = this.theController.deactivatedUsers();

        if (!iterable.iterator().hasNext()) {
            System.out.println("There is no registered User");

        } else {
            int cont = 1;

            System.out.println("Select User to activate\n");
            System.out.printf("%-6s%-10s%-30s%-30s%n", "Nº:", "Email:", "Firstname", "Lastname");

            for (final SystemUser user : iterable) {
                list.add(user);
                System.out.printf("%-6d%-10s%-30s%-30s%n", cont, user.email(), user.name().firstName(), user.name().lastName());
                cont++;
            }

            final int option = Console.readInteger("Enter user number to activate or 0 to finish ");

            if (option == 0) {
                System.out.println("No user selected");
            } else {

                try {
                    this.theController.activateUser(list.get(option - 1));
                } catch (IntegrityViolationException | ConcurrencyException ex) {
                    LOGGER.error("Error performing the operation", ex);
                    System.out.println(ConsoleColors.RED + "Unfortunately there was an unexpected error in the application. Please try again." + ConsoleColors.RESET);
                }
            }
        }
        return true;
    }

    /**
     * Method that returns the headline of the UI.
     *
     * @return the headline of the UI.
     */
    @Override
    public String headline() {
        return "Activate User";
    }
}
