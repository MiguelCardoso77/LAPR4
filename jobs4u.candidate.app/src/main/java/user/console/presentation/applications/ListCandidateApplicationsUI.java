package user.console.presentation.applications;

import core.application.controllers.ListCandidateApplicationsController;
import core.domain.application.Application;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

public class ListCandidateApplicationsUI extends AbstractUI {
    private final ListCandidateApplicationsController listCandidateApplicationsController = new ListCandidateApplicationsController();
    private  String email;

    public  ListCandidateApplicationsUI (String email) {
        this.email = email;
    }

    @Override
    protected boolean doShow() {
        List<Application> applications = listCandidateApplicationsController.sendApplicationsRequest(email);

        if (applications != null){
        System.out.printf("%-30s%-30s%-30s%-30s%n", "Application ID", "Rank", "Status", "Job Reference");
        for (Application applicationCandidate : applications) {
            System.out.printf("%-30s%-30s%-30s%-30s%n", applicationCandidate.identity(), applicationCandidate.rank(), applicationCandidate.status().name(), applicationCandidate.jobReference().jobReference());
        }
        }

        return true;
    }

    @Override
    public String headline() {
        return "List all candidate applications";
    }
}
