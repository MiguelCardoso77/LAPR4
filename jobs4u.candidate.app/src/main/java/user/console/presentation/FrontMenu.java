package user.console.presentation;

import console.presentation.authz.LoginUI;
import infrastructure.authz.AuthenticationCredentialHandler;
import core.domain.user.Jobs4URoles;
import eapli.framework.actions.ChainedAction;
import eapli.framework.actions.menu.Menu;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * User interface class for the front menu of Jobs4U application.
 * This menu provides options for user login and exiting the application.
 * It is specifically designed for candidates.
 *
 * @author Miguel Cardoso
 */
public class FrontMenu extends AbstractUI {
    private static final int EXIT_OPTION = 0;
    private static final int LOGIN_OPTION = 1;

    /**
     * Displays the front menu to the user.
     *
     * @return true if the user selected the exit option
     */
    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * Displays the front menu to the user.
     *
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = new Menu("Candidate Menu Options:");
        LoginUI loginUI = new LoginUI(new AuthenticationCredentialHandler(), Jobs4URoles.CANDIDATE, true);

        menu.addItem(LOGIN_OPTION, "Login", new ChainedAction(loginUI::show,() -> {new MainMenu(loginUI.email()).mainLoop();return true;}));
        menu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        final MenuRenderer renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    /**
     * Returns the headline for this menu.
     *
     * @return the headline for this menu
     */
    @Override
    public String headline() {
        return "Jobs4U Candidate Menu";
    }
}
