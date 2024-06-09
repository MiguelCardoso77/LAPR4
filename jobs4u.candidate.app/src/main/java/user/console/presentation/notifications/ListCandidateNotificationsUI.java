package user.console.presentation.notifications;

import core.application.controllers.ListCandidateNotificationsController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

/**
 * UI for listing candidate applications.
 *
 * @author Diogo Ribeiro
 */
public class ListCandidateNotificationsUI extends AbstractUI {
    public final ListCandidateNotificationsController listCandidateNotificationsController = new ListCandidateNotificationsController();
    private final String email;

    /**
     * Constructor for ListCandidateApplicationsUI.
     *
     * @param email The email of the candidate.
     */
    public ListCandidateNotificationsUI(String email) {
        this.email = email;
    }

    /**
     * Shows the UI.
     *
     * @return true if the UI was shown successfully, false otherwise.
     */
    @Override
    protected boolean doShow() {

        int selection = showMenuAndGetSelection();
        List<String> notifications;

        switch (selection) {
            case 1:
                System.out.println("Fetching new notifications...");
                notifications = listCandidateNotificationsController.sendNewNotificationsRequest(email);
                if (notifications != null) {
                    for (String notification : notifications) {
                        System.out.println(notification);
                    }
                }
                break;
            case 2:
                System.out.println("Fetching old notifications...");
                notifications = listCandidateNotificationsController.sendOldNotificationsRequest(email);
                if (notifications != null) {
                    for (String notification : notifications) {
                        System.out.println(notification);
                    }
                }
                break;
            default:
                System.out.println("Invalid option.");
                return false;
        }

        return true;

    }

    /**
     * Shows a menu and gets the user's selection.
     *
     * @return The user's selection.
     */
    private int showMenuAndGetSelection() {
        System.out.println("Please select an option:");
        System.out.println("1. List new notifications");
        System.out.println("2. List old notifications");
        System.out.print("Your selection: ");

        return Console.readInteger("");
    }

    /**
     * Returns the headline for the UI.
     *
     * @return The headline for the UI.
     */
    @Override
    public String headline() {
        return "List All Candidate Notifications";
    }

}
