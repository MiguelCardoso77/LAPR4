package backoffice.presentation.authz;

import core.application.controllers.ListUsersController;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.presentation.console.AbstractUI;

/**
 * UI for listing users.
 *
 * @author Miguel Cardoso
 */
@SuppressWarnings({"squid:S106"})
public class ListUsersUI extends AbstractUI {
    private final ListUsersController theController = new ListUsersController();

    /**
     * The UI's controller.
     *
     * @return the controller
     */
    @Override
    protected boolean doShow() {
        System.out.println(elements());
        return true;
    }

    /**
     * Generates the headline for the UI.
     *
     * @return the headline for the UI
     */
    @Override
    public String headline() {
        return "List Users";
    }

    /**
     * The elements to display.
     *
     * @return the elements to display
     */
    protected Iterable<SystemUser> elements() {
        return theController.allUsers();
    }

}
