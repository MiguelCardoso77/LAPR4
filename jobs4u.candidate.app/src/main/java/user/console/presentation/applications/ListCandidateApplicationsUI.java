package user.console.presentation.applications;

import core.application.controllers.ListCandidateApplicationsController;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

/**
 * UI class for listing all candidate applications.
 *
 * @author Diana Neves
 */
public class ListCandidateApplicationsUI extends AbstractUI {
    private final ListCandidateApplicationsController listCandidateApplicationsController = new ListCandidateApplicationsController();
    private final String email;

    /**
     * Constructor for ListCandidateApplicationsUI.
     *
     * @param email The email of the candidate whose applications are to be listed
     */
    public ListCandidateApplicationsUI(String email) {
        this.email = email;
    }

    /**
     * Displays the list of candidate applications.
     *
     * @return true if the UI should continue running, false otherwise
     */
    @Override
    protected boolean doShow() {
        List<String> applications = listCandidateApplicationsController.sendApplicationsRequest(email);

        if (applications != null) {
            for (String applicationCandidate : applications) {
                System.out.printf("%-140s \n", applicationCandidate);
            }
        }

        return true;
    }

    /**
     * Provides the headline for the UI.
     *
     * @return The headline string
     */
    @Override
    public String headline() {
        return "List all candidate applications";
    }
}
