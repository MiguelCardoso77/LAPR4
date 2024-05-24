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
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final String RETURN_LABEL = "Return ";
    private static final int EXIT_OPTION = 0;

    // MENU OPTIONS
    private static final int MY_USER_OPTION = 1;
    private static final int USER_MANAGEMENT_OPTION = 2;

    private static final String SEPARATOR_LABEL = "--------------";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
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

    @Override
    public String headline() {
        return authz.session().map(s -> "BackOffice [ @" + s.authenticatedUser().identity() + " ]").orElse("BackOffice [ ==Anonymous== ]");
    }

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

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (authz.isAuthenticatedUserAuthorizedTo(Jobs4URoles.LANGUAGE_ENGINEER)) {
            final Menu languageEngineerMenu = buildLanguageEnginnerMenu();
            mainMenu.addSubMenu(USER_MANAGEMENT_OPTION, languageEngineerMenu);
        }
        if (authz.isAuthenticatedUserAuthorizedTo(Jobs4URoles.CANDIDATE)) {
            final Menu candidatemenu = buildCandidateMenu();
            mainMenu.addSubMenu(USER_MANAGEMENT_OPTION, candidatemenu);
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    private Menu buildAdminMenu() {
        AdminMenu admin = new AdminMenu();
        return admin.build();
    }

    private Menu buildCustomerManagerMenu() {
        CustomerManagerMenu customerManager = new CustomerManagerMenu();
        return customerManager.build();
    }

    private Menu buildLanguageEnginnerMenu() {
        LanguageEngineerMenu languageEngineerMenu = new LanguageEngineerMenu();
        return languageEngineerMenu.build();
    }

    private Menu buildOperatorMenu() {
        OperatorMenu operator = new OperatorMenu();
        return operator.build();
    }
    private Menu buildCandidateMenu() {
        CandidateMenu candidate = new CandidateMenu();
        return candidate.build();
    }

}
