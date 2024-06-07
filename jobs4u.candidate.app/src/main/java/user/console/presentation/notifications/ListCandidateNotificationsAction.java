package user.console.presentation.notifications;

import eapli.framework.actions.Action;
/**
 * Action to list candidate notifications.
 *
 * @author 1220812@isep.ipp.pt
 */

public class ListCandidateNotificationsAction implements Action {
    private String email;
    /**
     * Constructor for ListCandidateNotificationsAction.
     * @param email The email of the candidate.
     */
    public ListCandidateNotificationsAction(String email) {
        this.email = email;
    }
    /**
     * Executes the action.
     * @return true if the action was successful, false otherwise.
     */
    @Override
    public boolean execute() {
        return new ListCandidateNotificationsUI(email).show();
    }
}