package backoffice.presentation;

import backoffice.presentation.application.ListJobOpeningApplicationsAction;
import backoffice.presentation.authz.*;
import backoffice.presentation.candidate.DisplayCandidateDataAction;
import backoffice.presentation.candidate.ListCandidatesAction;
import backoffice.presentation.candidate.RegisterCandidateAction;
import backoffice.presentation.customer.RegisterCustomerAction;
import backoffice.presentation.jobOpening.AddJobOpeningAction;
import backoffice.presentation.menus.OperatorMenu;
import infrastructure.Application;
import console.presentation.authz.MyUserMenu;
import core.domain.user.Jobs4URoles;
import eapli.framework.actions.Actions;
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

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final String RETURN_LABEL = "Return ";
    private static final int EXIT_OPTION = 0;

    // OPTIONS
    private static final int OPTION_ONE = 1;
    private static final int OPTION_TWO = 2;
    private static final int OPTION_THREE = 3;
    private static final int OPTION_FOUR = 4;
    private static final int OPTION_FIVE = 5;
    private static final int OPTION_SIX = 6;
    private static final int OPTION_SEVEN = 7;
    private static final int OPTION_EIGHT = 8;
    private static final int OPTION_NINE = 9;
    private static final int OPTION_TEN = 10;

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

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    private Menu buildAdminMenu() {
        final Menu menu = new Menu("Admin Actions >");

        menu.addItem(OPTION_ONE, "List all Users", new ListUsersAction());
        menu.addItem(OPTION_TWO, "List all Backoffice Users", new ListBackofficeUsersAction());
        menu.addItem(OPTION_THREE, "List all Candidate's data", new DisplayCandidateDataAction());
        menu.addItem(OPTION_FOUR, "List all applications of a job opening", new ListJobOpeningApplicationsAction() );

        menu.addItem(OPTION_FIVE, "Add User", new AddUserUI()::show);
        menu.addItem(OPTION_SIX,"Activate User", new ActivateUserAction());
        menu.addItem(OPTION_SEVEN, "Deactivate User", new DeactivateUserAction());

        menu.addItem(OPTION_EIGHT, "Add Job Opening", new AddJobOpeningAction());
        menu.addItem(OPTION_NINE, "Register Customer", new RegisterCustomerAction());

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildCustomerManagerMenu() {
        final Menu menu = new Menu("Customer Manager Actions >");

        menu.addItem(OPTION_ONE, "List all Candidate's data", new DisplayCandidateDataAction());
        menu.addItem(OPTION_TWO, "List all applications of a job opening", new ListJobOpeningApplicationsAction() );

        menu.addItem(OPTION_THREE, "Register Customer", new RegisterCustomerAction());
        menu.addItem(OPTION_FOUR, "Register Candidate", new RegisterCandidateAction());
        menu.addItem(OPTION_FIVE, "Add Job Opening", new AddJobOpeningAction());

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildOperatorMenu() {
        OperatorMenu operator = new OperatorMenu();
        return operator.build();
    }

}
