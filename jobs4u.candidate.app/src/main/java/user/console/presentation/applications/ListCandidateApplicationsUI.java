package user.console.presentation.applications;

import core.application.controllers.ListCandidateApplicationsController;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

public class ListCandidateApplicationsUI extends AbstractUI {
    private final ListCandidateApplicationsController listCandidateApplicationsController = new ListCandidateApplicationsController();
    private final String email;

    public ListCandidateApplicationsUI(String email) {
        this.email = email;
    }

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

    @Override
    public String headline() {
        return "List all candidate applications";
    }
}
