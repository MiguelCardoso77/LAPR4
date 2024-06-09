package backoffice.presentation.authz;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import core.application.controllers.AddUserController;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;
import core.domain.user.Jobs4URoles;

import java.util.HashSet;
import java.util.Set;

/**
 * UI class responsible for adding a new user to the application.
 * <p>
 * This class provides functionality to interactively add a new user by
 * specifying their first name, last name, email, and roles.
 * It handles user input, generates a password, and delegates user addition
 * to the AddUserController.
 *
 * @author Diana Neves
 */
public class AddUserUI extends AbstractUI {
    private final AddUserController theController = new AddUserController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * Displays the UI for adding a new user.
     *
     * @return false indicating that the operation is finished
     */
    @Override
    protected boolean doShow() {
        final String firstName = Console.readLine("First Name");
        final String lastName = Console.readLine("Last Name");
        final String email = Console.readLine("E-Mail");
        final String password = theController.passwordGenerator(firstName);
        final Set<Role> roleTypes = new HashSet<>();

        boolean show;
        do {
            show = showRoles(roleTypes);
        } while (!show);

        try {
            this.theController.addUser(password, firstName, lastName, email, roleTypes);

        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("That email is already in use.");
        }

        return false;
    }

    /**
     * Displays the available roles based on the current user's authorization.
     *
     * @param roleTypes a set to store selected roles
     * @return true if the roles are shown successfully, false otherwise
     */
    private boolean showRoles(final Set<Role> roleTypes) {
        final Menu rolesMenu;

        if (authz.isAuthenticatedUserAuthorizedTo(Jobs4URoles.OPERATOR)) {
            rolesMenu = buildOperatorAvailableRoles(roleTypes);
        } else if (authz.isAuthenticatedUserAuthorizedTo(Jobs4URoles.CUSTOMER_MANAGER)) {
            rolesMenu = buildCustomerManagerAvailableRoles(roleTypes);
        } else {
            rolesMenu = buildRolesMenu(roleTypes);
        }

        final MenuRenderer renderer = new VerticalMenuRenderer(rolesMenu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    /**
     * Builds the menu for selecting roles based on available roles in the system.
     *
     * @param roleTypes a set to store selected roles
     * @return the constructed menu
     */
    private Menu buildRolesMenu(final Set<Role> roleTypes) {
        final Menu rolesMenu = new Menu();
        int counter = 0;
        rolesMenu.addItem(MenuItem.of(counter++, "No Role", Actions.SUCCESS));

        for (final Role roleType : theController.getAllRoles()) {
            rolesMenu.addItem(MenuItem.of(counter++, roleType.toString(), () -> roleTypes.add(roleType)));
        }

        return rolesMenu;
    }

    /**
     * Builds the menu for selecting roles available to customer managers.
     *
     * @param roleTypes a set to store selected roles
     * @return the constructed menu
     */
    private Menu buildCustomerManagerAvailableRoles(final Set<Role> roleTypes) {
        final Menu rolesMenu = new Menu();
        int counter = 0;

        rolesMenu.addItem(MenuItem.of(counter++, "No Role", Actions.SUCCESS));
        rolesMenu.addItem(MenuItem.of(counter, Jobs4URoles.CUSTOMER.toString(), () -> roleTypes.add(Jobs4URoles.CUSTOMER)));

        return rolesMenu;
    }

    /**
     * Builds the menu for selecting roles available to operators.
     *
     * @param roleTypes a set to store selected roles
     * @return the constructed menu
     */
    private Menu buildOperatorAvailableRoles(final Set<Role> roleTypes) {
        final Menu rolesMenu = new Menu();
        int counter = 0;

        rolesMenu.addItem(MenuItem.of(counter++, "No Role", Actions.SUCCESS));
        rolesMenu.addItem(MenuItem.of(counter, Jobs4URoles.CANDIDATE.toString(), () -> roleTypes.add(Jobs4URoles.CANDIDATE)));

        return rolesMenu;
    }

    /**
     * Returns the headline for this UI.
     *
     * @return the headline for this UI
     */
    @Override
    public String headline() {
        return "Add User";
    }
}
