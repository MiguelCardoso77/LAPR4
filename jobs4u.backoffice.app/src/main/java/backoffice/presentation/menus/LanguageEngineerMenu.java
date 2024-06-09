package backoffice.presentation.menus;

import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.presentation.console.AbstractUI;

/**
 * The LanguageEngineerMenu class represents the menu for language engineer actions
 * in the Jobs4u Back Office application.
 * It extends the AbstractUI class and provides a menu with options for language engineers.
 * Currently, it includes only an option to exit the menu.
 *
 * @see AbstractUI
 * @see Menu
 * @see Actions
 *
 * @author Diogo Ribeiro
 */
public class LanguageEngineerMenu extends AbstractUI {
    private static final String RETURN_LABEL = "Return ";
    private static final int EXIT_OPTION = 0;

    /**
     * This method is overridden to always return true as this UI does not perform any specific actions.
     *
     * @return true
     */
    @Override
    protected boolean doShow() {
        return true;
    }

    /**
     * Builds the menu for language engineer actions.
     *
     * @return the constructed menu with language engineer actions
     */
    public Menu build(){
        final Menu menu = new Menu("Language Engineer Actions >");

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    /**
     * Returns the headline for the language engineer menu.
     *
     * @return the headline string
     */
    @Override
    public String headline() {
        return "Language Engineer Menu";
    }
}
