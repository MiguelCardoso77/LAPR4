package backoffice.presentation.candidate;

import console.presentation.utils.ConsoleColors;
import core.application.controllers.RankCandidatesController;
import core.domain.jobOpening.JobOpening;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

public class RankCandidatesUI extends AbstractUI {
    private final RankCandidatesController theController = new RankCandidatesController();

    @Override
    protected boolean doShow() {
        selectJobOpening();

        return true;
    }

    private void selectJobOpening() {
        List<JobOpening> jobOpenings = theController.getAllJobOpenings();

        for (int i = 0; i < jobOpenings.size(); i++) {
            System.out.println((i + 1) + " - " + jobOpenings.get(i).jobReference());
        }

        int selectedNumber = Console.readInteger("Please select a job opening by entering its number: ");
        if (selectedNumber < 1 || selectedNumber > jobOpenings.size()) {
            System.out.println(ConsoleColors.RED + "Invalid number. Please enter a number between 1 and " + jobOpenings.size() + ConsoleColors.RESET);
        }
    }

    @Override
    public String headline() {
        return "Rank Candidates";
    }
}
