package user.console.presentation;

import console.presentation.authz.LoginUI;
import user.console.presentation.myuser.SignupRequestAction;
import infrastructure.authz.AuthenticationCredentialHandler;
import eapli.usermanagement.domain.Jobs4URoles;
import eapli.framework.actions.ChainedAction;
import eapli.framework.actions.menu.Menu;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * @author Paulo Gandra Sousa
 */
public class FrontMenu extends AbstractUI {

    private static final int EXIT_OPTION = 0;

    private static final int LOGIN_OPTION = 1;
    private static final int SIGNUP_OPTION = 2;

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
        final Menu menu = new Menu();
        menu.addItem(LOGIN_OPTION, "Login", new ChainedAction(new LoginUI(new AuthenticationCredentialHandler(), Jobs4URoles.CANDIDATE)::show, () -> {
            new MainMenu().mainLoop();
            return true;
        }));
        //TODO: instead of leaving the app, return to the main menu again
        menu.addItem(SIGNUP_OPTION, "Sign up", new SignupRequestAction());
        menu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

        final MenuRenderer renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    @Override
    public String headline() {
        return "Jobs4U Candidate Menu";
    }
}
