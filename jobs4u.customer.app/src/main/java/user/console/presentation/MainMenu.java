package user.console.presentation;

import console.presentation.authz.MyUserMenu;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;
import user.console.presentation.jobOpenings.ListCustomerJobOpeningsAction;

/**
 * @author Paulo Gandra Sousa
 */
class MainMenu extends CustomerUserUI {
    private static final String SEPARATOR_LABEL = "--------------";
    private static final int EXIT_OPTION = 0;
    private static final int MY_USER_OPTION = 1;
    private static final int LIST_JOB_OPENINGS_OPTION = 2;
    private final String email;

    /**
     * Constructs a new MainMenu.
     *
     * @param email The email of the customer.
     */
    public MainMenu(String email) {
        super();
        this.email = email;
    }

    /**
     * Displays the main menu to the user.
     *
     * @return true if the user selected the exit option
     */

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * Displays the main menu to the user.
     *
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    /**
     * Builds the main menu.
     *
     * @return The built menu.
     */
    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);
        mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        mainMenu.addItem(LIST_JOB_OPENINGS_OPTION, "List Job Openings", new ListCustomerJobOpeningsAction(email));
        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }
}
