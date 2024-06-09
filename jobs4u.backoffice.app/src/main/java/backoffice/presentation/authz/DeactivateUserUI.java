package backoffice.presentation.authz;

import core.application.controllers.DeactivateUserController;
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
 * UI class responsible for deactivating a user in the application.
 * <p>
 * This class provides functionality to interactively deactivate a user
 * by selecting from a list of active users. It delegates user deactivation
 * to the DeactivateUserController.
 *
 * @author Diana Neves
 */
@SuppressWarnings("squid:S106")
public class DeactivateUserUI extends AbstractUI {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeactivateUserUI.class);

    private final DeactivateUserController theController = new DeactivateUserController();

    /**
     * Displays the UI for deactivating a user.
     *
     * @return true indicating the operation is finished
     */
    @Override
    protected boolean doShow() {
        final List<SystemUser> list = new ArrayList<>();
        final Iterable<SystemUser> iterable = this.theController.activeUsers();

        if (!iterable.iterator().hasNext()) {
            System.out.println("There is no registered User");

        } else {

            int cont = 1;
            System.out.println("SELECT User to deactivate\n");
            System.out.printf("%-6s%-10s%-30s%-30s%n", "Nº:", "Username", "Firstname", "Lastname");

            for (final SystemUser user : iterable) {
                list.add(user);
                System.out.printf("%-6d%-10s%-30s%-30s%n", cont, user.username(), user.name().firstName(), user.name().lastName());
                cont++;
            }

            final int option = Console.readInteger("Enter user nº to deactivate or 0 to finish ");

            if (option == 0) {
                System.out.println("No user selected");

            } else {

                try {
                    this.theController.deactivateUser(list.get(option - 1));

                } catch (IntegrityViolationException | ConcurrencyException ex) {
                    LOGGER.error("Error performing the operation", ex);
                    System.out.println("Unfortunately there was an unexpected error in the application. Please try again.");
                }
            }
        }
        return true;
    }

    /**
     * The headline for this UI.
     *
     * @return the headline for this UI
     */
    @Override
    public String headline() {
        return "Deactivate User";
    }
}
