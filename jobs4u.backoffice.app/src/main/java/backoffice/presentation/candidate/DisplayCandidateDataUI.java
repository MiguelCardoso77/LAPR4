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

public class DisplayCandidateDataUI extends AbstractListUI<Candidate> {

    private final DisplayCandidateDataController theController = new DisplayCandidateDataController();

    public String headline() {
        return "Display all personal data of a candidate";
    }

    protected String emptyMessage() {
        return "No data.";
    }

    protected Iterable<Candidate> elements() {
        return theController.allCandidates();
    }

    @Override
    protected Visitor<Candidate> elementPrinter() {
        return null;
    }

    protected String elementName() {
        return "Candidates";
    }

    @Override
    protected String listHeader() {
        return null;
    }


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
                if (candidateSave != null) {

                    SystemUser userCandidate = candidateSave.user();
                    System.out.println("Candidate Information:");
                    System.out.println("Name: " + userCandidate.name());
                    System.out.println("Email: " + userCandidate.email());
                    System.out.println("Phone Number: " + candidateSave.identity());
                    System.out.println("Curriculum: " + candidateSave.curriculum());
                } else {
                    System.out.println("Candidate information not found");
                }
            }
        }
        return false;
    }
}
