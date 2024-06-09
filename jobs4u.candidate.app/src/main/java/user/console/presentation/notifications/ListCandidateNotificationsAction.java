package user.console.presentation.notifications;

import eapli.framework.actions.Action;

/**
 * Action to list candidate notifications.
 *
 * @author Diogo Ribeiro
 */
public class ListCandidateNotificationsAction implements Action {
    private final String email;

    /**
     * Constructor for ListCandidateNotificationsAction.
     *
     * @param email The email of the candidate.
     */
    public ListCandidateNotificationsAction(String email) {
        this.email = email;
    }

    /**
     * Executes the action.
     *
     * @return true if the action was successful, false otherwise.
     */
    @Override
    public boolean execute() {
        return new ListCandidateNotificationsUI(email).show();
    }
}