package backoffice.presentation.candidate;

import core.application.controllers.ListCandidatesController;
import core.domain.candidate.Candidate;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

/**
 * User interface responsible for listing all candidates.
 * It retrieves a list of all registered candidates from the controller and displays their information.
 * The displayed information includes the telephone number, curriculum, and user email of each candidate.
 * If there are no candidates registered, an appropriate message is displayed.
 * This UI is used to view and manage candidate information effectively.
 * It returns true upon completion of displaying the candidate list.
 *
 * @author Miguel Cardoso
 */
public class ListCandidatesUI extends AbstractUI {
    private final ListCandidatesController theController = new ListCandidatesController();

    /**
     * Displays the list of all candidates.
     *
     * @return true upon completion of displaying the candidate list.
     */
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

    /**
     * Provides the headline for the UI.
     *
     * @return a string representing the headline for the UI.
     */
    @Override
    public String headline() {
        return "List All Candidates";
    }
}
