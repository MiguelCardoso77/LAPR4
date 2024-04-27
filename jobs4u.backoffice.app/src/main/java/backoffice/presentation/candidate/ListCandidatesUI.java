package backoffice.presentation.candidate;

import core.application.controllers.ListCandidatesController;
import core.domain.candidate.Candidate;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

public class ListCandidatesUI extends AbstractUI {
    private final ListCandidatesController theController = new ListCandidatesController();

    @Override
    protected boolean doShow() {
        List<Candidate> allCandidates = theController.allCandidates();

        if (allCandidates.isEmpty()) {
            System.out.println("There are no candidates to show.");
        } else {
            System.out.printf("%-20s | %-30s | %-25s \n", "Telephone Number", "Curriculum", "User");
            for (Candidate candidate : allCandidates) {
                System.out.printf("%-20s | %-30s | %-25s \n", candidate.identity(), candidate.curriculum(), candidate.user().email());
            }
        }

        return true;
    }

    @Override
    public String headline() {
        return "List All Candidates";
    }
}
