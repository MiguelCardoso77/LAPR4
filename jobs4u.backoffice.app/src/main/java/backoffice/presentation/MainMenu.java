package backoffice.presentation;

import backoffice.presentation.menus.*;
import console.presentation.authz.MyUserMenu;
import core.domain.user.Jobs4URoles;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;
import infrastructure.Application;

/**
 * The MainMenu class represents the main menu of the Jobs4u Back Office application.
 * It extends the AbstractUI class and provides the structure for rendering the main menu.
 * The menu includes options for user management, based on the roles of the authenticated user.
 *
 * <p>This class interacts with various sub-menus such as MyUserMenu, AdminMenu,
 * CustomerManagerMenu, LanguageEngineerMenu, and OperatorMenu to build a comprehensive
 * user interface.</p>
 *
 * @author Miguel Cardoso
 * @see AbstractUI
 * @see MyUserMenu
 * @see AdminMenu
 * @see CustomerManagerMenu
 * @see LanguageEngineerMenu
 * @see OperatorMenu
 * @see AuthorizationService
 * @see AuthzRegistry
 * @see Menu
 * @see MenuItem
 * @see MenuRenderer
 * @see HorizontalMenuRenderer
 * @see VerticalMenuRenderer
 * @see ExitWithMessageAction
 * @see Application
 */
public class MainMenu extends AbstractUI {
    private static final int EXIT_OPTION = 0;
    private static final int MY_USER_OPTION = 1;
    private static final int USER_MANAGEMENT_OPTION = 2;

    private static final String SEPARATOR_LABEL = "--------------";
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * Displays the main menu.
     *
     * @return true if the user selected the exit option, false otherwise
     */
    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * Executes the main logic of displaying the menu and handling user input.
     *
     * @return true if the user selected the exit option, false otherwise
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    /**
     * Returns the headline of the menu, indicating the authenticated user or anonymous state.
     *
     * @return the headline string
     */
    @Override
    public String headline() {
        return authz.session().map(s -> "BackOffice [ @" + s.authenticatedUser().identity() + " ]").orElse("BackOffice [ ==Anonymous== ]");
    }

    /**
     * Builds the main menu structure, including sub-menus based on the roles of the authenticated user.
     *
     * @return the constructed main menu
     */
    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (authz.isAuthenticatedUserAuthorizedTo(Jobs4URoles.BOOTSTRAP, Jobs4URoles.ADMIN)) {
            final Menu adminMenu = buildAdminMenu();
            mainMenu.addSubMenu(USER_MANAGEMENT_OPTION, adminMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(Jobs4URoles.CUSTOMER_MANAGER)) {
            final Menu customerManagerMenu = buildCustomerManagerMenu();
            mainMenu.addSubMenu(USER_MANAGEMENT_OPTION, customerManagerMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(Jobs4URoles.OPERATOR)) {
            final Menu operatorMenu = buildOperatorMenu();
            mainMenu.addSubMenu(USER_MANAGEMENT_OPTION, operatorMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(Jobs4URoles.LANGUAGE_ENGINEER)) {
            final Menu languageEngineerMenu = buildLanguageEngineerMenu();
            mainMenu.addSubMenu(USER_MANAGEMENT_OPTION, languageEngineerMenu);
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    /**
     * Builds the admin menu.
     *
     * @return the constructed admin menu
     */
    private Menu buildAdminMenu() {
        AdminMenu admin = new AdminMenu();
        return admin.build();
    }

    /**
     * Builds the customer manager menu.
     *
     * @return the constructed customer manager menu
     */
    private Menu buildCustomerManagerMenu() {
        CustomerManagerMenu customerManager = new CustomerManagerMenu();
        return customerManager.build();
    }

    /**
     * Builds the language engineer menu.
     *
     * @return the constructed language engineer menu
     */
    private Menu buildLanguageEngineerMenu() {
        LanguageEngineerMenu languageEngineerMenu = new LanguageEngineerMenu();
        return languageEngineerMenu.build();
    }

    /**
     * Builds the operator menu.
     *
     * @return the constructed operator menu
     */
    private Menu buildOperatorMenu() {
        OperatorMenu operator = new OperatorMenu();
        return operator.build();
    }

}
