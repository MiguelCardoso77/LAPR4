package backoffice.presentation.authz;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.presentation.console.AbstractUI;
import core.application.controllers.ListUsersController;

/**
 * UI for listing backoffice users.
 *
 * @author Miguel Cardoso
 */
public class ListBackofficeUsersUI extends AbstractUI {
    private final ListUsersController theController = new ListUsersController();

    /**
     * Retrieves the headline for this UI.
     *
     * @return the headline
     */
    @Override
    public String headline() {
        return "List BackOffice Users";
    }

    /**
     * Retrieves the elements to be displayed.
     *
     * @return the iterable collection of backoffice users
     */
    protected Iterable<SystemUser> elements() {
        return theController.allBackofficeUsers();
    }

    /**
     * Displays the UI for listing backoffice users.
     *
     * @return false indicating the operation is finished
     */
    @Override
    protected boolean doShow() {
        final Iterable<SystemUser> iterable = elements();

        if (!iterable.iterator().hasNext()) {
            System.out.println("There is no Backoffice User");

        } else {
            for (SystemUser user : iterable) {
                System.out.println(user.username() + user.name().firstName() + user.name().lastName());
            }
        }

        return true;
    }

}
