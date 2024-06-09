package backoffice.presentation.interview;

import console.presentation.utils.ConsoleColors;
import core.application.controllers.*;
import core.domain.application.Application;
import core.domain.candidate.Candidate;
import core.domain.interview.JobInterview;
import core.domain.jobOpening.JobOpening;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

/**
 * User Interface for displaying an ordered list of candidates based on their interview scores
 * for a selected job opening.
 *
 * @author Tomás Gonçalves
 */
public class OrderedListOfCandidatesUI extends AbstractUI {
    private final OrderedListOfCandidatesController orderedListOfCandidatesController = new OrderedListOfCandidatesController();

    /**
     * Displays the ordered list of candidates for a selected job opening.
     *
     * @return true if the process was successful.
     */
    public boolean doShow() {
        JobOpening jobOpening = orderedListOfCandidatesController.selectJobOpening();

        Iterable<Application> applicationList = orderedListOfCandidatesController.allApplicationsOfJobOpening(jobOpening.jobReference());

        List<JobInterview> orderedList = orderedListOfCandidatesController.orderedList(applicationList);

        List<Application> finalList = orderedListOfCandidatesController.applicationList(orderedList);

        displayList(finalList, orderedList);

        return true;
    }

    /**
     * Displays the final list of applications along with their corresponding job interviews.
     *
     * @param finalList   A list of applications corresponding to the ordered job interviews.
     * @param orderedList A list of job interviews ordered by their scores.
     */
    public void displayList(List<Application> finalList, List<JobInterview> orderedList) {
        int count = 0;

        if (orderedList != null && finalList != null) {
            for (JobInterview jobInterview : orderedList) {
                if (finalList.contains(jobInterview.application())) {
                    count++;
                    if (count == 1) {
                        System.out.printf("%-50s | %-50s | %-30s | \n", "Telephone Number", "Curriculum", "Grade");
                    }
                    Candidate candidate = jobInterview.application().candidate();
                    System.out.printf("%-50s | %-50s | %-30s | \n", candidate.identity(), candidate.curriculum(), jobInterview.score());
                }
            }
        }

        if (count == 0) {
            System.out.println(ConsoleColors.RED + "There are no candidates with grade for this job opening." + ConsoleColors.RESET);
        }
    }

    /**
     * The headline for the UI.
     *
     * @return The headline for the UI.
     */
    @Override
    public String headline() {
        return "Order List of Candidates";
    }
}





