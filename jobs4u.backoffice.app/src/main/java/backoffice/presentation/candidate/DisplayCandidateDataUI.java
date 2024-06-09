package backoffice.presentation.candidate;

import console.presentation.utils.ConsoleColors;
import core.application.controllers.DisplayCandidateDataController;
import core.domain.candidate.Candidate;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;

/**
 * User interface for displaying all personal data of a candidate.
 *
 * @author Tomás Gonçalves
 */
public class DisplayCandidateDataUI extends AbstractUI {
    private final DisplayCandidateDataController theController = new DisplayCandidateDataController();

    /**
     * Executes the logic for displaying candidate data.
     *
     * @return Always returns true.
     */
    protected boolean doShow() {
        final List<Candidate> list = new ArrayList<>();
        final Iterable<Candidate> iterable = elements();

        if (!iterable.iterator().hasNext()) {
            System.out.println(ConsoleColors.RED + "There are no candidates!" + ConsoleColors.RESET);
        } else {
            int count = 1;
            System.out.println("Select a Candidate: \n");
            System.out.printf("%-10s%-10s%n", "Option:", "Telephone Number:");

            for (Candidate candidate : iterable) {
                list.add(candidate);
                System.out.printf("%-10s%-10s%n", count, candidate.identity());
                count++;
            }

            final int option = Console.readInteger("Enter the number of Candidate:");
            if (option == 0 || option > list.size()) {
                System.out.println("Invalid option selected");
            } else {
                Candidate selectedCandidate = list.get(option - 1);
                if (selectedCandidate != null) {
                    displayCandidateData(selectedCandidate);
                } else {
                    System.out.println("Candidate information not found");
                }
            }
        }

        return true;
    }

    /**
     * Retrieves all candidates.
     *
     * @return Iterable of all candidates.
     */
    protected Iterable<Candidate> elements() {
        return theController.allCandidates();
    }

    /**
     * Displays the data of a candidate.
     *
     * @param candidateSave The candidate to display the data of.
     */
    private void displayCandidateData(Candidate candidateSave) {
        SystemUser userCandidate = candidateSave.user();
        System.out.println("Candidate Information:");
        System.out.println("Name: " + userCandidate.name());
        System.out.println("Email: " + userCandidate.email());
        System.out.println("Phone Number: " + candidateSave.identity());
        System.out.println("Curriculum: " + candidateSave.curriculum());
    }

    /**
     * Provides the headline for the UI.
     *
     * @return The headline text.
     */
    @Override
    public String headline() {
        return "Display All Personal Data of a Candidate";
    }
}
