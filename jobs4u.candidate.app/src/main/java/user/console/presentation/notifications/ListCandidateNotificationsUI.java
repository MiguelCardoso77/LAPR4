package user.console.presentation.notifications;

import core.application.controllers.ListCandidateNotificationsController;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

/**
 * UI for listing candidate applications.
 *
 * @author 1220812@isep.ipp.pt
 */

public class ListCandidateNotificationsUI extends AbstractUI {

    public final ListCandidateNotificationsController listCandidateNotificationsController = new ListCandidateNotificationsController();
    private String email;
    /**
     * Constructor for ListCandidateApplicationsUI.
     * @param email The email of the candidate.
     */
    public ListCandidateNotificationsUI(String email) {
        this.email = email;
    }
    /**
     * Shows the UI.
     * @return true if the UI was shown successfully, false otherwise.
     */
    @Override
    protected boolean doShow() {
        List<String> notifications = listCandidateNotificationsController.sendNotificationsRequest(email);

        if(notifications != null) {
            for(String notification : notifications) {
                System.out.println(notification);
            }
        }

        return true;

    }
    /**
     * Returns the headline for the UI.
     * @return The headline for the UI.
     */
    @Override
    public String headline() {
        return "List all candidate notifications";
    }

}
