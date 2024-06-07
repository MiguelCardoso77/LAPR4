package user.console.presentation;

import console.presentation.authz.MyUserMenu;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;
import user.console.presentation.applications.ListCandidateApplicationsAction;
import user.console.presentation.email.CheckEmailsAction;
import user.console.presentation.notifications.ListCandidateNotificationsAction;

/**
 * Represents the main menu of the Candidate App.
 * Extends {@link CandidateUserUI} to display user authentication information in the headline.
 * Responsible for displaying the main menu options and handling user input.
 * This menu includes options for managing the user's profile and exiting the application.
 * <p>
 * The main menu consists of the following options:
 * 1. My User: Sub-menu for managing the current user's profile.
 * 2. Exit: Option to exit the application.
 * </p>
 *
 * @author Miguel Cardoso
 */
class MainMenu extends CandidateUserUI {

    private static final String SEPARATOR_LABEL = "--------------";
    private static final int EXIT_OPTION = 0;
    private static final int USER_PROFILE_OPTION = 1;
    private static final int LIST_ALL_MY_APPLICATIONS = 2;
    private static final int CHECK_NOTIFICATIONS = 3;
    private String email;

    public MainMenu(String email){
        super();
        this.email = email;
    }

    /**
     * Displays the main menu and handles user input.
     *
     * @return true if the user selected the exit option, false otherwise
     */
    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * Builds and displays the main menu options.
     *
     * @return true if the user selected the exit option, false otherwise
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);

        return renderer.render();
    }

    /**
     * Builds the main menu with its options.
     *
     * @return the constructed main menu
     */
    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();
        final Menu myUserMenu = new MyUserMenu();

        mainMenu.addSubMenu(USER_PROFILE_OPTION, myUserMenu);
        mainMenu.addItem(LIST_ALL_MY_APPLICATIONS, "List all my Applications", new ListCandidateApplicationsAction(email));
        mainMenu.addItem(CHECK_NOTIFICATIONS, "Check Notifications", new ListCandidateNotificationsAction(email));
        mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    @Override
    public String headline() {
        return "Candidate Menu";
    }
}
