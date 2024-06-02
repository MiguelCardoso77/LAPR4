package user.console.presentation.applications;

import core.application.controllers.ListCandidateApplicationsController;
import eapli.framework.presentation.console.AbstractUI;

public class ListCandidateApplicationsUI extends AbstractUI {
    private final ListCandidateApplicationsController listCandidateApplicationsController = new ListCandidateApplicationsController();

    @Override
    protected boolean doShow() {
        //Candidate candidateLoggedIn = listCandidateApplicationsController.candidateLoggedIn();\


        return true;
    }

    @Override
    public String headline() {
        return "List all candidate applications";
    }
}
