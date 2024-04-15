package menus;

import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.ListUI;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

import java.util.Optional;

public class MainMenu extends Jobs4uUserBaseUI {
    private static final String SEPARATOR_LABEL = "--------------";

    private static final String RETURN = "Return ";

    private static final String NOT_IMPLEMENTED_YET = "Not implemented yet";

    private static final int EXIT_OPTION = 0;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int BOOKINGS_OPTION = 2;
    private static final int ACCOUNT_OPTION = 3;
    private static final int SETTINGS_OPTION = 4;

    // BOOKINGS MENU
    private static final int BOOK_A_MEAL_OPTION = 2;
    private static final int LIST_MY_BOOKINGS_OPTION = 3;

    // ACCOUNT MENU
    private static final int LIST_MOVEMENTS_OPTION = 1;

    // SETTINGS
    private static final int SET_USER_ALERT_LIMIT_OPTION = 1;

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
        final var renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));

        final Menu bookingsMenu = buildBookingsMenu();
        mainMenu.addSubMenu(BOOKINGS_OPTION, bookingsMenu);

        mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));

        final Menu accountMenu = buildAccountMenu();
        mainMenu.addSubMenu(ACCOUNT_OPTION, accountMenu);

        mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));

        final Menu settingsMenu = buildAdminSettingsMenu();
        mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);

        mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        return mainMenu;
    }

    private Menu buildAccountMenu() {
        final Menu accountMenu = new Menu("Account");
        accountMenu.addItem(LIST_MOVEMENTS_OPTION, "List movements", () -> {
            final Optional<UserSession> s = authz.session();
            new ListMovementsUI<>(() -> new ListMovementsController().allMovementsByUsername(s.orElseThrow(IllegalStateException::new).authenticatedUser().identity()), new MovementPrinter(), "Movement", "List My Movements", "No data").show();
            return true;
        });
        accountMenu.addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);
        return accountMenu;
    }

    private Menu buildBookingsMenu() {
        final Menu menu = new Menu("Bookings");
        menu.addItem(BOOK_A_MEAL_OPTION, "Book a meal", () -> new RegisterBookingUI().show());

        menu.addItem(LIST_MY_BOOKINGS_OPTION, "My Bookings", () -> {
                    new ListUI<>(new MyBookingsController().myBookings(), new BookingPrinter(), "Booking",
                    String.format("   %-40s %-15s %s", "TOKEN", "DAY", "STATUS"), "List My Bookings",
                    "No data").show();
            return true;
        });
        menu.addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);
        return menu;
    }

    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Settings >");

        menu.addItem(SET_USER_ALERT_LIMIT_OPTION, "Set users' alert limit", new ShowMessageAction(NOT_IMPLEMENTED_YET));
        menu.addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);

        return menu;
    }
}
