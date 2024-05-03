package backoffice.presentation.candidate;

import core.application.controllers.DisplayCandidateDataController;
import core.domain.application.Application;
import core.domain.candidate.Candidate;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;


/**
 * User interface for displaying all personal data of a candidate.
 *
 * @author Tomás Gonçalves
 */
public class DisplayCandidateDataUI extends AbstractListUI<Candidate> {

    private final DisplayCandidateDataController theController = new DisplayCandidateDataController();

    /**
     * Provides the headline for the UI.
     *
     * @return The headline text.
     */
    public String headline() {
        return "Display all personal data of a candidate";
    }


    /**
     * Provides the message to be displayed when there is no data to show.
     *
     * @return The empty message text.
     */
    protected String emptyMessage() {
        return "No data.";
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
     * Specifies how to print each candidate in the list.
     *
     * @return A visitor for printing candidate details.
     */
    @Override
    protected Visitor<Candidate> elementPrinter() {
        return null;
    }

    /**
     * Provides the name for the elements being listed.
     *
     * @return The name of the elements.
     */
    protected String elementName() {
        return "Candidates";
    }

    /**
     * Provides the header for the list.
     *
     * @return The header text.
     */
    @Override
    protected String listHeader() {
        return null;
    }

    /**
     * Executes the logic for displaying candidate data.
     *
     * @return Always returns false.
     */
    protected boolean doShow() {
        final List<Candidate> list = new ArrayList<>();
        final Iterable<Candidate> iterable = elements();
        if (!iterable.iterator().hasNext()) {
            System.out.println("There are no candidates ");
        } else {
            int cont = 1;
            System.out.println("Select a Candidate: \n");
            System.out.printf("%-10s%-10s%n", "Option:", "Telephone Number:");

            Candidate candidateSave = null;
            for (Candidate candidate : iterable) {
                list.add(candidate);
                System.out.printf("%-10s%-10s%n", cont, candidate.identity());
                cont++;
                candidateSave = candidate;
            }
            final int option = Console.readInteger("Enter the number of Candidate:");
            if (option == 0 || option > list.size()) {
                System.out.println("Invalid option selected");
            } else {
                Candidate selectedCandidate = list.get(option - 1); // Adjusted index
                if (selectedCandidate != null) {
                    displayCandidateData(selectedCandidate);
                } else {
                    System.out.println("Candidate information not found");
                }
            }
        }
        return false;
    }



    public boolean displayCandidateData(Candidate candidateSave ){

        SystemUser userCandidate = candidateSave.user();
        System.out.println("Candidate Information:");
        System.out.println("Name: " + userCandidate.name());
        System.out.println("Email: " + userCandidate.email());
        System.out.println("Phone Number: " + candidateSave.identity());
        System.out.println("Curriculum: " + candidateSave.curriculum());
        return true;
    }
}
