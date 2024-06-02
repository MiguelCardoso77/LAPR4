package backoffice.presentation.menus;

import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.presentation.console.AbstractUI;

/**
 * @author 1220812@isep.ipp.pt
 */
public class LanguageEngineerMenu extends AbstractUI {

    private static final String RETURN_LABEL = "Return ";
    private static final int EXIT_OPTION = 0;


    @Override
    protected boolean doShow() {
        return true;
    }

    public Menu build(){
        final Menu menu = new Menu("Language Engineer Actions >");

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    @Override
    public String headline() {
        return "Language Engineer Menu";
    }
}
