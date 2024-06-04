package user.console.presentation.applications;

import core.application.controllers.AddUserController;
import core.application.controllers.ListCandidateApplicationsController;
import core.domain.application.Application;
import core.domain.candidate.Candidate;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

public class ListCandidateApplicationsUI extends AbstractUI {
    private final ListCandidateApplicationsController listCandidateApplicationsController = new ListCandidateApplicationsController();
    private AddUserController addUserController = new AddUserController();


    @Override
    protected boolean doShow() {
        String email = Console.readLine("Email");
        listCandidateApplicationsController.sendApplicationsRequest(email);

        return true;
    }

    @Override
    public String headline() {
        return "List all candidate applications";
    }
}
