package console.presentation.authz;

import infrastructure.authz.AuthenticationCredentialHandler;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;

/**
 * MyUserMenu represents a menu for managing user accounts and sessions.
 * It provides options for changing passwords, logging in, and logging out.
 *
 * @author Miguel Cardoso
 */
public class MyUserMenu extends Menu {
    private static final String MENU_TITLE = "My account >";

    // Menu options
    private static final int EXIT_OPTION = 0;
    private static final int CHANGE_PASSWORD_OPTION = 1;
    private static final int LOGIN_OPTION = 2;
    private static final int LOGOUT_OPTION = 3;

    // Services
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * Constructs a MyUserMenu instance.
     */
    public MyUserMenu() {
        super(MENU_TITLE);
        buildMyUserMenu(null);
    }

    /**
     * Constructs a MyUserMenu instance with a specified role.
     *
     * @param onlyWithThis the role to restrict menu options to, or null for unrestricted access
     */
    public MyUserMenu(final Role onlyWithThis) {
        super(MENU_TITLE);
        buildMyUserMenu(onlyWithThis);
    }

    /**
     * Builds the menu items for managing user accounts and sessions.
     *
     * @param onlyWithThis the role to restrict menu options to, or null for unrestricted access
     */
    private void buildMyUserMenu(final Role onlyWithThis) {
        if (authz.hasSession()) {
            addItem(MenuItem.of(CHANGE_PASSWORD_OPTION, "Change Password", new ChangePasswordUI()::show));
            addItem(MenuItem.of(LOGIN_OPTION, "Change User", new LoginUI(new AuthenticationCredentialHandler(), onlyWithThis)::show));
            addItem(MenuItem.of(LOGOUT_OPTION, "Logout", new LogoutUI()::show));
        } else {
            addItem(MenuItem.of(LOGIN_OPTION, "Login", new LoginUI(new AuthenticationCredentialHandler(), onlyWithThis)::show));
        }

        addItem(MenuItem.of(EXIT_OPTION, "Return ", Actions.SUCCESS));
    }
}
