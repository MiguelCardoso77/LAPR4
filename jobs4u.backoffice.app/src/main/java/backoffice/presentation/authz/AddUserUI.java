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
 * UI for adding a user to the application.
 * <p>
 * Created by nuno on 22/03/16.
 */
public class AddUserUI extends AbstractUI {

    private final AddUserController theController = new AddUserController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

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

    private boolean showRoles(final Set<Role> roleTypes) {
        final Menu rolesMenu;

        if (authz.isAuthenticatedUserAuthorizedTo(Jobs4URoles.OPERATOR)) {
            rolesMenu = buildOperatorAvailableRoles(roleTypes);
        } else if (authz.isAuthenticatedUserAuthorizedTo(Jobs4URoles.CUSTOMER_MANAGER)) {
            rolesMenu = buildCustomerManagerAvailableRoles(roleTypes);
        } else{
            rolesMenu = buildRolesMenu(roleTypes);
        }

        final MenuRenderer renderer = new VerticalMenuRenderer(rolesMenu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    private Menu buildRolesMenu(final Set<Role> roleTypes) {
        final Menu rolesMenu = new Menu();
        int counter = 0;
        rolesMenu.addItem(MenuItem.of(counter++, "No Role", Actions.SUCCESS));

        for (final Role roleType : theController.getAllRoles()) {
            rolesMenu.addItem(MenuItem.of(counter++, roleType.toString(), () -> roleTypes.add(roleType)));
        }

        return rolesMenu;
    }

    private Menu buildCustomerManagerAvailableRoles(final Set<Role> roleTypes) {
        final Menu rolesMenu = new Menu();
        int counter = 0;

        rolesMenu.addItem(MenuItem.of(counter++, "No Role", Actions.SUCCESS));
        rolesMenu.addItem(MenuItem.of(counter, Jobs4URoles.CUSTOMER.toString(), () -> roleTypes.add(Jobs4URoles.CUSTOMER)));

        return rolesMenu;
    }

    private Menu buildOperatorAvailableRoles(final Set<Role> roleTypes) {
        final Menu rolesMenu = new Menu();
        int counter = 0;

        rolesMenu.addItem(MenuItem.of(counter++, "No Role", Actions.SUCCESS));
        rolesMenu.addItem(MenuItem.of(counter, Jobs4URoles.CANDIDATE.toString(), () -> roleTypes.add(Jobs4URoles.CANDIDATE)));

        return rolesMenu;
    }

    @Override
    public String headline() {
        return "Add User";
    }
}
