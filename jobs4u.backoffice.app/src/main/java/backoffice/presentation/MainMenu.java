package backoffice.presentation;

import backoffice.presentation.applications.ListJobOpeningApplicationsAction;
import backoffice.presentation.authz.*;
import backoffice.presentation.candidate.RegisterCandidateAction;
import backoffice.presentation.customer.RegisterCustomerAction;
import backoffice.presentation.jobs.AddJobOpeningAction;
import infrastructure.Application;
import backoffice.presentation.clientuser.AcceptRefuseSignupRequestAction;
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

    // USERS
    private static final int LIST_USERS_OPTION = 1;
    private static final int ADD_USER_OPTION = 2;
    private static final int LIST_BACKOFFICE_USERS_OPTION = 3;
    private static final int ACTIVATE_USER_OPTION = 4;
    private static final int DEACTIVATE_USER_OPTION = 5;
    private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 6;

    private static final int LIST_ALL_APPLICATIONS_OF_A_JOBOPENING = 7;


    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int USER_MANAGEMENT_OPTION = 2;
    private static final int SETTINGS_OPTION = 3;

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

        menu.addItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction());
        menu.addItem(ADD_USER_OPTION, "Add User", new AddUserUI()::show);
        menu.addItem(LIST_BACKOFFICE_USERS_OPTION, "List all Backoffice Users", new ListBackofficeUsersAction());
        menu.addItem(ACTIVATE_USER_OPTION,"Activate User", new ActivateUserAction());
        menu.addItem(DEACTIVATE_USER_OPTION, "Deactivate User", new DeactivateUserAction());
        menu.addItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request", new AcceptRefuseSignupRequestAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildCustomerManagerMenu() {
        final Menu menu = new Menu("Customer Manager Actions >");

        menu.addItem(1, "List all Users", new ListUsersAction());
        menu.addItem(2, "Add User", new AddUserUI()::show);
        menu.addItem(3, "List all Candidate's data", new ListCandidatesAction());
        menu.addItem(4, "Add Job Opening", new AddJobOpeningAction());
        menu.addItem(5, "List all applications of a job opening", new ListJobOpeningApplicationsAction() );
        menu.addItem(6, "Register Customer", new RegisterCustomerAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildOperatorMenu() {
        final Menu menu = new Menu("Operator Actions >");

        menu.addItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction());
        menu.addItem(ADD_USER_OPTION, "Add User", new AddUserUI()::show);
        menu.addItem(3, "Register Candidate", new RegisterCandidateAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

}
